package com.cpsdb.declareserv.service;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.common.CPSStringUtils;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.baseservapi.holder.PlatformUserHolder;
import com.cpsdb.baseservapi.utils.AreaUtils;
import com.cpsdb.declareapi.utils.DeclareTokenUtils;
import com.cpsdb.declareapi.utils.UserUtils;
import com.cpsdb.declareserv.dao.DeclarerMapper;
import com.cpsdb.declareserv.dto.JAppLogin;
import com.cpsdb.declareserv.entity.*;
import com.cpsdb.declareserv.manager.OrderManager;
import com.cpsdb.declareserv.params.declarer.*;
import com.cpsdb.declareserv.tools.ThreadUtils;
import com.cpsdb.declareserv.utils.*;
import com.cpsdb.redis.annotation.Lock;
import com.cpsdb.thirdapi.api.JuHeApi;
import com.cpsdb.thirdapi.utils.ModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author 李银
 * @ClassName DeclarerService
 * @Description
 * @date 2018-09-20 10:42:40
 */
@Service
public class DeclarerService extends DeclarerFsmService {

    @Autowired
    private DeclarerMapper declarerMapper;

    @Autowired
    private OrderManager orderManager;

    @Autowired
    private UserService userService;

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private DeclareEnterpriseService declareEnterpriseService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private JuHeApi juHeApi;

    @Autowired
    private SubsidyDetailService subsidyDetailService;

    @Autowired
    private ProvenceCenterService provenceCenterService;

    public void insert(Declarer declarer) {
        declarerMapper.insert(declarer);
    }

    public void update(Declarer declarer) {
        declarerMapper.update(declarer);
    }

    public Declarer getById(Long id) {
        return declarerMapper.getById(id);
    }

    public Declarer getByIdNumber(String idNumber) {
        return declarerMapper.getByIdNumber(idNumber);
    }

    public void updateAuthen(Declarer declarer, JDeclarerAuthenParams params, DeclarerSignParams signParams) {
        // 获取该申报官区域的省级服务中心，如果还没有成立则取默认的服务中心
        Long provenceId = Optional.ofNullable(provenceCenterService.getByAreaCode(params.getAreaCode().substring(0, 2) + "0000"))
                .map(ProvenceCenter::getId)
                .orElse(null);

        declarer.setSurveyImageUrl(params.getSurveyImageUrl())
                .setLetterImageUrl(params.getLetterImageUrl())
                .setSex(signParams.getSex())
                .setFkProvenceCenterId(provenceId)
                .setEducation(signParams.getEducation())
                .setAreaCode(params.getAreaCode())
                .setAddress(signParams.getAddress())
                .setNation(signParams.getVolk())
                .setGraduate(signParams.getUniversity())
                .setPortrait(params.getPortrait())
                .setIdFrontUrl(params.getIdFrontUrl())
                .setIdBackUrl(params.getIdBackUrl());
        this.update(declarer);
    }

    public Declarer getByPhone(String cellphone) {
        return declarerMapper.getByPhone(cellphone);
    }

    @Lock(key = "create_declarer", argsIndex = 0)
    public JAppLogin insertInfo(Organiz organiz, String name, String cellphone,
                                String idNumber, String recommend, String password) throws Exception {
        Declarer declarer = new Declarer()
                .setName(name)
                .setCellphone(cellphone)
                .setFkOrganizId(organiz.getId())
                .setIdNumber(idNumber)
                .setScore(-1L)
                .setRegistMethod(DeclarerUtils.RegistMethod.publics.name())
                .setIspass(false);

        this.insert(declarer);

        // 创建订单信息
        orderManager.create(declarer.getId(), name);

        // 插入用户
        User user = userService.insert(cellphone, password, UserUtils.UserType.declarer, declarer.getId());

        // 如果填有推荐码  我的推荐就是填写的推荐  我的父推荐就是填写的推荐的推荐
        Recommend r = null;
        if (CPSStringUtils.isNotBlank(recommend)) {
            User recommendUser = this.userService.getByUsername(recommend);
            r = recommendService.insert(recommendUser, user);
        }

        this.processEvent(declarer.getId(), DeclarerUtils.DeclarerEvent.create, cellphone, null);

        return new JAppLogin()
                .setState(declarer.getState())
                .setAmount(DeclareConfigUtils.getBigDecimal("pay.declarer.amount"))
                .setSn(SnUtils.getDeclarerSn(declarer.getId().toString()))
                .setOrganiz(organiz.getName())
                .setType(user.getType())
                .setRecommendId(user.getUsername())
                .setRecommendName(r == null ? null : userService.getById(r.getRecommendId()).getUsername())
                .setName(declarer.getName())
                .setCode(DeclarerUtils.declarerQrcode(declarer.getId()))
                .setPortrait(declarer.getPortrait())
                .setToken(DeclareTokenUtils.encrypt(user.getType(), user.getId(), user.getObjectId(), user.getUsername()));
    }

