package com.cpsdb.declareserv.manager;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.common.BeanUtils;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.baseservapi.api.AreaApi;
import com.cpsdb.baseservapi.dto.ApiArea;
import com.cpsdb.baseservapi.holder.PlatformUserHolder;
import com.cpsdb.declareapi.holder.DeclareHolder;
import com.cpsdb.declareapi.utils.UserUtils;
import com.cpsdb.declareserv.dao.OperationLogMapper;
import com.cpsdb.declareserv.dao.OrganizMapper;
import com.cpsdb.declareserv.dto.JDeclareOrganiz;
import com.cpsdb.declareserv.dto.JOrganiz;
import com.cpsdb.declareserv.dto.JOrganizEssential;
import com.cpsdb.declareserv.dto.JOrganizRegister;
import com.cpsdb.declareserv.entity.OperationLog;
import com.cpsdb.declareserv.entity.Organiz;
import com.cpsdb.declareserv.entity.OrganizAddress;
import com.cpsdb.declareserv.entity.User;
import com.cpsdb.declareserv.params.organiz.BaseInfoParams;
import com.cpsdb.declareserv.params.organiz.JQueryList;
import com.cpsdb.declareserv.params.organiz.RegistParams;
import com.cpsdb.declareserv.service.*;
import com.cpsdb.declareserv.utils.OperationLogUtils;
import com.cpsdb.declareserv.utils.OrganizUtils;
import com.cpsdb.declareserv.utils.SnUtils;
import com.cpsdb.redis.annotation.Lock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrganizManager {
    @Autowired
    private OrganizService organizService;

    @Autowired
    private OrganizAddressService organizAddressService;

    @Autowired
    private AreaApi areaApi;

    @Autowired
    private OrganizMapper organizMapper;

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Autowired
    private DeclarerService declarerService;

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private UserService userService;

    @Lock(key = "organiz", argsIndex = 0)
    public void baseInfo(Long organizId, BaseInfoParams params) {

        Organiz organiz = organizService.getById(organizId);
        if(params.getIdNumber()!=null){
            if (!params.getIdNumber().equals(organiz.getIdNumber())) {
                AssertUtils.isNull(organizMapper.getByIdNumber(params.getIdNumber()), new CustomException("身份证号码已存在！"));
                AssertUtils.isNull(declarerService.getByIdNumber(params.getIdNumber()), new CustomException("身份证号码已存在！"));
            }
        }
        // 如果当前状态是基本信息待填写，则新增基本信息，否则为修改基本信息
        if(OrganizUtils.OrganizState.baseWaitSubmit.name().equals(organiz.getState())) {
            // 新增
            this.organizService.createBaseInfo(organiz, params);
        } else {
            // 修改
            this.organizService.updateBaseInfo(organiz, params);
        }
    }

    /**
     * 总后台审核时获取申报服务处信息
     * @param organizId
     * @return
     */
    public JOrganiz getBaseInfo(Long organizId) {
        Organiz organiz = organizService.getById(organizId);
        AssertUtils.notNull(organiz, new CustomException("申报机构不存在！"));

        OrganizAddress liveAddress = organizAddressService.getAddress(organizId, OrganizUtils.AdressType.liveAdress);
        OrganizAddress applyAddress = organizAddressService.getAddress(organizId, OrganizUtils.AdressType.declareAdress);

        /*居住信息解析：四川省-成都市-双流区-海港广场*/
        String liveAddressCode = liveAddress.getAreaCode();

        /*申请地址信息解析*/
        String applyAddressCode = applyAddress.getAreaCode();

        //操作历史
        List<OperationLog> logs = operationLogMapper.getByIdType(organizId, OperationLogUtils.Type.organiz);
        User me = userService.getByObjectId(organizId, UserUtils.UserType.organiz);

        return BeanUtils.copyObject(organiz, new JOrganiz())
                .setAddress(liveAddress.getAddress())
                .setChargerImageUrl(organiz.getChargerImageUrl())
                .setChargerSurveyImageUrl(organiz.getChargerSurveyImageUrl())
                .setCommerceImageUrl(organiz.getCommerceImageUrl())

                //常驻地址 给前台页面返回3个具体的省市区code,便于修改时候的回显
                .setLiveAddress(liveAddressCode)
                //申请地址 给前台页面返回3个具体的省市区code,便于修改时候的回显
                .setApplyAddress(applyAddressCode)
                //推荐者账号
                .setRecommend(recommendService.getUserName(me.getId()))
                .setCompanyName(organiz.getCompanyName())
                .setLogs(logs)
                .setBaseTime(organiz.getBaseTime())
                .setRegistTime(organiz.getRegistTime())
                .setReason(organiz.getReason());
    }

    /**
     * 基本信息审核不通过时修改基本信息
     * @param organizId
     * @return
     */
    public JOrganizEssential getEssential(Long organizId){
        Organiz organiz = organizService.getById(organizId);
        AssertUtils.notNull(organiz, new CustomException("申报机构不存在！"));
        AssertUtils.isTrue(organiz.getState().equals(OrganizUtils.OrganizState.baseUnPass.name()), new CustomException("暂无详情！"));

        OrganizAddress liveAddress = organizAddressService.getAddress(organizId, OrganizUtils.AdressType.liveAdress);
        OrganizAddress applyAddress = organizAddressService.getAddress(organizId, OrganizUtils.AdressType.declareAdress);

        /*居住信息解析：四川省-成都市-双流区-海港广场*/
        String liveAddressCode = liveAddress.getAreaCode();

        /*申请地址信息解析*/
        String applyAddressCode = applyAddress.getAreaCode();

        User me = userService.getByObjectId(organizId, UserUtils.UserType.organiz);

        return BeanUtils.copyObject(organiz, new JOrganizEssential())
                .setAddress(liveAddress.getAddress())
                //常驻地址code
                .setLiveAddress(liveAddressCode)
                //申请地址code
                .setApplyAddress(applyAddressCode)
                //推荐者账号
                .setRecommend(recommendService.getUserName(me.getId()))
                .setCompanyName(organiz.getCompanyName())
                .setReason(organiz.getReason());
        }


    /**
     * 登记信息审核不通过时修改登记信息
     * @param organizId
     * @return
     */
    public JOrganizRegister getRegister(Long organizId){
        Organiz organiz = organizService.getById(organizId);
        AssertUtils.notNull(organiz, new CustomException("申报机构不存在！"));
        AssertUtils.isTrue(organiz.getState().equals(OrganizUtils.OrganizState.registUnPass.name()), new CustomException("暂无详情！"));

        return BeanUtils.copyObject(organiz, new JOrganizRegister())
                .setChargerImageUrl(organiz.getChargerImageUrl())
                .setChargerSurveyImageUrl(organiz.getChargerSurveyImageUrl())
                .setCommerceImageUrl(organiz.getCommerceImageUrl())
                .setReason(organiz.getReason());
    }



    /**
     * 支付凭证审核
     * @param id     当前登录的服务处id
     * @param state  登记信息实例对象
     * @param reason 是否通过理由
     * @param paymentUrl 支付凭证
     * @return
     */
    public void financeAudit(Long id, Boolean state, String reason,String paymentUrl) {
        Organiz organiz = this.organizMapper.getById(id);
        organizService.updateFinanceAudit(organiz,id, state, reason, paymentUrl);
    }

    /**
     * 申报服务处信息审核
     *
     * @param id     当前登录的服务处id
     * @param state  登记信息实例对象
     * @param reason 是否通过理由
     * @return
     */
    public void audit(Long id, Boolean state, String reason) {
        if (state) {
            organizService.processEvent(id, OrganizUtils.OrganizEvent.confirm, PlatformUserHolder.getUsername(), reason);
        } else {
            organizService.processEvent(id, OrganizUtils.OrganizEvent.reject, PlatformUserHolder.getUsername(), reason);
        }
    }


    /**
     * 申报服务处完善登记信息
     *
     * @param organizId    当前登录的服务处id
     * @param registParams 登记信息实例对象
     * @return
     */
    public String registInfo(Long organizId, RegistParams registParams) {
        Organiz organiz = organizService.getById(organizId);
        return organizService.updateRegistInfo(organiz, registParams);
    }

    /**
     * 获取申报服务处列表（提供给前台页面）
     * @param jQueryList
     * @param pager
     * @return
     */
    public List<JDeclareOrganiz> getOrganizlist(JQueryList jQueryList, DatagridPager pager) {
        UserUtils.UserType userType = DeclareHolder.getUserType();
        if (userType == UserUtils.UserType.service) {
                /*获取服务处列表*/
                List<Organiz> organizList = organizService.getOrganizlist(jQueryList.setFkProvenceCenterId(DeclareHolder.getObjectId()), pager);

                Set<String> liveCodes = organizList.stream().map(Organiz::getLiveCode).collect(Collectors.toSet());
                List<ApiArea> codeList = areaApi.getByCode(liveCodes);
                Map<String ,String> areaMap = com.cpsdb.base.streaming.Streamable.valueOf(codeList).uniqueGroupby(ApiArea::getAreaCode,ApiArea::getArea).collect();

                Set<Long> organizIds = organizList.stream().map(Organiz::getId).collect(Collectors.toSet());

                return organizList.stream()
                        .map(s -> new JDeclareOrganiz()
                                .setId(s.getId())
                                .setSn(SnUtils.getOrganizSn(s.getId().toString()))
                                .setName(s.getName())
                                .setCompanyName(s.getCompanyName())
                                .setProvenceName(s.getProvenceName())
                                .setAddress((s.getLiveCode()==null)?null:areaMap.get(s.getLiveCode()) + s.getAddress())
                                .setCreateTime(s.getCreateTime())
                        )
                        .collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 申报机构总数
     * @param jQueryList
     * @return
     */
    public Integer getOrganizCount(JQueryList jQueryList) {
        UserUtils.UserType userType = DeclareHolder.getUserType();
        if (userType == UserUtils.UserType.service) {
            return organizService.getOrganizCount(jQueryList.setFkProvenceCenterId(DeclareHolder.getObjectId()));
        }else {
            return null;
        }
    }

    /**
     * 删除申报服务处
     *
     * @param organizId
     * @return
     */
    public void delete(String username, Long organizId, String reason) {
        Organiz organiz = organizService.getById(organizId);
        AssertUtils.notNull(organiz, new CustomException("申报机构不存在！"));
        organizService.delete(username, organizId, reason);

    }
}
