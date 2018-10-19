package com.cpsdb.declareserv.service;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.base.mapper.JsonMapper;
import com.cpsdb.baseservapi.api.AreaApi;
import com.cpsdb.baseservapi.dto.ApiArea;
import com.cpsdb.baseservapi.holder.PlatformUserHolder;
import com.cpsdb.baseservapi.utils.AreaUtils;
import com.cpsdb.declareapi.utils.UserUtils;
import com.cpsdb.declareserv.entity.ProvenceCenter;
import com.cpsdb.declareserv.entity.User;
import com.cpsdb.declareserv.params.provence.JProvenceCenterAddParams;
import com.cpsdb.declareserv.params.provence.JProvenceCenterEditParams;
import com.cpsdb.declareserv.tools.ThreadUtils;
import com.cpsdb.declareserv.utils.DeclareConfigUtils;
import com.cpsdb.declareserv.utils.ProvenceUtils;
import com.cpsdb.declareserv.utils.RecommendUtils;
import com.cpsdb.redis.annotation.Lock;
import com.cpsdb.redis.tool.RedisProxy;
import com.cpsdb.thirdapi.utils.ModeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * @author 李银
 * @ClassName ProvenceCenterService
 * @Description
 * @date 2018-09-20 10:42:40
 */
@Service
public class ProvenceCenterService extends ProvenceFsmService {

    @Autowired
    private RedisProxy redisProxy;

    @Autowired
    private UserService userService;

    @Autowired
    private AreaApi areaApi;
    @Autowired
    private OrganizService organizService;
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private DeclarerService declarerService;

    public void insert(ProvenceCenter provenceCenter) {
        provenceCenterMapper.insert(provenceCenter);
    }

    public void update(ProvenceCenter provenceCenter) {
        provenceCenterMapper.update(provenceCenter);
    }

    public void updatePayment(Long id,String payment) {
        provenceCenterMapper.updatePayment(id,payment);
    }

    public ProvenceCenter getById(Long id) {
        return provenceCenterMapper.getById(id);
    }

    public ProvenceCenter getByPhone(String cellphone) {
        return provenceCenterMapper.getByPhone(cellphone);
    }

    public ProvenceCenter getIdNumber(String idNumber) {
        return provenceCenterMapper.getIdNumber(idNumber);
    }

    public ProvenceCenter getByAreaCode(String areaCode) {
        return provenceCenterMapper.getByAreaCode(areaCode);
    }

    public void create(JProvenceCenterAddParams params) {
        // 选择的必须是省，120000
        AssertUtils.isTrue(AreaUtils.Type.provence == AreaUtils.getType(params.getAreaCode()),
                new CustomException("请选择省份！"));

        ProvenceCenter prov = this.getByAreaCode(params.getAreaCode());
        AssertUtils.isNull(prov, new CustomException("该省的省级服务中心已存在！"));
        ProvenceCenter provPhone = this.getByPhone(params.getCellphone());
        AssertUtils.isNull(provPhone, new CustomException("该账号已存在！"));
        ProvenceCenter provIdNumber = this.getIdNumber(params.getIdNumber());
        AssertUtils.isNull(provIdNumber, new CustomException("该证件号已注册！"));

        ApiArea apiArea = areaApi.getByCode(params.getAreaCode());
        params.setType(this.getType(apiArea.getAreaCode()));
        AssertUtils.notNull(apiArea, new CustomException("请选择正确的区域！"));

        ProvenceCenter provenceCenter = new ProvenceCenter()
                .setAddress(params.getAddress())
                .setType(params.getType())
                .setApplyLetterUrl(params.getApplyLetterUrl())
                .setAreaCode(params.getAreaCode())
                .setCellphone(params.getCellphone())
                .setChargerInvestigationUrl(params.getChargerInvestigationUrl())
                .setChargerName(params.getChargerName())
                .setCompanyName(params.getCompanyName())
                .setIdBackUrl(params.getIdBackUrl())
                .setIdFrontUrl(params.getIdFrontUrl())
                .setPromiseLetterUrl(params.getPromiseLetterUrl())
                .setBusinessLicenseUrl(params.getBusinessLicenseUrl())
                .setRecommend(params.getRecommend())
                .setCompanyInvestigationUrl(params.getCompanyInvestigationUrl())
                .setIdNumber(params.getIdNumber());
        this.insert(provenceCenter);
        // 创建用户
        User me = this.userService.insert(params.getCellphone(), params.getPassword(), UserUtils.UserType.service, provenceCenter.getId());
        // 推荐的
        if (StringUtils.isNotBlank(params.getRecommend())) {
            recommendService.insert(params.getRecommend(), me.getUsername());
        }
        // 初始化状态
        this.processEvent(provenceCenter.getId(), ProvenceUtils.ProvEvent.create, provenceCenter.getChargerName(), null);
    }


