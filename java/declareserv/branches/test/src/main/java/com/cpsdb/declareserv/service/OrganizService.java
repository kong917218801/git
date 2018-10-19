package com.cpsdb.declareserv.service;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.base.pager.Pageable;
import com.cpsdb.baseservapi.api.AreaApi;
import com.cpsdb.baseservapi.holder.PlatformUserHolder;
import com.cpsdb.baseservapi.utils.AreaUtils;
import com.cpsdb.declareapi.utils.DeclareTokenUtils;
import com.cpsdb.declareapi.utils.UserUtils;
import com.cpsdb.declareserv.dao.OrganizMapper;
import com.cpsdb.declareserv.dto.JOrganizFreedom;
import com.cpsdb.declareserv.dto.JOrganizList;
import com.cpsdb.declareserv.dto.JOrganizQuery;
import com.cpsdb.declareserv.entity.Organiz;
import com.cpsdb.declareserv.entity.ProvenceCenter;
import com.cpsdb.declareserv.entity.User;
import com.cpsdb.declareserv.params.organiz.*;
import com.cpsdb.declareserv.tools.ThreadUtils;
import com.cpsdb.declareserv.utils.DeclareConfigUtils;
import com.cpsdb.declareserv.utils.DeclareOrganizUtils.OrganizState;
import com.cpsdb.declareserv.utils.OrganizUtils;
import com.cpsdb.declareserv.utils.OrganizUtils.OrganizEvent;
import com.cpsdb.declareserv.utils.RecommendUtils;
import com.cpsdb.declareserv.utils.SnUtils;
import com.cpsdb.redis.annotation.Lock;
import com.cpsdb.thirdapi.api.JuHeApi;
import com.cpsdb.thirdapi.utils.ModeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 李银
 * @ClassName OrganizService
 * @Description
 * @date 2018-09-20 10:42:40
 */
@Service
public class OrganizService extends OrganizFsmService {

    @Autowired
    private OrganizMapper organizMapper;

    @Autowired
    private OrganizAddressService organizAddressService;

    @Autowired
    private ProvenceCenterService provenceCenterService;

    @Autowired
    private UserService userService;

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private JuHeApi juHeApi;

    @Autowired
    private AreaApi areaApi;


    public Long insert(Organiz organiz) {
        return organizMapper.insert(organiz);
    }

    public void update(Organiz organiz) {
        organizMapper.update(organiz);
    }

    public Organiz getById(Long id) {
        return organizMapper.getById(id);
    }

    public Organiz getByPhone(String cellphone) {
        return organizMapper.getByCellphone(cellphone);
    }

    /**
     * 申报服务处列表->总后台
     *
     * @param params
     * @param pager
     * @return
     */
    public List<JOrganizQuery> query(JOrganizQueryParams params, Pageable pager) {
        List<Organiz> organizs = this.organizMapper.query(params, pager);

        return organizs.stream()
                .map(s -> new JOrganizQuery()
                        .setId(s.getId())
                        .setSn(SnUtils.getOrganizSn(s.getId().toString()))
                        .setCellphone(s.getCellphone())
                        .setCreateTime(s.getCreateTime().getTime())
                        .setName(s.getName())
                        .setCompanyName(s.getCompanyName())
                        .setProvenceName(s.getProvenceName())
                        .setState(s.getState())
                )
                .collect(Collectors.toList());
    }

    public Long count(JOrganizQueryParams params) {
        return organizMapper.count(params);
    }

    public Integer getOrganizCount(JQueryList jQueryList) {
        return organizMapper.getOrganizCount(jQueryList);
    }

    /**
     * 申报服务处基本信息完善->有服务区域 更新地址
     *
     * @param organiz
     * @param registParams
     * @return
     */
    @Lock(key = "organiz", argsIndex = 0)
    public String updateRegistInfo(Organiz organiz, RegistParams registParams) {
        AssertUtils.notNull(organiz, new CustomException("该机构不存在"));
        organiz.setChargerImageUrl(registParams.getChargerImageUrl())
                .setSurveyImageUrl(registParams.getSurveyImageUrl())
                .setOtherImageUrl(registParams.getOtherImageUrl())
                .setLetterImageUrl(registParams.getLetterImageUrl())
                .setCommerceImageUrl(registParams.getCommerceImageUrl())
                .setRegistTime(new Date())
                .setChargerSurveyImageUrl(registParams.getChargerSurveyImageUrl());
        this.update(organiz);
        this.processEvent(organiz.getId(), OrganizEvent.submit, organiz.getCellphone(), null);
        return this.getById(organiz.getId()).getState();
    }

