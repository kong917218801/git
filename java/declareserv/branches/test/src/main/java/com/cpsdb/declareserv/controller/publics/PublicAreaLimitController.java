package com.cpsdb.declareserv.controller.publics;

import com.cpsdb.base.controller.BaseController;
import com.cpsdb.declareserv.dto.JAreaLimitOrganiz;
import com.cpsdb.declareserv.service.AreaLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zdr
 * @ClassName PublicAreaLimitController
 * @Description
 * @date: 2018-09-20 10:40:38
 */

@RestController
@RequestMapping("publics/arealimit")
public class PublicAreaLimitController extends BaseController {

    @Autowired
    AreaLimitService areaLimitService;
    /**
     * @Description 申请区域剩余名额
     * @Required areaCode
     */
    @GetMapping("")
    public JAreaLimitOrganiz arealimitList(String areaCode) {
      return areaLimitService.getAreaLimitList(areaCode);
    }
}
