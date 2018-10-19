package com.cpsdb.declareserv.controller.declarerapp;

import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.declareapi.holder.DeclareHolder;
import com.cpsdb.declareserv.dto.JDeclareEnterprise;
import com.cpsdb.declareserv.manager.DeclareEnterpriseManager;
import com.cpsdb.declareserv.dto.JDeclareEnterpriseDetail;
import com.cpsdb.declareserv.params.declarer.JEnterpriseListParams;
import com.cpsdb.declareserv.params.declarer.OrganizEnterprisParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 老周
 * @ClassName DeclarerAppEnterpirseController
 *  * @Description
 * @date: 2018-03-05 15:00:38
 */
@RestController
@RequestMapping("declarerapp/enterprise")
public class DeclarerAppEnterpirseController extends BaseController {

    @Autowired
    private DeclareEnterpriseManager declareEnterpriseManager;

    /**
     * @Description 添加申报企业
     */
    @PostMapping("info")
    public void addInfo(OrganizEnterprisParams organizEnterprisParams) {
        Long userId = DeclareHolder.getUserId();
        declareEnterpriseManager.addInfo(userId, organizEnterprisParams);
    }

    /**
     * @Description 更新申报企业
     * @Required id
     */
    @PostMapping("{id}")
    public void addInfo(@PathVariable Long id, OrganizEnterprisParams organizEnterprisParams) {
        Long userId = DeclareHolder.getUserId();
        declareEnterpriseManager.updateInfo(userId, id, organizEnterprisParams);
    }

    /**
     * @Description 申报企业详情
     * @Required id
     */
    @GetMapping("{id}")
    public JDeclareEnterpriseDetail getDetails(@PathVariable Long id) {
        return declareEnterpriseManager.getPlatDetails(id);
    }

    /**
     * @Description 查询申报企业列表
     */
    @GetMapping("list")
    public List<JDeclareEnterprise> list(JEnterpriseListParams jEnterpriseListParams, DatagridPager pager) {
        return declareEnterpriseManager.getEnterpriseList(jEnterpriseListParams, pager);
    }
}
