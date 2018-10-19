package com.cpsdb.declareserv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * @author 李银
 * @ClassName Recommend
 * @Description
 * @date 2018-09-20 17:26:02
 */
public class Recommend {

    /**
     * @Description 主键
     */
    private Long id;

    /**
     * @Description 对象版本，乐观锁需要可使用
     */
    @JsonIgnore
    private Integer version;

    /**
     * @Description 推荐者的id
     */
    private Long recommendId;
    /**
     * @Description 被推荐者id
     */
    private Long targetId;
    /**
     * @Description 被推荐者name  冗余字段
     */
    private String targetName;
    /**
     * @Description 补贴 unwithdrawed-待提取；withdrawing-提取中；withdrawed-已提取
     */
    private String subsidyState;
    /**
     * @Description
     */
    private Date createTime;
    /**
     * @Description
     */
    private Date modifyTime;

    /***********************额外字段************************/
    /**
     * @Description 被推荐者的审核状态  pending - 审核中； passed - 已通过，unpass-未通过;
     */
    private String targetState;
    private Integer targetType;
    private String targetCellphone;

    public Long getId() {
        return id;
    }

    public Recommend setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public Recommend setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public Long getRecommendId() {
        return recommendId;
    }

    public Recommend setRecommendId(Long recommendId) {
        this.recommendId = recommendId;
        return this;
    }

    public Long getTargetId() {
        return targetId;
    }

    public Recommend setTargetId(Long targetId) {
        this.targetId = targetId;
        return this;
    }

    public String getSubsidyState() {
        return subsidyState;
    }

    public Recommend setSubsidyState(String subsidyState) {
        this.subsidyState = subsidyState;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Recommend setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public Recommend setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public String getTargetName() {
        return targetName;
    }

    public Recommend setTargetName(String targetName) {
        this.targetName = targetName;
        return this;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public Recommend setTargetType(Integer targetType) {
        this.targetType = targetType;
        return this;
    }

    public String getTargetCellphone() {
        return targetCellphone;
    }

    public Recommend setTargetCellphone(String targetCellphone) {
        this.targetCellphone = targetCellphone;
        return this;
    }

    public String getTargetState() {
        return targetState;
    }

    public Recommend setTargetState(String targetState) {
        this.targetState = targetState;
        return this;
    }
}
