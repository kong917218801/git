package com.cpsdb.declareserv.controller.organiz;

import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.declareapi.holder.DeclareHolder;
import com.cpsdb.declareserv.dto.JAppRecommend;
import com.cpsdb.declareserv.dto.JOrganizRecommend;
import com.cpsdb.declareserv.params.JRecommendQueryParams;
import com.cpsdb.declareserv.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 李银
 * @ClassName OrganizRecommendController
 * @date 2018年9月20日 16:12:08
 */
@RestController
@RequestMapping("organiz/recommend")
public class OrganizRecommendController extends BaseController {

    @Autowired
    private RecommendService recommendService;

    /**
     * @Description CPS申报服务处自主管理平台分页查询我的推荐列表
     */
    @GetMapping("list")
    List<JAppRecommend> list(JRecommendQueryParams params, DatagridPager pager) {
        return recommendService.query(params.setRecommendId(DeclareHolder.getUserId()), pager)
                .stream()
                .map(s -> new JAppRecommend()
                        .setCellphone(s.getTargetCellphone())
                        .setCreateTime(s.getCreateTime())
                        .setName(s.getTargetName())
                        .setState(s.getTargetState())
                )
                .collect(Collectors.toList());
    }

    /**
     * @Description CPS申报服务处自主管理平台我的推荐列表计算总数
     */
    @GetMapping("count")
    Integer count(JRecommendQueryParams params) {
        return recommendService.count(params.setRecommendId(DeclareHolder.getUserId()));
    }
}