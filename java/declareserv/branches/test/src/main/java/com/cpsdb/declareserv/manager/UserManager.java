package com.cpsdb.declareserv.manager;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.common.PasswordUtils;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.declareapi.utils.DeclareTokenUtils;
import com.cpsdb.declareapi.utils.UserUtils;
import com.cpsdb.declareserv.dao.DeclarerMapper;
import com.cpsdb.declareserv.dto.JAppLogin;
import com.cpsdb.declareserv.entity.Declarer;
import com.cpsdb.declareserv.entity.Organiz;
import com.cpsdb.declareserv.entity.Recommend;
import com.cpsdb.declareserv.entity.User;
import com.cpsdb.declareserv.params.declarer.JExamineeLogin;
import com.cpsdb.declareserv.service.DeclarerService;
import com.cpsdb.declareserv.service.OrganizService;
import com.cpsdb.declareserv.service.RecommendService;
import com.cpsdb.declareserv.service.UserService;
import com.cpsdb.declareserv.utils.DeclareConfigUtils;
import com.cpsdb.declareserv.utils.DeclarerUtils;
import com.cpsdb.declareserv.utils.SnUtils;
import com.cpsdb.examinationapi.api.DeclareCheckApi;
import com.cpsdb.redis.tool.RedisProxy;
import com.cpsdb.redis.utils.LoginSecurityUtils;
import com.cpsdb.thirdapi.api.JuHeApi;
import com.cpsdb.thirdapi.utils.ModeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    @Autowired
    private UserService userService;

    @Autowired
    private DeclarerService declarerService;

    @Autowired
    private OrganizService organizService;

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private DeclareCheckApi declareCheckApi;

    @Autowired
    private RedisProxy redisProxy;

    @Autowired
    private JuHeApi juHeApi;

    @Autowired
    private DeclarerMapper declarerMapper;

    //移动端APP登录
    public JAppLogin appLogin(String username, String password) throws Exception {

        User user = userService.getByUsername(username);
        AssertUtils.notNull(user, new CustomException("用户名不存在！"));
        //账户类型为2的时候不可用
        Boolean passed = PasswordUtils.validate(password, user.getRandom(), user.getPassword());
        String key = "DECLARER_LOGIN_ERROR_COUNT_" + user.getUsername();

        AssertUtils.isTrue(LoginSecurityUtils.validateFailedCount(key, passed),
                new CustomException("密码输入次数上限，请明天再试"));
        AssertUtils.isTrue(passed, new CustomException("密码错误！"));

        if (user.getType() == UserUtils.UserType.declarer.getValue()) {
            Declarer declarer = declarerService.getById(user.getObjectId());
            Organiz organiz = organizService.getById(declarer.getFkOrganizId());
            //获取推荐人工号
            Recommend recommend = recommendService.getByTargetUserId(user.getId());
            return new JAppLogin()
                    .setState(declarer.getState())
                    .setAmount(DeclareConfigUtils.getBigDecimal("pay.declarer.amount"))
                    .setSn(SnUtils.getDeclarerSn(declarer.getId().toString()))
                    .setOrganiz(organiz.getCompanyName())
                    .setType(user.getType())
                    .setExamination(declarer.getState().equals(DeclarerUtils.DeclarerState.passed.name())? declareCheckApi.check(user.getId(), declarer.getScore().intValue(), declarer.getCreateTime().getTime()):false)
                    .setRecommendId(user.getUsername())
                    .setRecommendName(recommend == null ? null : userService.getById(recommend.getRecommendId()).getUsername())
                    .setName(declarer.getName())
                    .setScore(declarer.getScore().toString())
                    .setReason(declarer.getReason())
                    .setCode(DeclarerUtils.declarerQrcode(declarer.getId()))
                    .setPortrait(declarer.getPortrait())
                    .setToken(DeclareTokenUtils.encrypt(user.getType(), user.getId(), user.getObjectId(), user.getUsername()));
        }
        return null;
    }

    public void findPassword(String cellphone, String newPassword, String confirmPassword, String code) {
        AssertUtils.isTrue(StringUtils.isNotBlank(newPassword), new CustomException("密码不能为空"));
        AssertUtils.isTrue(newPassword.equals(confirmPassword), new CustomException("两次输入的密码不一致"));
        AssertUtils.notNull(userService.getByUsername(cellphone), new CustomException("没有该手机号码！"));

        //控制修改密码时候的次数
        String keyCount = ModeType.FIND + "_vilidaty_" + cellphone;
        //获取发送次数
        String keyCounts = redisProxy.get(keyCount);
        Integer sCount = 0;
        if (keyCounts == null || keyCounts.isEmpty()) {
            keyCounts = 0 + "";
        } else {
            sCount = Integer.valueOf(keyCounts);
            sCount = sCount + 1;
        }
        //获取指定时间发送次数
        int count = 5;
        if (sCount < count) {
            redisProxy.set(keyCount, String.valueOf(sCount), 3600L);
        } else {
            throw new CustomException("错误次数上限，请联系客服人员！");
        }

        juHeApi.vilidaty(ModeType.FIND, cellphone, code);

        User user = userService.getByUsername(cellphone);
        userService.updatePassword(user, newPassword);
    }

    public JExamineeLogin examineeLogin(String name, String idnumber, String cellphone) throws Exception {

        Declarer declarer = declarerMapper.getByExaminee(name, idnumber, cellphone);
        AssertUtils.notNull(declarer, new CustomException("该申报官审核未通过！"));
        User user = userService.getByObjectId(declarer.getId(),UserUtils.UserType.declarer);
        AssertUtils.notNull(declarer, new CustomException("对不起，您没有资格参加考试！"));
        return new JExamineeLogin()
                .setName(declarer.getName())
                .setScore(declarer.getScore().intValue())
                .setToken(DeclareTokenUtils.encrypt(user.getType(),user.getId(),user.getObjectId(),user.getUsername()));
    }
}
