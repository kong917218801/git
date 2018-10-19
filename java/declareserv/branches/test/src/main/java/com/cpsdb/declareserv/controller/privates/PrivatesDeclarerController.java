package com.cpsdb.declareserv.controller.privates;

import com.cpsdb.base.controller.BaseController;
import com.cpsdb.declareapi.dto.ApiAuthofficerInfo;
import com.cpsdb.declareapi.dto.ApiDeclareInfo;
import com.cpsdb.declareapi.dto.ApiDeclarerInfo;
import com.cpsdb.declareapi.params.ApiQueryParams;
import com.cpsdb.declareapi.params.ApiScoreParams;
import com.cpsdb.declareserv.dao.DeclareEnterpriseMapper;
import com.cpsdb.declareserv.manager.AuthofficerManager;
import com.cpsdb.declareserv.manager.DeclarerManager;
import com.cpsdb.declareserv.service.DeclareToEnterpriseService;
import com.cpsdb.declareserv.service.RecommendService;
import com.cpsdb.declareserv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李银
 * @ClassName PrivateDeclarerController
 * @Description:
 * @date: 2017-12-03 15:00:38
 */
@RestController
@RequestMapping("privates")
public class PrivatesDeclarerController extends BaseController {


    @Autowired
    private DeclarerManager declarerManager;

    @Autowired
    private AuthofficerManager authofficerManager;

    @Autowired
    private UserService userService;

    @Autowired
    private DeclareToEnterpriseService declareToEnterpriseService;

    @Autowired
    private DeclareEnterpriseMapper declareEnterpriseMapper;

    @Autowired
    private RecommendService recommendService;


    /**
     * @Description 申报官考试分数
     */
    @PostMapping("declarer/score")
    public Boolean declarerScore(ApiScoreParams params) {
        return declarerManager.declarerScore(params);
    }

    /**
     * @Description 考试系统申报官查询
     */
    @GetMapping("get/declarer")
    public Integer getDeclarer(ApiQueryParams apiQueryParams) {
        return declarerManager.getDeclarer(apiQueryParams);
    }


    /**
     * @Description 根据用户ID查找申报官信息
     */
    @GetMapping("get/declarerInfo")
    public ApiDeclarerInfo getDeclarer(Long userId) {
        return declarerManager.getDeclarers(userId);
    }


    /**
     * @Description 根据企业ID查找申报官信息
     */
    @GetMapping("get/declarer/byEnterpriserId")
    public ApiDeclareInfo getByEnterpriserId(Long enterpriseId) {
        return declarerManager.getByEnterpriserId(enterpriseId);
    }

    /**
     * @Description 根据企业ID查找认证官信息
     */
    @GetMapping("get/authofficer/byAuthofficerId")
    public ApiAuthofficerInfo getByAuthofficerId(Long authofficerId) {
        return authofficerManager.getByAuthofficerId(authofficerId);
    }

    /**
     * @Description 根据用户ID查找用户类型
     */
    @GetMapping("get/type/byUserId")
    public Integer getTypeByUserID(Long userId) {
        return userService.getById(userId).getType();
    }

    /**
     * @Description 根据用户ID查找该用户下面的所有的企业ID
     */
    @GetMapping("get/enterpriseId/byUserId")
    public List<Long> getEnterpriseIdUserId(Long userId) {
        return declareToEnterpriseService.getEnterpriseIdUserId(userId);
    }

    /**
     * @Description 根据企业名称查找申报企业
     */
    @GetMapping("get/enterprise/name")
    public Boolean getEnterpriseIdUserId(String name) {
        return null != declareEnterpriseMapper.getByEnterpriseName(name);
    }


    /**
     * @Description 根据用户Id查找具体返回的姓名
     */
    @GetMapping("get/username")
    public String getNameByOrganizId(Long organizId) {
        return userService.getNameByOrganizId(organizId);
    }


    /**
     * @Description 根据申报官ID更新推荐状态
     */
    @PostMapping("update/toDeclarer")
    public Boolean updateState(@RequestParam(value = "stateTo") String stateTo,
                               @RequestParam(value = "userId") Long userId,
                               @RequestParam(value = "declarerId") List<Long> declarerId) {
        return recommendService.updateState(stateTo, userService.getById(userId).getObjectId(), declarerId);
    }
}
