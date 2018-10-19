package com.cpsdb.declareserv.controller.organiz;

import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.declareapi.holder.DeclareHolder;
import com.cpsdb.declareserv.dto.JDeclareOrganiz;
import com.cpsdb.declareserv.dto.JOrganiz;
import com.cpsdb.declareserv.dto.JOrganizEssential;
import com.cpsdb.declareserv.dto.JOrganizRegister;
import com.cpsdb.declareserv.manager.OrganizManager;
import com.cpsdb.declareserv.params.organiz.BaseInfoParams;
import com.cpsdb.declareserv.params.organiz.JQueryList;
import com.cpsdb.declareserv.params.organiz.RegistParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zdr
 * @ClassName OrganizController
 * @Description
 * @date: 2018-09-20 10:40:38
 */

@RestController
@RequestMapping("organiz")
public class OrganizController extends BaseController {

    @Autowired
    private OrganizManager organizManager;

    /**
     * @Description 申报服务处完善基本信息
     */
    @PostMapping("baseInfo")
    public void baseInfo(BaseInfoParams baseInfoParams) {
        Long organizId = DeclareHolder.getObjectId();
        organizManager.baseInfo(organizId, baseInfoParams);
    }

    /**
     * @Description 申报服务处完善登记信息
     */
    @PostMapping("registInfo")
    public String registInfo(RegistParams registParams) {
        Long organizId = DeclareHolder.getObjectId();
        return organizManager.registInfo(organizId, registParams);
    }

    /**
     * @Description 审核不通过修改基本信息
     */
    @GetMapping("essential")
    public JOrganizEssential getEssential() {
        Long organizId = DeclareHolder.getObjectId();
        return organizManager.getEssential(organizId);
    }


    /**
     * @Description 登记信息审核不通过时修改登记信息
     */
    @GetMapping("register")
    public JOrganizRegister getRegister() {
        Long organizId = DeclareHolder.getObjectId();
        return organizManager.getRegister(organizId);
    }

    /**
     * @Description 申报服务处列表
     */
    @GetMapping("list")
    public List<JDeclareOrganiz> list(JQueryList jQueryList, DatagridPager pager) {
        return organizManager.getOrganizlist(jQueryList, pager);
    }
 
    /**
     * @Description 申报服务处列表总数
     */
    @GetMapping("count")
    public Integer count(JQueryList jQueryList) {
        return organizManager.getOrganizCount(jQueryList);
    }


}