    public List<Organiz> getOrganizlist(JQueryList jQueryList, DatagridPager pager) {
        return organizMapper.getOrganizlist(jQueryList, pager);
    }

    public Integer getfreedomCount(JQueryFreedomList jQueryFreedomList) {
        JQueryList jQueryList = new JQueryList()
                .setName(jQueryFreedomList.getName())
                .setAreaCode(jQueryFreedomList.getAreaCode());
        return organizMapper.getOrganizCount(jQueryList);
    }


    /**
     * 获取申报服务处列表（提供给公共平台）
     *
     * @param jQueryFreedomList
     * @param pager
     * @return
     */
    public List<JOrganizFreedom> getFreedomlist(JQueryFreedomList jQueryFreedomList, DatagridPager pager) {
        JQueryList jQueryList = new JQueryList()
                .setName(jQueryFreedomList.getName())
                .setAreaCode(jQueryFreedomList.getAreaCode())
                .setState(OrganizState.passed.name());
        /*获取服务处列表*/
        List<Organiz> organizList = organizMapper.getOrganizlist(jQueryList, pager);

        return organizList.stream()
                .map(s -> new JOrganizFreedom()
                        .setSn(SnUtils.getOrganizSn(s.getId().toString()))
                        .setId(s.getId())
                        .setName(s.getName())
                        .setCompanyName(s.getCompanyName())
                        .setProvenceName(s.getProvenceName())
                        .setAddress((s.getLiveCode() == null) ? null : areaApi.getByCode(s.getLiveCode()).getArea() + s.getAddress())
                )
                .collect(Collectors.toList());
    }


    @Override
    protected void onStateChanged(Long id, String stateFrom, OrganizEvent event, String stateTo, String mark) {
        Organiz organiz = this.getById(id);
        if (OrganizState.baseUnPass.name().equals(stateTo)) {
            //更新审核不通过信息
            this.update(organiz.setReason(mark));

            // 更新推荐状态
            User user = userService.getByObjectId(organiz.getId(), UserUtils.UserType.organiz);
            recommendService.updateTargetState(user.getId(), RecommendUtils.TargetState.unpass);

            //基本信息审核不通过发短信通知
            ThreadUtils.runThread(organiz.getCellphone(), ModeType.ORGANIZ_BASE_UNPASS, mark);

        } else if (OrganizEvent.confirm == event && OrganizState.registWaitSubmit.name().equals(stateTo)) {
            this.update(organiz.setReason(null));
            //基本资料审核通过发短信通知
            ThreadUtils.runThread(organiz.getCellphone(), ModeType.ORGANIZ_BASE_PASS, organiz.getName());
        } else if (OrganizEvent.confirm == event && OrganizState.passed.name().equals(stateTo)) {
            this.update(organiz.setReason(null));

            //更新用户登录状态
            this.userService.updateState(organiz.getId(), UserUtils.UserType.organiz, UserUtils.State.using);
            // 更新推荐状态
            User user = userService.getByObjectId(organiz.getId(), UserUtils.UserType.organiz);
            recommendService.updateTargetState(user.getId(), RecommendUtils.TargetState.passed);

            //注册资料审核通过发短信通知
            ThreadUtils.runThread(organiz.getCellphone(), ModeType.ORGANIZ_REGIST_PASS, organiz.getName());

        } else if (OrganizState.registUnPass.name().equals(stateTo)) {
            //更新审核不通过信息
            this.update(organiz.setReason(mark));
            // 更新推荐状态
            User user = userService.getByObjectId(organiz.getId(), UserUtils.UserType.organiz);
            recommendService.updateTargetState(user.getId(), RecommendUtils.TargetState.unpass);
            ThreadUtils.runThread(organiz.getCellphone(), ModeType.ORGANIZ_REGIST_UNPASS, mark);
        } else if (OrganizState.delete.name().equals(stateTo)) {

            this.userService.updateState(organiz.getId(), UserUtils.UserType.organiz, UserUtils.State.useless);
        }

    }

    /**
     * 修改服务处绑定省级服务中心
     *
     * @param areaCode
     * @param provenceId
     * @return
     */
    public void updateProvenceId(String areaCode, Long provenceId) {
        organizMapper.updateProvenceId(AreaUtils.simplizedCode(areaCode), provenceId);
    }


