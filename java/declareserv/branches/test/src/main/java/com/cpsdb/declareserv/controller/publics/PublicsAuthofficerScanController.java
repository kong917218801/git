package com.cpsdb.declareserv.controller.publics;

import com.cpsdb.base.common.ProjectUtils;
import com.cpsdb.base.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 李银
 * @ClassName PublicsAuthofficerScanController
 * @Description:
 * @date: 2017-12-03 15:00:38
 */
@RestController
@RequestMapping("e")
public class PublicsAuthofficerScanController extends BaseController {

    /**
     * @Description 认证官二维码被扫
     * @Required id
     */
    @GetMapping("{id}")
    public <T> T scan(/*具体的二维码*/@PathVariable Long id) {
        return (T) new ModelAndView("redirect:" + ProjectUtils.Project.m.getDomain() + "apps/authofficer/detail.html").addObject("id", id);
    }

}