package com.cpsdb.declareserv.controller.declarerapp;

import com.cpsdb.base.common.CPSStringUtils;
import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.declareapi.holder.DeclareHolder;
import com.cpsdb.declareserv.dto.JAppRecommend;
import com.cpsdb.declareserv.params.JRecommendQueryParams;
import com.cpsdb.declareserv.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 孔清
 * @ClassName DeclarerAppRecommendController
 * @Description
 * @date: 2018-03-05 15:00:38
 */
@RestController
@RequestMapping("declarerapp/recommend")
public class DeclarerAppRecommendController extends BaseController {

    @Autowired
    private RecommendService recommendService;

    /**
     * @Description 申报官APP分页查询我的推荐列表
     */
    @GetMapping("list")
    List<JAppRecommend> list(String text, DatagridPager pager) {
        JRecommendQueryParams params = new JRecommendQueryParams();
        if (null != CPSStringUtils.parseLong(text)) {
            params.setTargetCellphone(text);
        } else {
            params.setTargetName(text);
        }
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
}
