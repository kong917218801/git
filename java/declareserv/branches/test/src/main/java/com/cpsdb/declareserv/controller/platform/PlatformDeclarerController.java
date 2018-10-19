package com.cpsdb.declareserv.controller.platform;

import com.cpsdb.base.common.ImageUtils;
import com.cpsdb.base.common.QRCodeUtils;
import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.baseservapi.annotation.GetPermission;
import com.cpsdb.baseservapi.holder.PlatformUserHolder;
import com.cpsdb.baseservapi.utils.UserType;
import com.cpsdb.declareserv.dto.JCodeDetails;
import com.cpsdb.declareserv.dto.MDeclarer;
import com.cpsdb.declareserv.manager.DeclarerCodeDetailManager;
import com.cpsdb.declareserv.manager.DeclarerManager;
import com.cpsdb.declareserv.params.declarer.JDeclarerDetail;
import com.cpsdb.declareserv.params.declarer.JDeclarerQueryParams;
import com.cpsdb.declareserv.service.DeclarerService;
import com.cpsdb.declareserv.utils.DeclarerUtils;
import com.cpsdb.declareserv.utils.SnUtils;
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
 * @ClassName PlatformDeclarerController
 * @Description
 * @date: 2017-12-13 10:08:08
 */
@RestController
@RequestMapping("platform/declarer")
public class PlatformDeclarerController extends BaseController {

    @Autowired
    private DeclarerManager declarerManager;

    @Autowired
    private DeclarerService declarerService;

    @Autowired
    private DeclarerCodeDetailManager declarerCodeDetailManager;

    /**
     * @Description 分页查询申报官
     */
    @GetMapping("query")
    public List<MDeclarer> query(JDeclarerQueryParams params, DatagridPager pager) {
        return declarerManager.query(params, pager);
    }

    /**
     * @Description 分页查询机构总数
     */
    @GetMapping("count")
    public Long count(JDeclarerQueryParams params) {
        return declarerManager.count(params);
    }

    /**
     * @Description 删除申报官
     * @Required id
     */
    @PostMapping("delete/{id}")
    @GetPermission(permission = UserType.center)
    public void deleteOrganiz(@PathVariable Long id, String reason) {
        declarerManager.platformDeleteDeclarer(id, PlatformUserHolder.getUsername(), reason);
    }

    /**
     * @Description 申报官详情
     * @Required id
     */
    @GetMapping("{id}")
    public JDeclarerDetail declarerDetails(@PathVariable Long id) {
        return declarerManager.declarerDetails(id);
    }

    /**
     * @Description 申报官财务审核
     * @Required id
     * @Required state
     */
    @PostMapping("financeAudit/{id}")
    @GetPermission(permission = UserType.center)
    public void declarerFinanceAudit(@PathVariable Long id, @RequestParam Boolean state,String reason, String paymentUrl) {
        declarerService.updateFinanace(id, state,reason, paymentUrl);
    }

    /**
     * @Description 申报官初审
     * @Required id
     * @Required state
     */
    @PostMapping("waitAudit/{id}")
    @GetPermission(permission = UserType.market)
    public void declarerWaitAudit(@PathVariable Long id, @RequestParam Boolean state, String reason) {
        declarerManager.confirm(id, state, PlatformUserHolder.getUsername(), reason);
    }

    /**
     * @Description 申报官审核
     * @Required id
     * @Required state
     */
    @PostMapping("audit/{id}")
    @GetPermission(permission = UserType.center)
    public void declarerAudit(@PathVariable Long id, @RequestParam Boolean state, String reason) {
        declarerManager.confirm(id, state, PlatformUserHolder.getUsername(), reason);
    }

    /**
     * @Description 查看申报官二维码详情
     * @Required id
     */
    @GetMapping("code/{id}")
    public List<JCodeDetails> getCodeDetails(@PathVariable Long id) {
        return declarerManager.getCodeDetails(id);
    }


    /**
     * @Description 申报官二维码详情导出
     * @Required id
     */
    @GetMapping("code/export/{id}")
    @GetPermission(permission = {UserType.center, UserType.market})
    public void export(@PathVariable Long id, HttpServletResponse response) throws IOException {
        declarerCodeDetailManager.declarerExport(id, response);
    }


    /**
     * @Description 申报官指派
     * @Required id
     */
    @PostMapping("designate/{declarerId}")
    @GetPermission(permission = {UserType.center})
    public void designate(@PathVariable Long declarerId, @RequestParam Long organizId) {
        declarerManager.designate(declarerId, organizId);
    }

    /**
     * @Description 申报官二维码下载
     */
    @GetMapping("download/qrcode.jpg")
    @GetPermission(permission = {UserType.center, UserType.market})
    public void donwload(HttpServletResponse response, Long id) throws Exception {
        BufferedImage image = QRCodeUtils.createImage(DeclarerUtils.declarerQrcode(id), 170);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/force-download");
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, ImageUtils.FORMAT_NAME_JPG, out);
        response.setHeader("Content-Disposition", "attachment;filename=\"qrcode-" + SnUtils.getDeclarerSn(id.toString()) + ".jpg\"");
        ImageIO.write(image, ImageUtils.FORMAT_NAME_JPG, out);
        out.flush();
        out.close();
    }
}