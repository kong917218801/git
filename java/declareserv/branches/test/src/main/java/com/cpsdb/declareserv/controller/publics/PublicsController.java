package com.cpsdb.declareserv.controller.publics;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.common.CookieUtils;
import com.cpsdb.base.common.ImageUtils;
import com.cpsdb.base.common.QRCodeUtils;
import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.baseservapi.api.AreaApi;
import com.cpsdb.baseservapi.api.PlatformUserApi;
import com.cpsdb.baseservapi.dto.ApiPlatformUser;
import com.cpsdb.declareapi.params.ApiQueryParams;
import com.cpsdb.declareserv.entity.AreaLimit;
import com.cpsdb.declareserv.entity.Declarer;
import com.cpsdb.declareserv.manager.AuthofficerManager;
import com.cpsdb.declareserv.manager.DeclarerManager;
import com.cpsdb.declareserv.manager.UserManager;
import com.cpsdb.declareserv.params.authofficer.JAuthofficerLogin;
import com.cpsdb.declareserv.params.authofficer.JAuthofficerLoginParams;
import com.cpsdb.declareserv.params.authofficer.JAuthofficerParams;
import com.cpsdb.declareserv.params.declarer.JDeclarerRegistParams;
import com.cpsdb.declareserv.params.declarer.JExamineeLogin;
import com.cpsdb.declareserv.service.AreaLimitService;
import com.cpsdb.declareserv.service.AuthofficerService;
import com.cpsdb.declareserv.service.DeclarerService;
import com.cpsdb.declareserv.utils.DeclarerUtils;
import com.cpsdb.declareserv.utils.PDFUtils;
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
 * @ClassName: PublicsController
 * @Description:
 * @date: 2017-12-13 10:08:08
 */
@RestController
@RequestMapping("publics")
public class PublicsController extends BaseController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private AuthofficerService authofficerService;

    @Autowired
    private DeclarerService declarerService;

    @Autowired
    private AreaLimitService areaLimitService;

    @Autowired
    private PlatformUserApi platformUserApi;

    @Autowired
    private AuthofficerManager authofficeManager;

    @Autowired
    private AreaApi areaApi;

    @Autowired
    private DeclarerManager declarerManager;

    /**
     * @Description 认证官登录接口
     */
    @PostMapping("authofficer/login")
    public JAuthofficerLogin authofficerLogin(JAuthofficerLoginParams loginParams) throws Exception {
        return authofficeManager.login(loginParams);
    }

    /**
     * @Description 认证官免登录接口
     */
    @PostMapping("authofficer/dontLogin")
    public JAuthofficerLogin authofficerNoLogin(String token) throws Exception {
        return authofficeManager.authofficerNoLogin(token);
    }


    /**
     * @Description 创建认证官
     */
    @PostMapping("authofficer/create")
    public void createAuthofficer(JAuthofficerParams params, @RequestParam String validateCode) throws IOException {
        authofficerService.insert(params, validateCode);
    }


    /**
     * @Description 获取服务中心账户列表
     */
    @GetMapping("get/platFormUserList")
    public List<ApiPlatformUser> getCenterServer() {
        return platformUserApi.platformUserList(null, null);
    }

    /**
     * @Description 考试系统登录接口
     */
    @PostMapping("examinee/login")
    public JExamineeLogin examineeLogin(@RequestParam String name, @RequestParam String idnumber, @RequestParam String cellphone) throws Exception {
        return userManager.examineeLogin(name, idnumber, cellphone);
    }


    /**
     * @Description 查询手机号码唯一
     */
    @GetMapping("get/{cellphone}")
    public void validaty(@PathVariable String cellphone) {
        declarerService.validatyPhone(cellphone);
    }


    /**
     * @Description 查询身份证号码唯一
     */
    @GetMapping("getIdNumber/{idNumber}")
    public void validatyId(@PathVariable String idNumber) {
        declarerService.validatyIdNumber(idNumber);
    }


    /**
     * @Description 获取负责区域剩余名额
     */
    @GetMapping("areaLimit/{areaCode}")
    public Integer getLimit(@PathVariable String areaCode) {

        String substring = areaCode.substring(0, 2);
        String retrun = substring + "0000";

        AreaLimit limit = areaLimitService.getByAreaCode(retrun);
        return limit == null ? 0 : limit.getRemainNumber();
    }




    /**
     * @Description 找回密码
     */
    @PostMapping("user/password")
    public void findPassword(@RequestParam String cellphone,
                             @RequestParam String newPassword,
                             @RequestParam String confirmPassword,
                             @RequestParam String code) {
        userManager.findPassword(cellphone, newPassword, confirmPassword, code);
    }


    /**
     * @Description 获取申报官二维码图片
     */
    @GetMapping("qcode.jpg")
    public void generateCode(HttpServletResponse response, @RequestParam(required = true) Long id) throws Exception {
        Declarer declarer = declarerService.getById(id);
        AssertUtils.notNull(declarer, new CustomException("申报官不存在！"));
        BufferedImage image = QRCodeUtils.createImage(DeclarerUtils.declarerQrcode(declarer.getId()), 170);
        response.reset();
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, ImageUtils.FORMAT_NAME_JPG, out);
        response.setHeader("Content-Disposition", "attachment; filename=\"qrcode-" + id + ".jpg\"");
        response.setContentType("image/gif");
        out.flush();
        out.close();
    }

    /**
     * @Description 注册申请表
     */
    @GetMapping("register/protocol.pdf")
    public void transferPdf(HttpServletResponse response, JDeclarerRegistParams params) throws Exception {

        params.validates();
        String address = areaApi.getByCode(params.getAreaCode()).getArea();

        String[] tobereplaced = {params.getName(),params.getIdNumber(),address+params.getAddress(),params.getCellphone()};
        response.reset();
        CookieUtils.addCookies(response, "declarer_regist", params.getIdNumber(), 300);
        OutputStream out = response.getOutputStream();
        PDFUtils.fillTemplate(out, "/templates/regist.pdf", tobereplaced);
        String fileName = new String(params.getName().getBytes(), "ISO8859-1");

        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".pdf");
        ///response.addHeader("Content-Length", "");
        response.setContentType("application/octet-stream;charset=UTF-8");
        out.flush();
        out.close();

    }

    /**
     * @Description 考试系统申报官查询
     */
    @GetMapping("get/declarer")
    public Integer getDeclarer(ApiQueryParams apiQueryParams) {
        return declarerManager.getDeclarer(apiQueryParams);
    }

    public static void main(String[] args) {

    }
}