package com.cpsdb.declareserv.controller.zhsapp;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.declareserv.dto.JAuthofficer;
import com.cpsdb.declareserv.params.authofficer.JAuthofficerQueryParams;
import com.cpsdb.declareserv.service.AuthofficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李银
 * @ClassName ZhsAppAuthofficerController
 * @Description
 * @date: 2017-12-03 15:00:38
 */
@RestController
@RequestMapping("zhsapp/authofficer")
public class ZhsAppAuthofficerController extends BaseController {

    @Autowired
    private AuthofficerService authofficerService;

    /**
     * @Description APP认证官详情
     * @Required id
     */
    @GetMapping("detail")
    public JAuthofficer get(@RequestParam Long id) {
        List<JAuthofficer> authofficers = this.authofficerService.query(new JAuthofficerQueryParams().setId(id), new DatagridPager(1, 1));
        AssertUtils.notEmpty(authofficers, new CustomException("获取认证官信息失败"));
        return authofficers.get(0);
    }
}