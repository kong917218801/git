package com.cpsdb.declareserv.service;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.base.pager.Pageable;
import com.cpsdb.declareapi.utils.UserUtils.UserType;
import com.cpsdb.declareserv.dao.RecommendMapper;
import com.cpsdb.declareserv.entity.*;
import com.cpsdb.declareserv.params.JRecommendQueryParams;
import com.cpsdb.declareserv.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @author 李银
 * @ClassName RecommendService
 * @Description
 * @date 2018-09-20 10:27:53
 */
@Service
public class RecommendService {

    @Autowired
    private RecommendMapper recommendMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private DeclarerService declarerService;

    @Autowired
    private OrganizService organizService;

    @Autowired
    private ProvenceCenterService provenceCenterService;

    @Autowired
    private SubsidyDetailService subsidyDetailService;

    private Recommend insert(Recommend recommend) {
        recommendMapper.insert(recommend);
        return recommend;
    }

    private void update(Recommend recommend) {
        recommendMapper.update(recommend);
    }

    public Recommend getById(Long id) {
        return recommendMapper.getById(id);
    }

    /**
     * @param recommendUserCellphone 推荐者的账户：（user表中的username）
     * @param recommendUserCellphone 被推荐者的账户：（user表中的username）
     */
    public Recommend insert(String recommendUserCellphone, String targetUserCellphone) {
        AssertUtils.notNull(recommendUserCellphone, new CustomException("无效推荐码"));
        AssertUtils.notNull(targetUserCellphone, new CustomException("非法参数"));

        User recommendUser = userService.getByUsername(recommendUserCellphone);
        User targetUser = userService.getByUsername(targetUserCellphone);

        return this.insert(recommendUser, targetUser);
    }

    /**
     * @param recommendUser 推荐者的账户 填写的推荐号
     * @param targetUser    被推荐者的账户 我的账号
     */
    public Recommend insert(User recommendUser, User targetUser) {
        AssertUtils.notNull(recommendUser, new CustomException("无效推荐码！"));
        AssertUtils.notNull(targetUser, new CustomException("非法参数！"));

        // 判断推荐者的状态是否审核通过
        this._validateState(recommendUser);

        // 获取被推荐者的name
        String name = this._getTargetName(targetUser);

        // 省级可以推荐省级、服务处、申报官；服务处可以推荐服务处、申报官；申报官只能推荐申报官
        Set<UserType> availableTypes = RecommendUtils.RECOMMEND_RELATIONSHIP_GRAPH.get(UserType.getByValue(recommendUser.getType()));
        AssertUtils.isTrue(availableTypes.contains(UserType.getByValue(targetUser.getType())), new CustomException("无效推荐码！"));

        // 插入记录
        return this.insert(new Recommend().setRecommendId(recommendUser.getId())
                .setTargetId(targetUser.getId())
                .setTargetName(name)
                .setTargetState(RecommendUtils.TargetState.pending.name()));
    }

    private String _getTargetName(User targetUser) {
        if (UserType.declarer.getValue() == targetUser.getType()) {
            return this.declarerService.getById(targetUser.getObjectId()).getName();
        } else if (UserType.organiz.getValue() == targetUser.getType()) {
            return this.organizService.getById(targetUser.getObjectId()).getName();
        } else if (UserType.service.getValue() == targetUser.getType()) {
            return this.provenceCenterService.getById(targetUser.getObjectId()).getCompanyName();
        } else {
            throw new CustomException("could not happened here!");
        }
    }

    private void _validateState(User recommendUser) {
        if (UserType.declarer.getValue() == recommendUser.getType()) {
            Declarer declarer = this.declarerService.getById(recommendUser.getObjectId());
            AssertUtils.isTrue(DeclarerUtils.DeclarerState.passed.name().equals(declarer.getState()),
                    new CustomException("无效推荐码！"));
        } else if (UserType.organiz.getValue() == recommendUser.getType()) {
            Organiz organiz = this.organizService.getById(recommendUser.getObjectId());
            AssertUtils.isTrue(OrganizUtils.OrganizState.passed.name().equals(organiz.getState()),
                    new CustomException("无效推荐码！"));
        } else if (UserType.service.getValue() == recommendUser.getType()) {
            ProvenceCenter provenceCenter = this.provenceCenterService.getById(recommendUser.getObjectId());
            AssertUtils.isTrue(ProvenceUtils.ProvenceState.passed.name().equals(provenceCenter.getState()),
                    new CustomException("无效推荐码！"));
        } else {
            throw new CustomException("could not happened here!");
        }
    }

    /**
     * 当省级、服务处、申报官被审核通过、或者不通过时，调用此方法
     * TODO
     *
     * @param targetUserId ：被推荐者id
     * @param state
     */
    public void updateTargetState(Long targetUserId, RecommendUtils.TargetState state) {
        if (state == RecommendUtils.TargetState.passed) {
            Recommend recommend = this.getByTargetUserId(targetUserId);
            if (recommend != null) {
                this.subsidyDetailService.insert(
                        SubsidyWithdrawUtils.DetailType.推荐补贴,
                        recommend.getRecommendId(),
                        targetUserId,
                        recommend.getTargetName(),
                        1,
                        _getSubsidyAmount(recommend),
                        BigDecimal.ONE
                );
            }
        } else if (state == RecommendUtils.TargetState.unpass) {
            // 未通过不做啥事
        } else {
            throw new CustomException("could not happened here!");
        }
        this.recommendMapper.updateTargetState(targetUserId, state.name());
    }

    private BigDecimal _getSubsidyAmount(Recommend recommend) {
        User user = userService.getById(recommend.getTargetId());
        if (UserType.declarer.getValue() == user.getType()) {
            return DeclareConfigUtils.getBigDecimal("recommend.subsidy.declarer");
        } else if (UserType.organiz.getValue() == user.getType()) {
            return DeclareConfigUtils.getBigDecimal("recommend.subsidy.organiz");
        } else if (UserType.service.getValue() == user.getType()) {
            ProvenceCenter center = this.provenceCenterService.getById(user.getObjectId());
            return DeclareConfigUtils.getBigDecimal("recommend.subsidy.service." + center.getType().toLowerCase());
        } else {
            throw new CustomException("could not happened here!");
        }
    }

    public static void main(String[] args) {
        System.out.println(DeclareConfigUtils.getBigDecimal("recommend.subsidy.service.A"));
        System.out.println(DeclareConfigUtils.getBigDecimal("recommend.subsidy.service.a"));
    }

    public Recommend getByTargetUserId(Long targetUserId) {
        return recommendMapper.getByTargetId(targetUserId);
    }

    public List<Recommend> query(JRecommendQueryParams params, Pageable pager) {
        return this.recommendMapper.query(params, pager);
    }

    public Integer count(JRecommendQueryParams params) {
        return this.recommendMapper.count(params);
    }

    public Boolean updateState(String stateTo, Long userId, List<Long> declarerIds) {
        recommendMapper.branchParent(stateTo, userId, declarerIds);
        return true;
    }

    /**
     * 获取推荐者的用户名
     * @param targetUserId ： 被推荐者的userid
     * @return
     */
    public String getUserName(Long targetUserId){return recommendMapper.getUserName(targetUserId);}
}