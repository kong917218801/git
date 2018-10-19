package com.cpsdb.declareserv.service;

import com.cpsdb.declareapi.utils.UserUtils;
import com.cpsdb.declareserv.dao.DeclareEnterpriseMapper;
import com.cpsdb.declareserv.dao.DeclareToEnterpriseMapper;
import com.cpsdb.declareserv.entity.DeclareEnterprise;
import com.cpsdb.declareserv.entity.DeclareToEnterprise;
import com.cpsdb.declareserv.entity.User;
import com.cpsdb.declareserv.utils.DeclareConfigUtils;
import com.cpsdb.declareserv.utils.DeclareEnterpriseUtils;
import com.cpsdb.declareserv.utils.SubsidyWithdrawUtils;
import com.cpsdb.enterpriseapi.utils.MQEnterprise;
import com.cpsdb.goodsapi.params.CodeUsed;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李银
 * @ClassName DeclareToEnterpriseService
 * @Description
 * @date 2018-09-20 10:42:40
 */
@Service
public class DeclareToEnterpriseService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DeclareToEnterpriseMapper declareToEnterpriseMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private DeclareEnterpriseMapper declareEnterpriseMapper;

    @Autowired
    private SubsidyDetailService subsidyDetailService;

    public void insert(DeclareToEnterprise declareToEnterprise) {
        declareToEnterpriseMapper.insert(declareToEnterprise);
    }

    public void update(DeclareToEnterprise declareToEnterprise) {
        declareToEnterpriseMapper.update(declareToEnterprise);
    }

    public DeclareToEnterprise getById(Long id) {
        return declareToEnterpriseMapper.getById(id);
    }

    public List<Long> getEnterpriseIdUserId(Long userId) {
        User user = userService.getById(userId);
        Long serviceId = null, organizId = null, declarerId = null;
        if (UserUtils.UserType.service.getValue() == user.getType()) {
            serviceId = user.getObjectId();
        } else if (UserUtils.UserType.organiz.getValue() == user.getType()) {
            organizId = user.getObjectId();
        } else if (UserUtils.UserType.declarer.getValue() == user.getType()) {
            declarerId = user.getObjectId();
        } else {
            return new ArrayList<>();
        }
        return this.declareToEnterpriseMapper.getEnterpriseId(serviceId, organizId, declarerId);
    }

    public DeclareToEnterprise getByEnterpriseId(Long enterpriserId) {
        return declareToEnterpriseMapper.getByEnterpriseId(enterpriserId);
    }

    //企业入库补贴
    public void updateMqEnterpriseToDeclare(MQEnterprise data) {
        logger.info("=============MQEnterprise:{}", data.toString());
        //根据传回的mq消息找到申报企业
        DeclareEnterprise declareEnterprise = declareEnterpriseMapper.getByEnterpriseName(StringUtils.trim(data.getEnterpriseName()));
        Long declareEnterpriseId = declareEnterprise == null ? null : declareEnterprise.getId();
        if (declareEnterprise == null) {
            logger.warn("收到一条未走申报流程的企业信息: {}", data.toString());
        }

        DeclareToEnterprise dte = declareToEnterpriseMapper.getByEnterpriseId(data.getEnterpriseId());
        //如果申报企业不存在  则保存记录
        if (dte == null) {
            dte = new DeclareToEnterprise()
                    .setFkDeclareEnterpriseId(declareEnterpriseId)
                    .setEnterpriseName(data.getEnterpriseName())
                    .setFkEnterpriseId(data.getEnterpriseId())
                    .setEnterpriseState(data.getState());
            declareToEnterpriseMapper.insert(dte);
        } else {
            dte.setFkDeclareEnterpriseId(declareEnterpriseId)
                    .setFkEnterpriseId(data.getEnterpriseId())
                    .setEnterpriseName(data.getEnterpriseName())
                    .setEnterpriseState(data.getState());
            declareToEnterpriseMapper.update(dte);
        }

        // 当企业状态到达审核通过时，插入入库补贴
        if (DeclareEnterpriseUtils.EnterpriseState.passed.name().equals(data.getState()) && declareEnterprise != null) {
            this.insertSubsidy(declareEnterprise, data.getEnterpriseId(), data.getAmount());
        }

    }

    private void insertSubsidy(DeclareEnterprise declareEnterprise, Long enterpriseId, BigDecimal amount) {
        //获取企业所属各级部门id
        Long declarerId = declareEnterprise.getFkDeclarerId();
        //插入申报官的补贴
        User declarerUser = userService.getByObjectId(declarerId, UserUtils.UserType.declarer);

        logger.info("!!!!!!!!!!!!!declarerId{};enterpriseId{}!!!!!!!!!!!!!", declareEnterprise.getFkDeclarerId(), enterpriseId);

        subsidyDetailService.insert(SubsidyWithdrawUtils.DetailType.企业入库, declarerUser.getId(),
                enterpriseId, declareEnterprise.getName(), 1, amount,
                DeclareConfigUtils.getBigDecimal("enterprise.declarer.percent"));

        //插入申报服务处的补贴
        Long organizId = declareEnterprise.getFkOrganizId();
        User organizUser = userService.getByObjectId(organizId, UserUtils.UserType.organiz);
        subsidyDetailService.insert(SubsidyWithdrawUtils.DetailType.企业入库, organizUser.getId(),
                enterpriseId, declareEnterprise.getName(), 1, amount,
                DeclareConfigUtils.getBigDecimal("enterprise.organiz.percent"));

        //插入省级服务中心的补贴
        Long provenceCenterId = declareEnterprise.getFkProvenceCenterId();
        if (provenceCenterId != null) {
            User provenceUser = userService.getByObjectId(provenceCenterId, UserUtils.UserType.service);
            subsidyDetailService.insert(SubsidyWithdrawUtils.DetailType.企业入库, provenceUser.getId(),
                    enterpriseId, declareEnterprise.getName(), 1, amount,
                    DeclareConfigUtils.getBigDecimal("enterprise.service.percent"));
        }
    }

    public void updateCodeUsed(CodeUsed data) {
        BigDecimal paidAmount = ObjectUtils.defaultIfNull(data.getPaidAmount(), BigDecimal.ZERO);
        Integer codeCount = ObjectUtils.defaultIfNull(data.getQuantity(), 0);
        //更新企业的二维码数量和支付总金额
        DeclareToEnterprise dte = declareToEnterpriseMapper.getByEnterpriseId(data.getEnterpriseId());
        if (null == dte) {
            logger.warn("收到一条未走申报流程的企业的用码信息: {}", data.toString());
            return;
        }

        dte.setFkEnterpriseId(data.getEnterpriseId())
                .setPaidAmount(ObjectUtils.defaultIfNull(dte.getPaidAmount(), BigDecimal.ZERO).add(paidAmount))
                .setCodeCount(ObjectUtils.defaultIfNull(dte.getCodeCount(), 0L) + codeCount);
        declareToEnterpriseMapper.update(dte);

        // 支付的金额大于0的时候才有补贴，否则有可能企业只是在使用免费的二维码
        if (paidAmount.compareTo(BigDecimal.ZERO) > 0) {
            //获取企业所属各级部门id
            DeclareEnterprise declareEnterprise = declareEnterpriseMapper.getById(dte.getFkDeclareEnterpriseId());
            Long declarerId = declareEnterprise.getFkDeclarerId();

            //插入申报官的补贴
            User declarerUser = userService.getByObjectId(declarerId, UserUtils.UserType.declarer);
            subsidyDetailService.insert(SubsidyWithdrawUtils.DetailType.购买二维码, declarerUser.getId(),
                    data.getEnterpriseId(), declareEnterprise.getName(), codeCount, paidAmount,
                    DeclareConfigUtils.getBigDecimal("code.declarer.amount"));

            //插入申报服务处的补贴
            Long organizId = declareEnterprise.getFkOrganizId();
            User organizUser = userService.getByObjectId(organizId, UserUtils.UserType.organiz);
            subsidyDetailService.insert(SubsidyWithdrawUtils.DetailType.购买二维码, organizUser.getId(),
                    data.getEnterpriseId(), declareEnterprise.getName(), codeCount, paidAmount,
                    DeclareConfigUtils.getBigDecimal("code.organiz.amount"));

            //插入省级服务中心的补贴
            Long provenceCenterId = declareEnterprise.getFkProvenceCenterId();
            if (provenceCenterId != null) {
                User provenceUser = userService.getByObjectId(provenceCenterId, UserUtils.UserType.service);
                subsidyDetailService.insert(SubsidyWithdrawUtils.DetailType.购买二维码, provenceUser.getId(),
                        data.getEnterpriseId(), declareEnterprise.getName(), codeCount, paidAmount,
                        DeclareConfigUtils.getBigDecimal("code.service.amount"));
            }
        } else {
            logger.warn("收到一条企业使用免费二维码的信息: {}", data.toString());
        }
    }

    public DeclareToEnterprise getByDeclareEnterpriseId(Long declareEnterpriseId) {
        return declareToEnterpriseMapper.getByDeclareEnterpriseId(declareEnterpriseId);
    }
}