package com.cpsdb.declareserv.controller.platform;

import com.cpsdb.base.common.CollectionUtils;
import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.baseservapi.annotation.GetPermission;
import com.cpsdb.baseservapi.api.AreaApi;
import com.cpsdb.baseservapi.dto.ApiArea;
import com.cpsdb.baseservapi.utils.AreaUtils;
import com.cpsdb.baseservapi.utils.UserType;
import com.cpsdb.declareserv.dto.JProvenceCenterDetail;
import com.cpsdb.declareserv.dto.JProvenceCenterList;
import com.cpsdb.declareserv.entity.ProvenceCenter;
import com.cpsdb.declareserv.manager.ProvenceManager;
import com.cpsdb.declareserv.params.provence.JProvenceQueryParams;
import com.cpsdb.declareserv.service.ProvenceCenterService;
import com.cpsdb.declareserv.utils.SnUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 老周
 * @ClassName PlatformProvenceController
 * @Description
 * @date: 2018-09-20 10:40:38
 */

@RestController
@RequestMapping("platform/provence")
public class PlatformProvenceController extends BaseController {

    @Autowired
    private ProvenceManager provenceManager;

    @Autowired
    private ProvenceCenterService provenceCenterService;

    @Autowired
    private AreaApi areaApi;

    /**
     * @Description 总后台省级服务中心列表
     */
    @GetMapping("list")
    public List<JProvenceCenterList> list(JProvenceQueryParams params, DatagridPager pager) {
        /*查询出省级服务中心*/
        List<ProvenceCenter> list = provenceManager.query(params, pager);

        if (CollectionUtils.isNotEmpty(list)) {
            /*把负责区域查出来*/
            Set<String> areaCodeSet = list.stream().map(ProvenceCenter::getAreaCode).collect(Collectors.toSet());
            Map<String, ApiArea> apiAreaMap = areaApi.getMapByCode(areaCodeSet);

            return list.stream()
                    .map(s -> new JProvenceCenterList()
                            .setId(s.getId())
                            .setSn(SnUtils.getProvinceSn(s.getId(), s.getAreaCode()))
                            .setCellphone(s.getCellphone())
                            .setChargerName(s.getChargerName())
                            .setCompanyName(s.getCompanyName())
                            .setArea(Optional.ofNullable(apiAreaMap.get(s.getAreaCode()))
                                    .map(ApiArea::getArea).orElse("未知区域")
                            )
                            .setCreateTime(s.getCreateTime())
                            .setAddress(s.getAddress())
                            .setType(s.getType())
                            .setState(s.getState())
                    )
                    .collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

    /**
     * @Description 总后台省级服务中心列表总数
     */
    @GetMapping("count")
    public Long count(JProvenceQueryParams params) {
        return provenceManager.count(params);
    }

    /**
     * @Description 省级服务中心财务审核
     */
    @PostMapping("financeAudit/{id}")
    @GetPermission(permission = UserType.center)
    public void financeAudit(@PathVariable Long id, @RequestParam String payment) {
        this.provenceCenterService.onfinanceAudit(id, payment);

    }

    /**
     * @Description 省级服务中心注册初审
     */
    @PostMapping("registAudit/{id}")
    @GetPermission(permission = UserType.market)
    public void registAudit(@PathVariable Long id, @RequestParam Boolean state, String reason) {
        this.provenceCenterService.onAudit(id, state, reason);

    }

    /**
     * @Description 省级服务中心注册复审
     */
    @PostMapping("reviewAudit/{id}")
    @GetPermission(permission = UserType.center)
    public void reviewAudit(@PathVariable Long id, @RequestParam Boolean state, String reason) {
        this.provenceCenterService.onRewAudit(id, state, reason);

    }

    /**
     * @Description 省级服务中心审核详情
     * @Required id
     */
    @GetMapping("{id}")
    public JProvenceCenterDetail details(@PathVariable Long id) {
        ProvenceCenter details = provenceManager.getDetails(id);
        ApiArea area = areaApi.getByCode(details.getAreaCode());
        return new JProvenceCenterDetail()
                .setCompanyName(details.getCompanyName())
                .setIdNumber(details.getIdNumber())
                .setType(details.getType())
                .setState(details.getState())
                .setRecommend(details.getRecommend())
                .setIdFrontUrl(details.getIdFrontUrl())
                .setIdBackUrl(details.getIdBackUrl())
                .setPaymentUrl(details.getPaymentUrl ())
                .setChargerName(details.getChargerName())
                .setChargerInvestigationUrl(details.getChargerInvestigationUrl())
                .setCompanyInvestigationUrl(details.getCompanyInvestigationUrl())
                .setAreaCode(details.getAreaCode())
                .setArea(Optional.ofNullable(area).map(ApiArea::getArea).orElse(StringUtils.EMPTY))
                .setApplyLetterUrl(details.getApplyLetterUrl())
                .setId(details.getId())
                .setCellphone(details.getCellphone())
                .setPromiseLetterUrl(details.getPromiseLetterUrl())
                .setBusinessLicenseUrl(details.getBusinessLicenseUrl())
                .setAddress(details.getAddress())
                .setReason(details.getReason())
                .setCreateTime(details.getCreateTime())
                .setModifyTime(details.getModifyTime());
    }
}
