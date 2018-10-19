package com.cpsdb.declareserv.params;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @author 李银
 * @ClassName JRecommend
 * @Description
 * @date 2018-09-20 10:42:40
 */
public class JRecommendQueryParams {

    /**
     * @Description 被推荐者的类型
     */
    private Long targetType;
    /**
     * @Description 被推荐者的名称-省级、服务处、申报官名称
     */
    private String targetName;

    /**
     * @Description 被推荐者的手机号码
     */
    private String targetCellphone;
    /**
     * @Description 被推荐者申请时间起始
     */
    private Date createTimeGE;
    /**
     * @Description 被推荐者申请时间截至
     */
    private Date createTimeLE;

    /**
     * @Description 被推荐者当前审核状态： pending-审核中；passed-审核通过；unpass-审核未通过
     */
    private String state;

    /**
     * @Description 补贴状态：unwithdrawed-待提取；withdrawing-提取中；withdrawed-已提取
     */
    private String subsidyState;

    /**
     * @Description 推荐者userId
     */
    private Long recommendId;

    public Long getRecommendId() {
        return recommendId;
    }

    public JRecommendQueryParams setRecommendId(Long recommendId) {
        this.recommendId = recommendId;
        return this;
    }

    public Long getTargetType() {
        return targetType;
    }

    public JRecommendQueryParams setTargetType(Long targetType) {
        this.targetType = targetType;
        return this;
    }

    public String getTargetName() {
        return targetName;
    }

    public JRecommendQueryParams setTargetName(String targetName) {
        this.targetName = targetName;
        return this;
    }

    public String getTargetCellphone() {
        return targetCellphone;
    }

    public JRecommendQueryParams setTargetCellphone(String targetCellphone) {
        this.targetCellphone = targetCellphone;
        return this;
    }

    public Date getCreateTimeGE() {
        return createTimeGE;
    }

    public JRecommendQueryParams setCreateTimeGE(Date createTimeGE) {
        this.createTimeGE = createTimeGE;
        return this;
    }

    public Date getCreateTimeLE() {
        return createTimeLE;
    }

    public JRecommendQueryParams setCreateTimeLE(Date createTimeLE) {
        this.createTimeLE = createTimeLE;
        return this;
    }

    public String getState() {
        return this.state;
    }

    public JRecommendQueryParams setState(String state) {
        this.state = state;
        return this;
    }

    public String getSubsidyState() {
        return subsidyState;
    }

    public JRecommendQueryParams setSubsidyState(String subsidyState) {
        this.subsidyState = subsidyState;
        return this;
    }
}