    /**
     * 删除申报服务处
     *
     * @param organizId
     * @return
     */
    public void delete(String username, Long organizId, String reason) {
        this.processEvent(organizId, OrganizEvent.delete, username, reason);
    }

    public JOrganizLogin create(String cellphone, String password, String code) throws Exception {
        /*验证输入的手机短信验证码*/
        juHeApi.vilidaty(ModeType.REGISET, cellphone, code);

        Organiz organiz = new Organiz().setCellphone(cellphone);
        this.insert(organiz);
        User user = userService.insert(cellphone, password, UserUtils.UserType.organiz, organiz.getId());
        this.processEvent(organiz.getId(), OrganizUtils.OrganizEvent.create, cellphone, null);
        return new JOrganizLogin()
                .setUsername(user.getUsername())
                .setToken(DeclareTokenUtils.encrypt(user.getType(), user.getId(), user.getObjectId(), user.getUsername()));
    }

    public List<JOrganizList> organizName(String name) {
        List<Organiz> organizs = organizMapper.organizName(name);
        return organizs.stream().map(s -> new JOrganizList()
                .setId(s.getId())
                .setName(s.getName())
        ).collect(Collectors.toList());
    }

    private void _updateBaseInfo(Organiz organiz, BaseInfoParams params) {
        // 插入基本信息
        organiz.setName(params.getName())
                .setIdNumber(params.getIdNumber())
                .setIdBackUrl(params.getIdBackUrl())
                .setIdFrontUrl(params.getIdFrontUrl())
                .setBaseTime(new Date())
                .setCompanyName(params.getCompanyName());
        /*获取申请区域是否有省级服务中心 */
        // baseInfoParams.getApplyAddress()转化为省级的code，再去查；
        // 如省级为510000
        // 我申请的是： 511011， 那么先把 511011 后四位改成0： 510000再去查
        if (OrganizUtils.OrganizState.baseWaitSubmit.name().equals(organiz.getState())) {
            Long provenceId = Optional.ofNullable(provenceCenterService.getByAreaCode(params.getApplyAddress().substring(0, 2) + "0000"))
                    .map(ProvenceCenter::getId)
                    .orElse(null);
            organiz.setFkProvenceCenterId(provenceId);
        }
        this.update(organiz);
    }

    public void createBaseInfo(Organiz organiz, BaseInfoParams params) {
        // 更新基本信息
        this._updateBaseInfo(organiz, params);

        // 如果有推荐，绑定推荐关系
        if (StringUtils.isNotBlank(params.getRecommend())) {
            recommendService.insert(params.getRecommend(), organiz.getCellphone());
        }

        //插入申请区域
        organizAddressService.insertApplyAddress(organiz.getId(), params.getApplyAddress(), params.getAddress());

        // 插入常驻地址
        organizAddressService.insertOrUpdateLiveAddress(organiz.getId(), params.getLiveAddress(), params.getAddress());

        // 执行状态机
        this.processEvent(organiz.getId(), OrganizEvent.submit, organiz.getCellphone(), null);
    }

    /**
     * 申报服务处修改基本信息（存入数据库）
     *
     * @param organiz 当前登录的服务处id
     * @param params  登记信息实例对象
     * @return
     */
    public void updateBaseInfo(Organiz organiz, BaseInfoParams params) {
        // 更新几个可以更新的基本字段
        this._updateBaseInfo(organiz, params);

        // 修改常驻地址
        organizAddressService.insertOrUpdateLiveAddress(organiz.getId(), params.getLiveAddress(), params.getAddress());

        // 执行状态机
        this.processEvent(organiz.getId(), OrganizEvent.submit, organiz.getCellphone(), null);
    }

    public List<Organiz> getByIds(Set<Long> organizIds) {
        return organizMapper.getByIdS(organizIds);
    }


    /**
     * 支付凭证审核
     * @param id       当前登录的服务处id
     * @param state    状态
     * @param organiz  登记信息实例对象
     * @param reason   是否通过理由
     * @param paymentUrl 支付凭证
     * @return
     */
    public void updateFinanceAudit(Organiz organiz,Long id, Boolean state, String reason,String paymentUrl) {
        if (state) {
            this.update(organiz.setPaymentUrl(paymentUrl));
            this.processEvent(id, OrganizUtils.OrganizEvent.confirm, PlatformUserHolder.getUsername(), reason);
            return;
        }
        throw new CustomException("could not happened here!");
    }


}