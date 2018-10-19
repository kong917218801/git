package com.cpsdb.declareserv.controller.organiz;

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
 * @author 孔清
 * @ClassName OrganizEnterpriseController
 * @Description
 * @date: 2018-03-05 15:00:38
 */
@RestController
@RequestMapping("organiz/enterprise")
public class OrganizEnterpriseController extends BaseController {

    @Autowired
    private DeclareEnterpriseManager declareEnterpriseManager;

    /**
     * @Description 查询申报企业列表
     */
    @GetMapping("list")
    public List<JDeclareEnterprise> list(JEnterpriseListParams jEnterpriseListParams, DatagridPager pager) {
        return declareEnterpriseManager.getEnterpriseList(jEnterpriseListParams, pager);
    }


    /**
     * @Description 查询申报企业总条数
     */
    @GetMapping("count")
    public Long getCount(JEnterpriseListParams jEnterpriseListParams) {
        return declareEnterpriseManager.getEnterpriseListCount(jEnterpriseListParams);
    }

    /**
     * @Description 查询申报企业详情
     */
    @GetMapping("{id}")
    public JDeclareEnterpriseDetail details(@PathVariable Long id) {
        return declareEnterpriseManager.getPlatDetails(id);
    }

    /**
     * @Description 添加申报企业
     */
    @PostMapping("info")
    public void addInfo(OrganizEnterprisParams organizEnterprisParams) {
        Long userId = DeclareHolder.getUserId();
        declareEnterpriseManager.addInfo(userId, organizEnterprisParams);
    }
}