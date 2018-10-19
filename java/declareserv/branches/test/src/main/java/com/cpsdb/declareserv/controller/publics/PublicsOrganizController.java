package com.cpsdb.declareserv.controller.publics;

import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.declareserv.dto.JOrganizFreedom;
import com.cpsdb.declareserv.params.organiz.JOrganizLogin;
import com.cpsdb.declareserv.params.organiz.JQueryFreedomList;
import com.cpsdb.declareserv.service.OrganizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zdr
 * @ClassName PublicsOrganizController
 * @Description
 * @date: 2018-09-20 10:40:38
 */
@RestController
@RequestMapping("publics/organiz")
public class PublicsOrganizController extends BaseController {

    @Autowired
    private OrganizService organizService;


    /**
     * @Description 申报服务处注册
     * @Required cellphone
     * @Required password
     * @Required code
     */
    @PostMapping("addInfo")
    public JOrganizLogin addInfo(@RequestParam("cellphone") String cellphone,
                                 @RequestParam("password") String password,
                                 @RequestParam("code") String code) throws Exception {
       return organizService.create(cellphone, password,code);
    }

    /**
     * @Description  申报服务处自主管理平台列表
     */
    @GetMapping("freedomList")
    public List<JOrganizFreedom> freedomList(JQueryFreedomList jQueryFreedomList, DatagridPager pager) {
        return  organizService.getFreedomlist(jQueryFreedomList, pager);
    }


    /**
     * @Description 申报服务处自主管理平台列表总数
     */
    @GetMapping("freedomCount")
    public Integer count(JQueryFreedomList jQueryFreedomList) {
        return organizService.getfreedomCount(jQueryFreedomList);
    }


}