    public List<Declarer> getByOrganizId(JDeclarerQueryParams params, Long objectId, DatagridPager pager) {
        return declarerMapper.getByOrganizId(params, objectId, pager);
    }

    public List<Declarer> getByServiceId(JDeclarerQueryParams params, Long objectId, DatagridPager pager) {
        return declarerMapper.getByServiceId(params, objectId, pager);
    }

    public void deleteDeclarer(Declarer declarer, String operator, String reason) {
        this.processEvent(declarer.getId(), DeclarerUtils.DeclarerEvent.delete, operator, reason);

    }

    @Override
    protected void onStateChanged(Long declarerId, String stateFrom, DeclarerUtils.DeclarerEvent event, String stateTo, String mark) {
        Declarer declarer = this.getById(declarerId);
        if (DeclarerUtils.DeclarerState.waitAudit.name().equals(stateTo)) {
            declarer.setReason(null);
            this.update(declarer);
        } else if (DeclarerUtils.DeclarerState.waitPending.name().equals(stateTo)) {

            declarer.setReason(null);
            this.update(declarer);
        } else if (DeclarerUtils.DeclarerState.unpass.name().equals(stateTo)) {
            this.update(declarer.setReason(mark));

            User user = userService.getByObjectId(declarerId, UserUtils.UserType.declarer);
            recommendService.updateTargetState(user.getId(), RecommendUtils.TargetState.unpass);

            ThreadUtils.runThread(declarer.getCellphone(), ModeType.DECLARER_UNPASS, mark);
        } else if (DeclarerUtils.DeclarerState.passed.name().equals(stateTo)) {

            declarer.setReason(null);
            this.update(declarer);

            User user = userService.getByObjectId(declarerId, UserUtils.UserType.declarer);
            // 更新推荐
            recommendService.updateTargetState(user.getId(), RecommendUtils.TargetState.passed);

            // 插入注册补贴
            this.insertRegisterSubsidy(declarer);

            // 发送审核通过短信
            ThreadUtils.runThread(declarer.getCellphone(), ModeType.DECLARER_PASS, "", declarer.getName(), "", mark);

        } else if (DeclarerUtils.DeclarerState.delete.name().equals(stateTo)) {
            this.update(declarer);
        } else if (DeclarerUtils.DeclarerState.deleted.name().equals(stateTo)) {
            this.update(declarer);
        }

    }

    /**
     * 申报官注册补贴
     * 所属服务处 和服务中心各自拿相应补贴，如果改申报官对应的省级服务中心不存在，则没有。
     *
     * @param declarer
     */
    private void insertRegisterSubsidy(Declarer declarer) {

        if (declarer.getRegistMethod().equals(DeclarerUtils.RegistMethod.publics.name())) {
            BigDecimal amount = DeclareConfigUtils.getBigDecimal("pay.declarer.amount");
            // 插入服务处获取的补贴
            Long organizId = declarer.getFkOrganizId();
            Long organizUserId = userService.getByObjectId(organizId, UserUtils.UserType.organiz).getId();
            BigDecimal organizPercent = DeclareConfigUtils.getBigDecimal("declarer.organiz.percent");
            subsidyDetailService.insert(SubsidyWithdrawUtils.DetailType.申报官注册, organizUserId, declarer.getId(),
                    declarer.getName(), 1, amount, organizPercent);

            // 插入省级服务中心获取的补贴
            Long provenceCenterId = declarer.getFkProvenceCenterId();
            if (provenceCenterId != null) {
                BigDecimal provenceCenterPercent = DeclareConfigUtils.getBigDecimal("declarer.service.percent");
                Long provenceCenterUserId = userService.getByObjectId(provenceCenterId, UserUtils.UserType.service).getId();
                subsidyDetailService.insert(SubsidyWithdrawUtils.DetailType.申报官注册, provenceCenterUserId,
                        declarer.getId(), declarer.getName(), 1, amount, provenceCenterPercent);
            }
        }
    }

