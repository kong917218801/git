package com.cpsdb.declareserv.controller.organiz;

import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.declareapi.holder.DeclareHolder;
import com.cpsdb.declareapi.utils.UserUtils;
import com.cpsdb.declareserv.dto.JCodeDetails;
import com.cpsdb.declareserv.dto.JDeclarer;
import com.cpsdb.declareserv.dto.JDeclarerDto;
import com.cpsdb.declareserv.manager.DeclarerCodeDetailManager;
import com.cpsdb.declareserv.manager.DeclarerManager;
import com.cpsdb.declareserv.params.declarer.JDeclarerQueryParams;
import com.cpsdb.declareserv.params.declarer.JOrganizRegistParams;
import com.cpsdb.declareserv.utils.DeclarerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 孔清
 * @ClassName OrganizDeclarerController
 * @Description
 *
 * @date: 2018-03-05 15:00:38
 */
@RestController
@RequestMapping("organiz/declarer")
public class OrganizDeclarerController extends BaseController {

    @Autowired
    private DeclarerManager declarerManager;

    @Autowired
    private DeclarerCodeDetailManager declarerCodeDetailManager;

    /**
     * @Description 查询申报官列表
     */
    @GetMapping("list")
    public List<JDeclarer> getDeclarerList(JDeclarerQueryParams params,DatagridPager pager) {
        Long objectId = DeclareHolder.getObjectId();
        UserUtils.UserType userType = DeclareHolder.getUserType();
        return declarerManager.getDeclarerList(params.setState(DeclarerUtils.DeclarerState.passed.name()),objectId,userType,pager);
    }

    /**
     * @Description 查询申报官列表总条数
     */
    @GetMapping("count")
    public Long getDeclarerCount(JDeclarerQueryParams params) {
        Long objectId = DeclareHolder.getObjectId();
        UserUtils.UserType userType = DeclareHolder.getUserType();
        return declarerManager.getDeclarerCount(params,objectId,userType);
    }


    /**
     * @Description 申报机构系统发起删除申报官状态
     * @Required id
     */
    @PostMapping("delete/{id}")
    public void delete(@PathVariable Long id, String reason) {
        declarerManager.organizDeleteDeclarer(id, DeclareHolder.getObjectId(), DeclareHolder.getUsername(), reason);
    }

    /**
     * @Description 查询申报官详情
     * @Required id
     */
    @GetMapping("details/{id}")
    public JDeclarerDto getDeclarer(@PathVariable Long id) {
        return declarerManager.getDeclarer(id);
    }


    /**
     * @Description 申报系统服务处注册申报官接口
     */
    @PostMapping("declarerRegist")
    public void declarerRegist(JOrganizRegistParams params){
        params.validate();
        declarerManager.createByOrganiz(params);
    }

    /**
     * @Description 申报系统省级服务中心注册申报官接口
     */
    @PostMapping("serviceRegist")
    public void serviceRegist(JOrganizRegistParams params){
        params.validate();
        declarerManager.createByService(params);
    }

    /**
     * @Description 查看申报官二维码扫描详情
     */
    @GetMapping("code/{id}")
    public List<JCodeDetails> getCodeDetails(@PathVariable Long id) {
        return declarerCodeDetailManager.getCodeDetails(id);
    }


    /**
     * @Description 申报官二维码详情导出
     */
    @GetMapping("code/export/{id}")
    public void export(@PathVariable Long id, HttpServletResponse response) throws IOException {
        declarerCodeDetailManager.declarerExport(id, response);
    }
}