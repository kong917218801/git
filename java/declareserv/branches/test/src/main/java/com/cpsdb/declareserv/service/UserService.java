package com.cpsdb.declareserv.service;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.common.PasswordUtils;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.baseservapi.api.AreaApi;
import com.cpsdb.baseservapi.dto.ApiArea;
import com.cpsdb.declareapi.utils.DeclareTokenUtils;
import com.cpsdb.declareapi.utils.UserUtils;
import com.cpsdb.declareserv.dao.UserMapper;
import com.cpsdb.declareserv.dto.JAppLogin;
import com.cpsdb.declareserv.dto.JWebLogin;
import com.cpsdb.declareserv.entity.*;
import com.cpsdb.declareserv.utils.DeclareOrganizUtils;
import com.cpsdb.declareserv.utils.OrganizUtils;
import com.cpsdb.redis.utils.LoginSecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author 李银
 * @ClassName UserService
 * @Description
 * @date 2018-09-20 10:27:36
 */
@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrganizService organizService;

    @Autowired
    private OrganizAddressService organizAddressService;

    @Autowired
    private ProvenceCenterService provenceCenterService;

    @Autowired
    private DeclarerService declarerService;

    @Autowired
    private AreaApi areaApi;

    public void insert(User user) {
        userMapper.insert(user);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public User getById(Long id) {
        return userMapper.getById(id);
    }

    /**
     * @param username : 手机号码
     * @param password ： 密码
     * @param userType ： 用户类型
     * @param objectId ： 对应 （provence_center/declarer/organiz表中的id）
     */
    public User insert(String username, String password, UserUtils.UserType userType, Long objectId) {
        AssertUtils.isTrue(StringUtils.isNotBlank(username), new CustomException("账号名不能为空"));


        User existUser = this.getByUsername(username);
        AssertUtils.isNull(existUser, new CustomException(username + "账号已经存在"));

        String random = String.format("%d", Math.abs(new Random().nextInt()) % 99999999);
        User user = new User()
                .setUsername(username)
                .setRandom(random)
                .setPassword(PasswordUtils.encrypt(password, random))
                .setState(UserUtils.State.using.getValue())
                .setObjectId(objectId)
                .setType(userType.getValue());

        this.insert(user);
        return user;
    }

    public User getByUsername(String username) {
        return this.userMapper.getByUsername(username);
    }

    private User loginValidate(String username, String password) {
        User user = this.getByUsername(username);

        AssertUtils.notNull(user, new CustomException("用户不存在！"));
        //账户类型为2的时候不可用
        AssertUtils.isTrue(UserUtils.State.getByValue(user.getState()) == UserUtils.State.using,
                new CustomException("该账户不可用！"));
        Boolean passed = PasswordUtils.validate(password, user.getRandom(), user.getPassword());
        String key = "DECLARER_LOGIN_ERROR_COUNT_" + user.getUsername();

        AssertUtils.isTrue(LoginSecurityUtils.validateFailedCount(key, passed),
                new CustomException("密码输入次数上限，请明天再试"));
        AssertUtils.isTrue(passed, new CustomException("密码错误！"));
        return user;
    }

    public JWebLogin webLogin(String username, String password) throws Exception {
        User user = this.loginValidate(username, password);

        JWebLogin login = new JWebLogin();
        if (UserUtils.UserType.declarer.getValue() == user.getType()) {
            // 申报官账户登录
            Declarer declarer = declarerService.getById(user.getObjectId());
            login.setName(declarer.getName())
                    .setPortrait(declarer.getPortrait())
                    .setState(declarer.getState());
        } else if (UserUtils.UserType.organiz.getValue() == user.getType()) {
            //申报服务处登录
            Organiz organiz = organizService.getById(user.getObjectId());
            login.setName(organiz.getName())
                    .setState(organiz.getState());
        } else if (UserUtils.UserType.service.getValue() == user.getType()) {
            //省级服务中心登录
            ProvenceCenter provenceCenter = provenceCenterService.getById(user.getObjectId());
            login.setName(provenceCenter.getCompanyName())
                    .setAreaName(provenceCenter.getAreaCode() == null || provenceCenter.getAreaCode().equals("000000")? null:"("+ areaApi.getByCode(provenceCenter.getAreaCode()).getArea()+")")
                    .setState(provenceCenter.getState());
        } else {
            throw new CustomException("Can not happened here!");
        }

        return login.setCellphone(username)
                .setType(user.getType())
                .setId(user.getObjectId())
                .setToken(DeclareTokenUtils.encrypt(user.getType(), user.getId(), user.getObjectId(), username));
    }

    public User getByObjectId(Long objectId, UserUtils.UserType userType) {
        return userMapper.getByObjectId(objectId, userType.getValue());
    }

    public void updatePassword(User user, String password) {
        update(user.setPassword(PasswordUtils.encrypt(password, user.getRandom())));
    }

    public String getNameByOrganizId(Long organizId) {
        User user = userMapper.getById(organizId);

        if (user.getType().equals(UserUtils.UserType.declarer.getValue())) {
            return declarerService.getById(user.getObjectId()).getName();
        } else if (user.getType().equals(UserUtils.UserType.organiz.getValue())) {
            return organizService.getById(user.getObjectId()).getName();
        }
        return null;
    }

    public void updateState(Long objectId, UserUtils.UserType userType, UserUtils.State state) {
        this.userMapper.updateState(objectId, userType.getValue(), state.getValue());
    }
}