    public List<Declarer> getPublicDeclarerList(JDeclarerPublicParams params, DatagridPager pager) {
        return declarerMapper.getPublicDeclarerList(params, pager);
    }

    public Long getPublicDeclarerCount(JDeclarerPublicParams params) {
        return declarerMapper.getPublicDeclarerCount(params);
    }

    public void updateDeclarerScore(Declarer declarer, Integer score, Boolean ispass) {
        declarer.setScore(score.longValue());
        declarer.setIspass(ispass);
        this.update(declarer);
    }

    public List<Declarer> query(JDeclarerQueryParams params, DatagridPager pager) {
        return declarerMapper.query(params, pager);
    }

    public Long count(JDeclarerQueryParams params) {
        return declarerMapper.count(params);
    }

    public void validatyPhone(String cellphone) {
        declarerMapper.validatyPhone(cellphone);
    }

    public void validatyIdNumber(String idNumber) {
        declarerMapper.validatyIdNumber(idNumber);
    }

    public Long getByOrganizCount(JDeclarerQueryParams params, Long objectId) {
        return declarerMapper.getByOrganizCount(params, objectId);
    }

    public Long getByServiceCount(JDeclarerQueryParams params, Long objectId) {
        return declarerMapper.getByServiceCount(params, objectId);
    }

    /**
     * 修改申报官的服务中心归属
     * 在areaCode下的申报官全部归宿到provenceId
     */
    public void updateProvence(String areaCode, Long provenceId) {
        // 1， 修改申报官本身的服务中心归属 和 申报官所申报的企业归属
        this.declarerMapper.updateProvence(AreaUtils.simplizedCode(areaCode), provenceId);
    }

    /**
     * 修改申报官的服务处归属,
     * 这里不修改服务中心的归属
     */
    public void updateOrganiz(Long declarerId, Long organizId) {
        // 1， 修改申报官本身的服务处归属
        this.declarerMapper.updateOrganiz(declarerId, organizId);

        // 2，修改申报官所申报的企业归属；
        this.declareEnterpriseService.updateOrganiz(declarerId, organizId);
    }

    /**
     * 申报服务处和省级服务中心注册申报官
     *
     * @param organiz
     * @param params
     */
    @Lock(key = "create_declarer" ,argsIndex = 0)
    public void createByOrganiz(Organiz organiz, Long serviceId, JOrganizRegistParams params, String cellphone) {

        Declarer declarer = new Declarer()
                .setName(params.getName())
                .setCellphone(params.getCellphone())
                .setFkOrganizId(organiz.getId())
                .setFkProvenceCenterId(serviceId)
                .setIdNumber(params.getIdnumber())
                .setScore(-1L)
                .setIspass(false)
                .setRegistMethod(DeclarerUtils.RegistMethod.other.name())
                .setAreaCode(organiz.getAreaCode());
        this.insert(declarer);

        // 创建订单信息
        orderService.createByOrganiz(declarer.getId());

        // 插入用户
        userService.insert(params.getCellphone(), params.getPassword(), UserUtils.UserType.declarer, declarer.getId());
        this.processEvent(declarer.getId(), DeclarerUtils.DeclarerEvent.pay, organiz.getName(), null);

        juHeApi.sendSms(cellphone, ModeType.REGISTSUCCESS, declarer.getName());
    }

    public Declarer getByRegist(Long organizId, Long serviceId, String registMethod) {
        return declarerMapper.getByRegist(organizId, serviceId, registMethod);
    }

    public void updateFinanace(Long id, Boolean state, String reason, String paymentUrl) {
        Declarer declarer = this.getById(id);
        declarer.setPaymentUrl(paymentUrl);
        this.update(declarer);
        if (state) {
            this.processEvent(id, DeclarerUtils.DeclarerEvent.confirm, PlatformUserHolder.getUsername(), reason);
        } else {
            this.processEvent(id, DeclarerUtils.DeclarerEvent.reject, PlatformUserHolder.getUsername(), reason);

            // 将订单修改为未支付
            Order order = orderService.getByDeclarerId(declarer.getId(), null);
            AssertUtils.notNull(order, new CustomException("订单不存在"));
            this.orderService.update(order.setState(DeclarerUtils.PayState.wait.name()));
        }

    }
}