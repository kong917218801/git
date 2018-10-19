package com.cpsdb.declareserv.dto;

import java.util.Date;

/**
 * @ClassName JOrganizRecommend
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:02
 */
public class JOrganizRecommend {

    /**
     * @Description 主键
     */
    private Long id;
    /**
     * @Description 推荐者的id
     */
    private Long recommendId;
    /**
     * @Description 被推荐者的类型
     */
    private Integer targetType;
    /**
     * @Description 被推荐者id
     */
    private Long targetId;
    /**
     * @Description 被推荐者的名称
     */
    private String targetName;
    /**
     * @Description 被推荐者的手机号码
     */
    private String targetCellphone;
    /**
     * @Description 补贴 提取状态：applied -- 已提取, unapply--未提取，pending -- 审核中
     */
    private String subsidyState;
    /**
     * @Description 
     */
    private Date createTime;

    public Long getId() {
       	return id;
    }

    public JOrganizRecommend setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getRecommendId() {
        return recommendId;
    }

    public JOrganizRecommend setRecommendId(Long recommendId) {
        this.recommendId = recommendId;
        return this;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public JOrganizRecommend setTargetType(Integer targetType) {
        this.targetType = targetType;
        return this;
    }

    public Long getTargetId() {
        return targetId;
    }

    public JOrganizRecommend setTargetId(Long targetId) {
        this.targetId = targetId;
        return this;
    }

    public String getTargetName() {
        return targetName;
    }

    public JOrganizRecommend setTargetName(String targetName) {
        this.targetName = targetName;
        return this;
    }

    public String getSubsidyState() {
        return subsidyState;
    }

    public JOrganizRecommend setSubsidyState(String subsidyState) {
        this.subsidyState = subsidyState;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JOrganizRecommend setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getTargetCellphone() {
        return targetCellphone;
    }

    public JOrganizRecommend setTargetCellphone(String targetCellphone) {
        this.targetCellphone = targetCellphone;
        return this;
    }
}
