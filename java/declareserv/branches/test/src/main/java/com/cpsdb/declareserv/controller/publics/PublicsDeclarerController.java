package com.cpsdb.declareserv.controller.publics;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.baseservapi.utils.VerificationCodeUtils;
import com.cpsdb.declareserv.dto.JAppLogin;
import com.cpsdb.declareserv.dto.JDeclarerPublicList;
import com.cpsdb.declareserv.dto.JWebLogin;
import com.cpsdb.declareserv.entity.Declarer;
import com.cpsdb.declareserv.entity.Organiz;
import com.cpsdb.declareserv.entity.ProvenceCenter;
import com.cpsdb.declareserv.manager.DeclarerManager;
import com.cpsdb.declareserv.manager.UserManager;
import com.cpsdb.declareserv.params.declarer.JAppRegistParams;
import com.cpsdb.declareserv.params.declarer.JDeclarerPublicParams;
import com.cpsdb.declareserv.service.DeclarerService;
import com.cpsdb.declareserv.service.OrganizService;
import com.cpsdb.declareserv.service.ProvenceCenterService;
import com.cpsdb.declareserv.service.UserService;
import com.cpsdb.declareserv.utils.DeclarerUtils;
import com.cpsdb.declareserv.utils.OrganizUtils;
import com.cpsdb.declareserv.utils.ProvenceUtils;
import com.cpsdb.thirdapi.api.BaiduApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 李银
 * @ClassName PublicsDeclarerController
 * @Description
 * @date: 2017-12-13 10:08:08
 */
@RestController
@RequestMapping("publics/declarer")
public class PublicsDeclarerController extends BaseController {
    @Autowired
    private UserManager userManager;

    @Autowired
    private DeclarerService declarerService;

    @Autowired
    private DeclarerManager declarerManager;

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizService organizService;

    @Autowired
    private ProvenceCenterService provenceCenterService;



    /**
     * @Description CPS公共业务自主管理平台登录
     */
    @PostMapping("")
    JWebLogin webLogin(HttpServletRequest request, @RequestParam String username,
                       @RequestParam String password,
                       @RequestParam String checknumber) throws Exception {
        AssertUtils.isTrue(VerificationCodeUtils.verify(request, checknumber), new CustomException("验证码错误"));
        return userService.webLogin(username, password);
    }

    /**
     * @Description 申报系统APP登录接口
     */
    @PostMapping("login")
    public JAppLogin appLogin(@RequestParam String username, @RequestParam String password) throws Exception {
        JAppLogin jAppLogin =  userManager.appLogin(username, password);
        //兼容财务审核功能
        return jAppLogin.setState(DeclarerUtils.DeclarerState.waitPending.name());
    }

    /**
     * @Description APP查询申报官推荐
     */
    @GetMapping("{cellphone}")
    public void getDeclarerById(@PathVariable String cellphone) {
        Declarer declarer = declarerService.getByPhone(cellphone);
        Organiz organiz = organizService.getByPhone(cellphone);
        ProvenceCenter provenceCenter = provenceCenterService.getByPhone(cellphone);

        if (declarer != null && declarer.getState().equals(DeclarerUtils.DeclarerState.passed.name())) {

        } else if (organiz != null && organiz.getState().equals(OrganizUtils.OrganizState.passed.name())) {

        } else if (provenceCenter != null && provenceCenter.getState().equals(ProvenceUtils.ProvenceState.passed.name())) {

        } else {
            throw new CustomException("推荐码有误！");
        }
    }

    /**
     * @Description 申报系统APP注册接口
     */
    @PostMapping("app/regist")
    public JAppLogin regist(JAppRegistParams params) throws Exception {
        params.validate();
        return declarerManager.appRegist(params);
    }


    /**
     * @Description 申报系统APP找回密码
     */
    @PostMapping("password")
    public void editPassword(@RequestParam String cellphone,
                             @RequestParam String newPassword,
                             @RequestParam String confirmPassword,
                             @RequestParam String code) {
        userManager.findPassword(cellphone, newPassword, confirmPassword, code);
    }


    /**
     * @Description 查询申报官列表
     */
    @GetMapping("list")
    public List<JDeclarerPublicList> getDeclarerList(JDeclarerPublicParams params, DatagridPager pager) {
        return declarerManager.getPublicDeclarerList(params, pager);
    }

    /**
     * @Description 查询申报官列表总数
     */
    @GetMapping("count")
    public Long getDeclarerCount(JDeclarerPublicParams params) {
        return declarerManager.getPublicDeclarerCount(params);
    }


}