    @Lock(key = "_update_provence", argsIndex = 0)
    public void editProvence(Long id, JProvenceCenterEditParams params) {
        //1， 验证参数有效性；
        ProvenceCenter provenceCenter = this.getById(id);
        AssertUtils.notNull(provenceCenter, new CustomException("该省级服务中心不存在！"));
        AssertUtils.isTrue(provenceCenter.getId().equals(id), new CustomException("您没有该权限！"));
        provenceCenter.setCompanyName(params.getCompanyName())
//                .setIdNumber(params.getIdNumber())
                .setIdFrontUrl(params.getIdFrontUrl())
                .setIdBackUrl(params.getIdBackUrl())
                .setChargerName(params.getChargerName())
                .setChargerInvestigationUrl(params.getChargerInvestigationUrl())
                .setCompanyInvestigationUrl(params.getCompanyInvestigationUrl())
                .setBusinessLicenseUrl(params.getBusinessLicenseUrl())
                .setApplyLetterUrl(params.getApplyLetterUrl())
                .setPromiseLetterUrl(params.getPromiseLetterUrl())
                .setAddress(params.getAddress());
        if (provenceCenter.getIdNumber().equals(params.getIdNumber())) {
            provenceCenter.setIdNumber(params.getIdNumber());
        } else {
            ProvenceCenter provIdNumber = this.getIdNumber(params.getIdNumber());
            AssertUtils.isNull(provIdNumber, new CustomException("该证件号已注册！"));
            provenceCenter.setIdNumber(params.getIdNumber());
        }
        this.update(provenceCenter);
        this.processEvent(id, ProvenceUtils.ProvEvent.submit, provenceCenter.getCellphone(), null);
//        String key = this.getByPhone(params.getCellphone()) + "_update_provence";
//        AssertUtils.isTrue(redisProxy.lock(key, 900L), new CustomException("请勿重复提交！"));
//        try {
//            this.update(provenceCenter);
//            this.processEvent(id, ProvenceUtils.ProvEvent.submit, provenceCenter.getCellphone (), null);
//        } finally {
//            redisProxy.del(key);
//        }
    }

    @Override
    protected void onStateChanged(Long id, String stateFrom, ProvenceUtils.ProvEvent event, String stateTo, String mark) {
        //审核通过调用recommendService设置状态
        if (ProvenceUtils.ProvenceState.passed.name().equals(stateTo)) {

            User user = this.userService.getByObjectId(id, UserUtils.UserType.service);
            recommendService.updateTargetState(user.getId(), RecommendUtils.TargetState.passed);

            ProvenceCenter center = this.getById(id);
            ThreadUtils.runThread(center.getCellphone(), ModeType.PROVENCEPASS, center.getChargerName());

        } else if (ProvenceUtils.ProvenceState.unpass.name().equals(stateTo)) {

            User user = this.userService.getByObjectId(id, UserUtils.UserType.service);
            recommendService.updateTargetState(user.getId(), RecommendUtils.TargetState.unpass);

            ProvenceCenter center = this.getById(id);
            ThreadUtils.runThreadProv(center.getCellphone(), ModeType.PROVENCEUNPASS, center.getChargerName(), mark);
        } else {
            // do nothing;

        }
    }
    public  void onfinanceAudit(Long id, String payment){
        AssertUtils.notNull(payment, new CustomException("请上传支付凭证！"));
        this.updatePayment ( id,payment );
        this.processEvent(id, ProvenceUtils.ProvEvent.confirm, PlatformUserHolder.getUsername(),null);

    }
    public void onAudit(Long id, Boolean state, String reason) {
        if (state) {
            //审核通过
            this.processEvent(id, ProvenceUtils.ProvEvent.confirm, PlatformUserHolder.getUsername(), reason);
        } else {
            //审核不通过
            this.processEvent(id, ProvenceUtils.ProvEvent.reject, PlatformUserHolder.getUsername(), reason);
        }

    }

    public void onRewAudit(Long id, Boolean state, String reason) {
        ProvenceCenter provenceCenter = this.getById(id);
        if (state) {
            //审核通过
            ApiArea apiArea = areaApi.getByCode(provenceCenter.getAreaCode());
            //归属当前省级服务中心下申报处
            this.organizService.updateProvenceId(apiArea.getAreaCode(), id);
            //归属当前省级服务中心下申报官
            this.declarerService.updateProvence(apiArea.getAreaCode(), id);
            this.processEvent(id, ProvenceUtils.ProvEvent.confirm, PlatformUserHolder.getUsername(), reason);
        } else {
            //审核不通过
            this.processEvent(id, ProvenceUtils.ProvEvent.reject, PlatformUserHolder.getUsername(), reason);
        }

    }

    public String getType(String areaCode) {
        // 在diamond中去取配置数据，配置格式如下： {"110000":"A", ...}
        String mapping = DeclareConfigUtils.getValue("provence.type.mapping");
        Map<String, String> map = JsonMapper.buildNonNullMapper().fromJson(mapping, HashMap.class);

        String type = map.get(areaCode);
        AssertUtils.isTrue(StringUtils.containsIgnoreCase("A, B, C, D", type),
                new CustomException("未分配此区域的授权，请联系我中心"));
        return type;
    }


}