package com.cpsdb.declareserv.controller.publics;

import com.cpsdb.base.common.CPSStringUtils;
import com.cpsdb.base.common.CookieUtils;
import com.cpsdb.base.common.ProjectUtils;
import com.cpsdb.base.controller.BaseController;
import com.cpsdb.declareserv.entity.DeclarerCodeDetail;
import com.cpsdb.declareserv.service.DeclarerCodeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

/**
 * @author 李银
 * @ClassName PublicsDeclareofficerScanController
 * @Description:
 * @date: 2017-12-03 15:00:38
 */
@RestController
@RequestMapping("d")
public class PublicsDeclareofficerScanController extends BaseController {

    @Autowired
    private DeclarerCodeDetailService declarerCodeDetailService;

    /**
     * @Description 申报官二维码被扫
     * @Required id
     */
    @GetMapping("{id}")
    public <T> T scan(HttpServletRequest request,
                      HttpServletResponse response,
                      /*具体的二维码*/@PathVariable Long id,
                      /*具体的地址*/String address,
                      /*经度*/BigDecimal longitude,
                      /*纬度*/BigDecimal latitude) {

         /*那个app在扫，Android or iOS*/
        Map<String, String> cookieMap = CookieUtils.getCookieMap(request);
        this.logger.info("收到一物一码二维码被扫码, code：{}，lon：{}，lat：{}", id, longitude, latitude);
         /*不是中华搜app扫的，则直接跳转至网页*/
        String uuid = cookieMap.get("uuid");
        if (CPSStringUtils.isBlank(uuid)) {
            uuid = UUID.randomUUID().toString();
            CookieUtils.addCookies(response, "uuid", uuid, 100 * 365 * 24 * 3600);
        }

        DeclarerCodeDetail detail = new DeclarerCodeDetail()
                .setFkDeclarerId(id)
                .setAddress(address)
                .setLatitude(latitude)
                .setLongitude(longitude);
        declarerCodeDetailService.insert(detail);
        return (T) new ModelAndView("redirect:" + ProjectUtils.Project.m.getDomain() + "apps/declarer/detail.html").addObject("id", id);
    }
}