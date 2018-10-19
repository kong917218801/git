package com.cpsdb.declareserv.controller.zhsapp;

import com.cpsdb.base.controller.BaseController;
import com.cpsdb.declareserv.dto.JDeclarerList;
import com.cpsdb.declareserv.manager.DeclarerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李银
 * @ClassName ZhsAppDeclarerController
 * @Description
 * @date: 2017-12-03 15:00:38
 */
@RestController
@RequestMapping("zhsapp/declarer")
public class ZhsAppDeclarerController extends BaseController {

    @Autowired
    private DeclarerManager declarerManager;

    /**
     * @Description APP申报官详情
     * @Required id
     */
    @GetMapping("detail")
    public JDeclarerList getDetailByZhs(@RequestParam Long id) {
        return declarerManager.getDetailByZhs(id);
    }
}