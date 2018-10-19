package com.cpsdb.declareserv.controller.rzgapp;

import com.cpsdb.base.controller.BaseController;
import com.cpsdb.declareserv.manager.DeclareEnterpriseManager;
import com.cpsdb.declareserv.dto.JDeclareEnterpriseDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李银
 * @ClassName AuthappController
 * @Description 移动端认证官登录接口
 * @date: 2017-12-13 10:08:08
 */
@RestController
@RequestMapping("authapp")
public class AuthappController extends BaseController {

    @Autowired
    private DeclareEnterpriseManager declareEnterpriseManager;

    /**
     * @Description 获取企业申报材料
     * @Required id 入库企业id
     */
    @GetMapping("declareEnterprise/{id}")
    public JDeclareEnterpriseDetail getDeclareEnterprise(@PathVariable Long id){
        return declareEnterpriseManager.getDeclareEnterprise(id);
    }
}