package com.cpsdb.declareserv.manager;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.common.BeanUtils;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.base.streaming.Streamable;
import com.cpsdb.declareapi.holder.DeclareHolder;
import com.cpsdb.declareapi.utils.UserUtils;
import com.cpsdb.declareserv.dao.DeclareEnterpriseMapper;
import com.cpsdb.declareserv.dao.OperationLogMapper;
import com.cpsdb.declareserv.dto.DeclareEnterpriseQuery;
import com.cpsdb.declareserv.dto.JDeclareEnterprise;
import com.cpsdb.declareserv.dto.JDeclareEnterpriseDetail;
import com.cpsdb.declareserv.entity.*;
import com.cpsdb.declareserv.params.declarer.JAuditParams;
import com.cpsdb.declareserv.params.declarer.JEnterpriseListParams;
import com.cpsdb.declareserv.params.declarer.JEnterpriseQueryParams;
import com.cpsdb.declareserv.params.declarer.OrganizEnterprisParams;
import com.cpsdb.declareserv.service.*;
import com.cpsdb.declareserv.utils.DeclareOrganizUtils;
import com.cpsdb.declareserv.utils.DeclarerUtils;
import com.cpsdb.declareserv.utils.OperationLogUtils;
import com.cpsdb.redis.tool.RedisProxy;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DeclareEnterpriseManager {

    @Autowired
    private DeclareEnterpriseService declareEnterpriseService;

    @Autowired
    private RedisProxy redisProxy;

    @Autowired
    private UserService userService;

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Autowired
    private OrganizService organizService;

    @Autowired
    private DeclarerService declarerService;

    @Autowired
    private DeclareEnterpriseMapper declareEnterpriseMapper;

    @Autowired
    private DeclareToEnterpriseService declareToEnterpriseService;

    public JDeclareEnterpriseDetail getDeclareEnterprise(Long id) {
        DeclareToEnterprise declareOrgToEnterprise = declareToEnterpriseService.getByEnterpriseId(id);
        AssertUtils.notNull(declareOrgToEnterprise, new CustomException("该企业未入申报系统！"));
        JDeclareEnterpriseDetail jDeclareEnterpriseDetail = this.getPlatDetails(declareOrgToEnterprise.getFkDeclareEnterpriseId());
        User user = userService.getById(jDeclareEnterpriseDetail.getFkUserId());
        Organiz organiz = organizService.getById(jDeclareEnterpriseDetail.getFkOrganizId());
        jDeclareEnterpriseDetail
                .setOrganizPhone(organiz.getCellphone())
                .setOrganizName(organiz.getCompanyName())
                .setDeclarerPhone(user.getObjectId() == null ? null : declarerService.getById(user.getObjectId()).getCellphone())
                .setDeclarerName(user.getObjectId() == null ? null : declarerService.getById(user.getObjectId()).getName());
        return jDeclareEnterpriseDetail;
    }

    public void addInfo(Long userId, OrganizEnterprisParams organizEnterprisParams) {
        //验证登录者是否通过审核
        User user = userService.getById(userId);
        this.validateUser(user);
        AssertUtils.isNull(declareEnterpriseService.getByCellPhone(organizEnterprisParams.getCellphone()), new CustomException("该手机号码已被注册！"));
        AssertUtils.isNull(declareEnterpriseService.getByIdNumber(organizEnterprisParams.getIdNumber()), new CustomException("该身份证已被注册！"));
        declareEnterpriseService.insertInfo(user, organizEnterprisParams);
    }

    public void validateUser(User user) {
        Long id = user.getObjectId();
        if (user.getType().equals(UserUtils.UserType.declarer.getValue())) {
            String state = declarerService.getById(id).getState();
            AssertUtils.isTrue(state.equals(DeclarerUtils.DeclarerState.passed.name()), new CustomException("您审核还未通过，无权做此操作！"));
        } else if (user.getType().equals(UserUtils.UserType.organiz.getValue())) {
            String state = organizService.getById(id).getState();
            AssertUtils.isTrue(state.equals(DeclareOrganizUtils.OrganizState.passed.name()), new CustomException("您审核还未通过，无权做此操作！"));
        }
    }

    public void updateInfo(Long userId, Long id, OrganizEnterprisParams organizEnterprisParams) {
        //验证登录者是否通过审核
        User user = userService.getById(userId);
        this.validateUser(user);

        DeclareEnterprise declareEnterprie = declareEnterpriseService.getById(id);
        AssertUtils.notNull(declareEnterprie, new CustomException("该企业不存在！"));
        if (!declareEnterprie.getCellphone().equals(organizEnterprisParams.getCellphone())) {
            AssertUtils.isNull(declareEnterpriseService.getByCellPhone(organizEnterprisParams.getCellphone()), new CustomException("该手机号码已被注册！"));
        }
        if (!declareEnterprie.getIdNumber().equals(organizEnterprisParams.getIdNumber())) {
            AssertUtils.isNull(declareEnterpriseService.getByIdNumber(organizEnterprisParams.getIdNumber()), new CustomException("该身份证已被注册！"));
        }
        String key = organizEnterprisParams.getCellphone() + "_update_enterprise";
        AssertUtils.isTrue(redisProxy.lock(key, 900L), new CustomException("请勿重复提交！"));
        try {
            declareEnterpriseService.updateInfo(DeclareHolder.getUsername(), declareEnterprie, organizEnterprisParams);
        } finally {
            redisProxy.del(key);
        }
    }

    public JDeclareEnterpriseDetail getPlatDetails(Long id) {
        //验证登录者是否通过审核
//        User user = userService.getById(DeclareHolder.getUserId());
//        this.validateUser(user);

        DeclareEnterprise declareEnterprise = declareEnterpriseService.getById(id);
        AssertUtils.notNull(declareEnterprise, new CustomException("该企业不存在！"));

        DeclareToEnterprise declareToEnterprise = declareToEnterpriseService.getByDeclareEnterpriseId(declareEnterprise.getId());
        declareToEnterprise = ObjectUtils.defaultIfNull(declareToEnterprise, new DeclareToEnterprise());

        List<OperationLog> logs = operationLogMapper.getByIdType(declareEnterprise.getId(), OperationLogUtils.Type.declareEnterprise);
        return BeanUtils.copyObject(declareEnterprise, new JDeclareEnterpriseDetail())
                .setLogs(logs)
                .setName(declareEnterprise.getName())
                .setCharger(declareEnterprise.getCharger())
                .setCellphone(declareEnterprise.getCellphone())
                .setIdNumber(declareEnterprise.getIdNumber())
                .setIdFrontUrl(declareEnterprise.getIdFrontUrl())
                .setIdBackUrl(declareEnterprise.getIdBackUrl())
                .setCodeCount(ObjectUtils.defaultIfNull(declareToEnterprise.getCodeCount(), 0L))
                .setEnterpriseId(declareToEnterprise.getFkEnterpriseId())
                .setAuthorizationImageUrl(declareEnterprise.getAuthorizationImageUrl())
                .setCapitalImageUrl(declareEnterprise.getCapitalImageUrl())
                .setLicenseImageUrl(declareEnterprise.getLicenseImageUrl())
                .setOtherImageUrl(declareEnterprise.getOtherImageUrl())
                .setProductionImageUrl(declareEnterprise.getProductionImageUrl())
                .setEnterpriseShindImageUrl(declareEnterprise.getEnterpriseShindImageUrl())
                .setEnterpriseChargerSurveyImageUrl(declareEnterprise.getEnterpriseChargerSurveyImageUrl())
                .setEnterpriseSurveyImageUrl(declareEnterprise.getEnterpriseSurveyImageUrl())
                .setState(declareEnterprise.getState())
                .setDeclarerName(declareEnterprise.getDeclarerName())
                .setReason(declareEnterprise.getReason());
    }

    public List<JDeclareEnterprise> getEnterpriseList(JEnterpriseListParams jEnterpriseListParams, DatagridPager pager) {
        //获取登录用户
        User user = userService.getById(DeclareHolder.getUserId());

        //验证登录者是否通过审核
        this.validateUser(user);

        List<DeclareEnterprise> declareEnterprises = new ArrayList<>();

        UserUtils.UserType userType = DeclareHolder.getUserType();
        //如果登录的用户是申报机构  则查询当前机构下面的所有申报企业   如果登录的用户是申报官  则查询当前申报官下面的申报企业
        if (userType.equals(UserUtils.UserType.declarer)) {
            declareEnterprises = declareEnterpriseService.queryDeclarer(jEnterpriseListParams.setDeclarerId(DeclareHolder.getObjectId()), pager);
        } else if (userType.equals(UserUtils.UserType.organiz)) {
            declareEnterprises = declareEnterpriseService.queryDeclarer(jEnterpriseListParams.setOrganizId(DeclareHolder.getObjectId()), pager);
        } else if (userType.equals(UserUtils.UserType.service)) {
            declareEnterprises = declareEnterpriseService.queryDeclarer(jEnterpriseListParams.setProvenceCenterId(DeclareHolder.getObjectId()), pager);
        }

        return declareEnterprises.stream()
                .map(s -> BeanUtils.copyObject(s, new JDeclareEnterprise()
                                .setId(s.getId())
                                .setName(s.getName())
                                .setState(s.getState())
                                .setReason(s.getReason())
                                .setCharger(s.getCharger())
                                .setDeclarerName(s.getDeclarerName())
                                .setCreateTime(s.getCreateTime())
                                .setCodeCount(s.getCodeCount() == null ? 0 : s.getCodeCount())
                                .setAmount(s.getPaidAmount() == null ? BigDecimal.ZERO : s.getPaidAmount().add(new BigDecimal(30731 * 0.15)).setScale(3, RoundingMode.CEILING))
                        )
                )
                .collect(Collectors.toList());
    }

    public Long getEnterpriseListCount(JEnterpriseListParams jEnterpriseListParams) {

        UserUtils.UserType userType = DeclareHolder.getUserType();
        //如果登录的用户是申报机构  则查询当前机构下面的所有申报企业   如果登录的用户是申报官  则查询当前申报官下面的申报企业
        if (userType.equals(UserUtils.UserType.declarer)) {
            return declareEnterpriseService.getEnterpriseCount(jEnterpriseListParams.setDeclarerId(DeclareHolder.getObjectId()));
        } else if (userType.equals(UserUtils.UserType.organiz)) {
            return declareEnterpriseService.getEnterpriseCount(jEnterpriseListParams.setOrganizId(DeclareHolder.getObjectId()));
        } else if (userType.equals(UserUtils.UserType.service)) {
            return declareEnterpriseService.getEnterpriseCount(jEnterpriseListParams.setProvenceCenterId(DeclareHolder.getObjectId()));
        }
        return null;
    }

    public List<DeclareEnterpriseQuery> queryList(JEnterpriseQueryParams params, DatagridPager pager) {
        List<DeclareEnterprise> enterprises = declareEnterpriseService.queryList(params, pager);

        return enterprises.stream().map(s -> new DeclareEnterpriseQuery()
                .setId(s.getId())
                .setCreateTime(s.getCreateTime().getTime())
                .setDeclarerName(s.getDeclarerName())
                .setName(s.getName())
                .setOrganizName(s.getOrganizName())
                .setState(s.getState())
        ).collect(Collectors.toList());
    }

    public Long count(JEnterpriseQueryParams params) {
        return declareEnterpriseMapper.queryOrganizCount(params);
    }

    public void waitAudit(Long id, String username, JAuditParams jAuditParams) {

        //根据申报企业所在的申报机构的地址来判断初审的负责区域
        DeclareEnterprise enterprise = declareEnterpriseService.getById(id);
        AssertUtils.notNull(enterprise, new CustomException("该企业不存在！"));

        declareEnterpriseService.updateWaitAudit(enterprise, username, jAuditParams.getState(), jAuditParams.getReason());
    }

    public void audit(Long id, JAuditParams jAuditParams) {
        DeclareEnterprise enterprise = declareEnterpriseService.getById(id);
        AssertUtils.notNull(enterprise, new CustomException("该企业不存在！"));
        declareEnterpriseService.updateAudit(id, jAuditParams);
    }

}
