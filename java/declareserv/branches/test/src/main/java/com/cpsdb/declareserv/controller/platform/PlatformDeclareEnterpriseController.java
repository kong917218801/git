package com.cpsdb.declareserv.controller.platform;

import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.baseservapi.annotation.GetPermission;
import com.cpsdb.baseservapi.holder.PlatformUserHolder;
import com.cpsdb.baseservapi.utils.UserType;
import com.cpsdb.declareserv.dto.DeclareEnterpriseQuery;
import com.cpsdb.declareserv.manager.DeclareEnterpriseManager;
import com.cpsdb.declareserv.params.declarer.JAuditParams;
import com.cpsdb.declareserv.dto.JDeclareEnterpriseDetail;
import com.cpsdb.declareserv.params.declarer.JEnterpriseQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李银
 * @ClassName: PlatformDeclareEnterpriseController
 * @Description:
 * @date: 2017-12-13 10:08:08
 */
@RestController
@RequestMapping("platform/declare/enterprise")
public class PlatformDeclareEnterpriseController extends BaseController {

    @Autowired
    private DeclareEnterpriseManager declareEnterpriseManager;

    /**
     * @Description 分页查询申报企业
     */
    @GetMapping("query")
    public List<DeclareEnterpriseQuery> queryList(JEnterpriseQueryParams params, DatagridPager pager) {
        return declareEnterpriseManager.queryList(params, pager);
    }

    /**
     * @Description 分页查询企业总数
     */
    @GetMapping("count")
    public Long count(JEnterpriseQueryParams params) {
        return declareEnterpriseManager.count(params);
    }

    /**
     * @Description 申报企业详情
     */
    @GetMapping("{id}")
    public JDeclareEnterpriseDetail getDetails(@PathVariable Long id) {
        return declareEnterpriseManager.getPlatDetails(id);
    }

    /**
     * @Description 申报企业初审
     */
    @PostMapping("waitAudit/{id}")
    @GetPermission(permission = UserType.market)
    public void waitAudit(@PathVariable Long id, JAuditParams params) {
        declareEnterpriseManager.waitAudit(id, PlatformUserHolder.getUsername(),params);
    }

    /**
     * @Description 申报企业审核
     */
    @PostMapping("audit/{id}")
    @GetPermission(permission = UserType.center)
    public void enterpriseAudit(@PathVariable Long id,JAuditParams params) {
        declareEnterpriseManager.audit(id,params);

    }
}