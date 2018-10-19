package com.cpsdb.declareserv.manager;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.baseservapi.api.PlatformUserApi;
import com.cpsdb.baseservapi.dto.ApiPlatformUser;
import com.cpsdb.declareapi.dto.ApiAuthofficerInfo;
import com.cpsdb.declareapi.token.AuthofficerUser;
import com.cpsdb.declareapi.utils.AuthofficerTokenUtils;
import com.cpsdb.declareserv.entity.Authofficer;
import com.cpsdb.declareserv.params.authofficer.JAuthofficerLogin;
import com.cpsdb.declareserv.params.authofficer.JAuthofficerLoginParams;
import com.cpsdb.declareserv.service.AuthofficerService;
import com.cpsdb.declareserv.utils.DeclarerUtils;
import com.cpsdb.declareserv.utils.SnUtils;
import com.cpsdb.thirdapi.api.JuHeApi;
import com.cpsdb.thirdapi.utils.ModeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthofficerManager {
    protected final Logger logger = LoggerFactory.getLogger(AuthofficerManager.class);

    @Autowired
    private AuthofficerService authofficerService;

    @Autowired
    private JuHeApi juHeApi;

    @Autowired
    private PlatformUserApi platformUserApi;

    public JAuthofficerLogin login(JAuthofficerLoginParams loginParams) throws Exception {
        loginParams.validate();
        juHeApi.vilidaty(ModeType.LOGIN, loginParams.getCellPhone(), loginParams.getCode());

        Authofficer authofficer = authofficerService.getByCellPhone(loginParams.getCellPhone());
        AssertUtils.notNull(authofficer, new CustomException("账号不存在！"));
        return new JAuthofficerLogin()
                .setToken(AuthofficerTokenUtils.encryptUserInfo(authofficer.getId().toString(), authofficer.getName()))
                .setAuthcode(authofficer.getAuthcode())
                .setAvator(authofficer.getAvator())
                .setName(authofficer.getName())
                .setIdCard(authofficer.getIdCard())
                .setOrganiz(authofficer.getOrganiz())
                .setSn(SnUtils.getAuthofficer(authofficer.getId().toString()))
                .setCode(DeclarerUtils.authofficerQrcode(authofficer.getId().longValue()))
                .setIspass(authofficer.getIspass());
    }

    public ApiAuthofficerInfo getByAuthofficerId(Long authofficerId) {
        Authofficer authofficer = authofficerService.getById(authofficerId);
        if (authofficer != null) {
            return new ApiAuthofficerInfo().setName(authofficer.getName()).setCellphone(authofficer.getTel());
        } else {
            return null;
        }
    }

    public List<ApiAuthofficerInfo> getByAuthofficerList(String name) {
        List<Authofficer> authofficerList = authofficerService.getByName(name);
        return authofficerList.stream().map(s -> new ApiAuthofficerInfo()
                .setId(s.getId().longValue())
                .setName(s.getName())
                .setCellphone(s.getTel())
        ).collect(Collectors.toList());
    }

    public JAuthofficerLogin authofficerNoLogin(String token) throws Exception {

        AuthofficerUser user = AuthofficerTokenUtils.decrypt(token);
        AssertUtils.notNull(user, new CustomException("请重新登录！"));
        Authofficer authofficer = authofficerService.getById(user.getUserId());
        return new JAuthofficerLogin()
                .setToken(AuthofficerTokenUtils.encryptUserInfo(authofficer.getId().toString(), authofficer.getName()))
                .setAuthcode(authofficer.getAuthcode())
                .setAvator(authofficer.getAvator())
                .setName(authofficer.getName())
                .setIdCard(authofficer.getIdCard())
                .setOrganiz(authofficer.getOrganiz())
                .setSn(SnUtils.getAuthofficer(authofficer.getId().toString()))
                .setCode(DeclarerUtils.authofficerQrcode(authofficer.getId().longValue()))
                .setIspass(authofficer.getIspass());
    }

    public List<ApiPlatformUser> getCenter(String name) {
        return platformUserApi.platformUserList(name);
    }

//    public void deletAuthofficer(Long authoId) {
//
//        Authofficer authofficer = authofficerService.getById(authoId);
//        AssertUtils.notNull(authofficer,new CustomException("没有该认证官！"));
//        //根据认证官ID查找该认证官是否有状态未待采集的企业ID  如果有则不能删除  没有则改变其状态为未通过
//        List<Long> authentionIds = enterpriseApi.getByenterpriseByAuthoId(authoId);
//        AssertUtils.isTrue(authentionIds.size() == 0,new CustomException("该认证官有未完成的待采集企业,不能删除！"));
//        authofficer.setIspass(false);
//        authofficerService.update(authofficer);
//    }
}