package com.cpsdb.declareserv.controller.declarerapp;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.common.CookieUtils;
import com.cpsdb.base.common.ImageUtils;
import com.cpsdb.base.common.QRCodeUtils;
import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.base.exception.NoPermissionException;
import com.cpsdb.declareapi.holder.DeclareHolder;
import com.cpsdb.declareapi.utils.UserUtils;
import com.cpsdb.declareserv.dto.JAppLogin;
import com.cpsdb.declareserv.dto.JDeclarer;
import com.cpsdb.declareserv.entity.Declarer;
import com.cpsdb.declareserv.entity.Organiz;
import com.cpsdb.declareserv.entity.User;
import com.cpsdb.declareserv.manager.DeclarerManager;
import com.cpsdb.declareserv.manager.OrderManager;
import com.cpsdb.declareserv.params.declarer.*;
import com.cpsdb.declareserv.service.DeclarerService;
import com.cpsdb.declareserv.service.OrganizService;
import com.cpsdb.declareserv.service.UserService;
import com.cpsdb.declareserv.utils.DeclarerUtils;
import com.cpsdb.declareserv.utils.PDFUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

/**
 * @author 孔清
 * @ClassName DeclarerAppDeclarerController
 * @Description
 * @date: 2018-09-05 15:00:38
 */
@RestController
@RequestMapping("declarerapp/declarer")
public class DeclarerAppDeclarerController extends BaseController {

    @Autowired
    private OrderManager orderManager;

    @Autowired
    private DeclarerManager declarerManager;

    @Autowired
    private UserService userService;

    @Autowired
    private DeclarerService declarerService;

    @Autowired
    private OrganizService organizService;

    /**
     * @Description 申报官申请服务协议
     */
    @GetMapping("service/agreement.pdf")
    public void transferPdf(HttpServletResponse response) throws Exception {
        Long userId = DeclareHolder.getUserId();
        Long declarerId = userService.getById(userId).getObjectId();
        Declarer declarer = declarerService.getById(declarerId);
        String[] tobereplaced = {declarer.getName(), declarer.getIdNumber(), declarer.getCellphone()};
        response.reset();
        CookieUtils.addCookies(response, "declarer_regist", declarer.getIdNumber(), 300);
        OutputStream out = response.getOutputStream();
        PDFUtils.fillTemplate(out, "/templates/regist_app.pdf", tobereplaced);
        String fileName = new String(declarer.getName().getBytes(), "ISO8859-1");

        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".pdf");
        response.setContentType("application/x-msdownload;charset=UTF-8");
        out.flush();
        out.close();
    }

    /**
     * @Description 获取申报官的二维码
     */
    @GetMapping("qcode.jpg")
    public void generateCode(HttpServletResponse response) throws Exception {
        Long userId = DeclareHolder.getUserId();
        Long declarerId = userService.getById(userId).getObjectId();
        Declarer declarer = declarerService.getById(declarerId);
        AssertUtils.notNull(declarer, new CustomException("申报官不存在！"));
        BufferedImage image = QRCodeUtils.createImage(DeclarerUtils.declarerQrcode(declarer.getId()), 170);
        response.reset();
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, ImageUtils.FORMAT_NAME_JPG, out);
        response.setHeader("Content-Disposition", "attachment; filename=\"qrcode-" + declarer.getId() + ".jpg\"");
        response.setContentType("image/gif");
        out.flush();
        out.close();
    }

    /**
     * @Description 申报系统APP账户中心
     */
    @GetMapping("account")
    public JAppLogin account() throws Exception {
        Long userId = DeclareHolder.getUserId();
        User user = userService.getById(userId);
        Declarer declarer = declarerService.getById(user.getObjectId());
        Organiz organiz = organizService.getById(declarer.getFkOrganizId());

        //兼容财务审核功能
        JAppLogin jAppLogin = declarerManager.account(user, declarer, organiz);
        return jAppLogin.setState(DeclarerUtils.DeclarerState.waitPending.name());
    }

    /**
     * @return 浏览器打开返回的url
     * @Description 申报官注册缴费支付
     */
    @PostMapping("order/pay")
    public String pay(JPayParams params, JInvoiceParams JinvoiceParams) {
        AssertUtils.isTrue(DeclareHolder.getUserType() == UserUtils.UserType.declarer, new NoPermissionException());
        return orderManager.pay(DeclareHolder.getObjectId(), params, JinvoiceParams);
    }

    /**
     * @Description 申报官注册缴费检查订单支付是否成功
     */
    @GetMapping("order/check")
    public Boolean checkOrderSuccess() {
        Long userId = DeclareHolder.getUserId();
        Long declarerId = userService.getById(userId).getObjectId();
        return orderManager.checkOrderSuccess(declarerId);
    }

    /**
     * @Description 申报官基本信息认证
     * @Required portraitFile
     * @Required signFile
     */
    @PostMapping("authentication")
    public void authentication(JDeclarerAuthenParams params, @RequestParam("portraitFile") MultipartFile portraitFile, @RequestParam("signFile") MultipartFile signFile, DeclarerSignParams signParams) throws Exception {
        Long userId = DeclareHolder.getUserId();
        Long declarerId = userService.getById(userId).getObjectId();
        declarerManager.authentication(declarerId, params, portraitFile, signFile, signParams);
    }

    /**
     * @Description 申报官获取认证信息
     */
    @GetMapping("get/authentication")
    public JDeclarerDetail getAuthentication() {
        return declarerManager.declarerDetails(DeclareHolder.getObjectId());
    }

    /**
     * @Description 申报官APP获取考试信息
     */
    @GetMapping("get/examination")
    public JDeclarer examination() {
        Long userId = DeclareHolder.getUserId();
        Long declarerId = userService.getById(userId).getObjectId();
        Declarer declarer = declarerService.getById(declarerId);
        return new JDeclarer()
                .setName(declarer.getName())
                .setState(declarer.getState())
                .setIdNumber(declarer.getIdNumber())
                .setCellphone(declarer.getCellphone());
    }
}
