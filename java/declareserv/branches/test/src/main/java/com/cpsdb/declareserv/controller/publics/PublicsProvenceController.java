package com.cpsdb.declareserv.controller.publics;

import com.cpsdb.base.common.CollectionUtils;
import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.baseservapi.api.AreaApi;
import com.cpsdb.baseservapi.dto.ApiArea;
import com.cpsdb.declareserv.dto.JPublicsProvence;
import com.cpsdb.declareserv.entity.ProvenceCenter;
import com.cpsdb.declareserv.manager.ProvenceManager;
import com.cpsdb.declareserv.params.provence.JProvenceCenterAddParams;
import com.cpsdb.declareserv.params.provence.JProvenceQueryParams;
import com.cpsdb.declareserv.params.provence.JPublicsProvenceParams;
import com.cpsdb.declareserv.utils.ProvenceUtils;
import com.cpsdb.declareserv.utils.SnUtils;
import com.cpsdb.validator.Cascading;
import com.cpsdb.validator.Validate;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author 老周
 * @ClassName PublicsProvenceController
 * @Description
 * @date: 2018-09-20 10:40:38
 */

@RestController
@RequestMapping("publics/provence")
public class PublicsProvenceController extends BaseController {

    @Autowired
    private ProvenceManager provenceManager;

    @Autowired
    private AreaApi areaApi;


    /**
     * @Description 省级服务中心注册
     */
    @PostMapping("register")
    @Validate
    public void register(@Cascading JProvenceCenterAddParams params) {
        this.provenceManager.register(params.getCellphone(), params);
    }

    /**
     * @Description 省级服务中心列表
     */
    @GetMapping("list")
    public List<JPublicsProvence> list(JPublicsProvenceParams params, DatagridPager pager) {
        /*查询出省级服务中心*/
        List<ProvenceCenter> list = provenceManager.query(
                new JProvenceQueryParams()
                        .setAreaCode(params.getAreaCode())
                        .setCompanyName(params.getName())
                        // 这里只显示审核通过的省级服务中心
                        .setState(ProvenceUtils.ProvenceState.passed.name()),
                pager);

        if (CollectionUtils.isNotEmpty(list)) {
            /*把负责区域查出来*/
            Set<String> areaCodeSet = list.stream().map(ProvenceCenter::getAreaCode).collect(Collectors.toSet());
            Map<String, ApiArea> apiAreaMap = areaApi.getMapByCode(areaCodeSet);

            return list.stream()
                    .map(s -> new JPublicsProvence()
                            .setId(s.getId())
                            .setSn( SnUtils.getProvinceSn(s.getId(),s.getAreaCode ()))
                            .setCellphone(s.getCellphone())
                            .setChargerName(s.getChargerName())
                            .setCompanyName(s.getCompanyName())
                            .setArea(Optional.ofNullable(apiAreaMap.get(s.getAreaCode()))
                                    .map(ApiArea::getArea).orElse("中国商品诚信数据库服务中心")
                            )
                    )
                    .collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

    /**
     * @Description 省级服务中心列表总数
     */
    @GetMapping("count")
    public Long count(JPublicsProvenceParams params) {
        return provenceManager.count(
                new JProvenceQueryParams()
                        .setAreaCode(params.getAreaCode())
                        .setCompanyName(params.getName())
                        .setState(ProvenceUtils.ProvenceState.passed.name())
        );
    }

    /**
     * @Description 省级服务中心区域getAreaCode
     */
    @GetMapping("areacode")
    public List<String> getAreaCodeList() {
        return provenceManager.getAreaCodeList ();
    }
}

