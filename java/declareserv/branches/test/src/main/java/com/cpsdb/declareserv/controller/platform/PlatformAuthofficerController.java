package com.cpsdb.declareserv.controller.platform;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.common.ImageUtils;
import com.cpsdb.base.common.QRCodeUtils;
import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.baseservapi.annotation.GetPermission;
import com.cpsdb.baseservapi.dto.ApiPlatformUser;
import com.cpsdb.baseservapi.utils.UserType;
import com.cpsdb.declareserv.dto.JAuthofficer;
import com.cpsdb.declareserv.manager.AuthofficerManager;
import com.cpsdb.declareserv.params.authofficer.JAuthofficerParams;
import com.cpsdb.declareserv.params.authofficer.JAuthofficerQueryParams;
import com.cpsdb.declareserv.service.AuthofficerService;
import com.cpsdb.declareserv.utils.DeclarerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author 李银
 * @ClassName PlatformAuthofficerController
 * @date: 2017-12-13 10:08:08
 */
@RestController
@RequestMapping("platform/authofficer")
public class PlatformAuthofficerController extends BaseController {

    @Autowired
    private AuthofficerService authofficerService;

    @Autowired
    private AuthofficerManager authofficerManager;


    /**
     * @Description 创建认证官
     */
    @PostMapping("create")
    @GetPermission(permission = {UserType.center, UserType.market})
    public void createAuthofficer(JAuthofficerParams params, @RequestParam String validateCode) throws IOException {
        authofficerService.insert(params, validateCode);
    }

    /**
     * @Description 检索认证官列表
     */
    @GetMapping("query")
    @GetPermission(permission = {UserType.center, UserType.market})
    public List<JAuthofficer> query(JAuthofficerQueryParams params, DatagridPager pager) {
        return authofficerService.query(params, pager);
    }

    /**
     * @Description 获取申报认证官详情
     * @Required id
     */
    @GetMapping("get")
    public JAuthofficer get(@RequestParam Long id) {
        List<JAuthofficer> authofficers = this.query(new JAuthofficerQueryParams().setId(id), new DatagridPager(1, 1));
        AssertUtils.notEmpty(authofficers, new CustomException("获取认证官信息失败"));
        return authofficers.get(0);
    }

    /**
     * @Description 获取申报认证官列表总数
     */
    @GetMapping("query/count")
    public Integer count(JAuthofficerQueryParams params) {
        return authofficerService.count(params);
    }

    /**
     * @Description 申报认证官审核
     */
    @PostMapping("confirm")
    public void confirm(Long id) {
        authofficerService.updateState(id, true);
    }

    /**
     * @Description 修改申报认证官
     */
    @PostMapping("update")
    @GetPermission(permission = {UserType.center, UserType.market})
    public void update(Long id, JAuthofficerParams params) {
        authofficerService.update(id, params);
    }

    /**
     * @Description 认证官二维码下载
     * @Required id
     */
    @GetMapping("download/qrcode.jpg")
    public void donwload(HttpServletResponse response, Long id) throws Exception {
        BufferedImage image = QRCodeUtils.createImage(DeclarerUtils.authofficerQrcode(id), 170);

        response.reset();
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, ImageUtils.FORMAT_NAME_JPG, out);
        response.setHeader("Content-Disposition", "attachment; filename=\"qrcode-" + id + ".jpg\"");
        response.setContentType("multipart/form-data;charset=UTF-8");
        out.flush();
        out.close();
    }

    /**
     * @Description 获取服务中心下拉列表
     */
    @GetMapping("get/serviceCenter")
    public List<ApiPlatformUser> getCenter(String name) {
        return authofficerManager.getCenter(name);
    }

}