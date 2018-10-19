package com.cpsdb.declareserv.controller.platform;

import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.baseservapi.annotation.GetPermission;
import com.cpsdb.baseservapi.holder.PlatformUserHolder;
import com.cpsdb.baseservapi.utils.UserType;
import com.cpsdb.declareserv.dto.JOrganiz;
import com.cpsdb.declareserv.dto.JOrganizQuery;
import com.cpsdb.declareserv.manager.OrganizManager;
import com.cpsdb.declareserv.params.organiz.JOrganizQueryParams;
import com.cpsdb.declareserv.service.OrganizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李银
 * @ClassName PlatformOrganizController
 * @Description
 * @date: 2018-09-20 16:30:08
 */
@RestController
@RequestMapping("platform/organiz")
public class PlatformOrganizController  extends BaseController {
    @Autowired
    private OrganizService organizService;

    @Autowired
    private OrganizManager organizManager;

    /**
     * @Description 分页查询申报服务处
     */
    @GetMapping("query")
    public List<JOrganizQuery> query(JOrganizQueryParams params, DatagridPager pager) {
        return organizService.query(params, pager);
    }

    /**
     * @Description 分页查询申报服务处总数
     */
    @GetMapping("count")
    public Long count(JOrganizQueryParams params) {
        return organizService.count(params);
    }


    /**
     * @Description 申报服务处删除
     * @Required id
     */
    @PostMapping("delete/{id}")
    @GetPermission(permission = UserType.center)
    public void delete(@PathVariable Long id, String reason) {
        String username = PlatformUserHolder.getUsername();
        organizManager.delete(username, id, reason);
    }

    /**
     * @Description 申报服务处详情
     * @Required id
     */
    @GetMapping("{id}")
    public JOrganiz getDetails(@PathVariable Long id) {
        return organizManager.getBaseInfo(id);
    }



    /**
     * @Description 申报服务处支付凭证审核
     * @Required id
     * @Required state
     */
    @PostMapping("financeAudit/{id}")
    @GetPermission(permission = UserType.center)
    public void financeAudit(@PathVariable Long id, @RequestParam Boolean state, String reason,String paymentUrl) {
        organizManager.financeAudit(id, state, reason,paymentUrl);
    }


    /**
     * @Description 申报服务处基本信息初审
     * @Required id
     * @Required state
     */
    @PostMapping("baseWaitAudit/{id}")
    @GetPermission(permission = UserType.market)
    public void baseWaitAudit(@PathVariable Long id, @RequestParam Boolean state, String reason) {
        organizManager.audit(id, state, reason);
    }

    /**
     * @Description 申报服务处基本信息复审
     * @Required id
     * @Required state
     */
    @PostMapping("baseAudit/{id}")
    @GetPermission(permission = UserType.center)
    public void audit(@PathVariable Long id, @RequestParam Boolean state, String reason) {
        organizManager.audit(id, state, reason);
    }

    /**
     * @Description 申报服务处登记信息初审
     * @Required id
     * @Required state
     */
    @PostMapping("registWaitAudit/{id}")
    @GetPermission(permission = UserType.market)
    public void registWaitAudit(@PathVariable Long id, @RequestParam Boolean state, String reason) {
        organizManager.audit(id, state, reason);
    }


    /**
     * @Description 申报服务处登记信息复审
     * @Required id
     * @Required state
     */
    @PostMapping("registAudit/{id}")
    @GetPermission(permission = UserType.center)
    public void registAudit(@PathVariable Long id, @RequestParam Boolean state, String reason) {
        organizManager.audit(id, state, reason);
    }



}
