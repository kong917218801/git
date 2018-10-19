package com.cpsdb.declareserv.dto;

import java.math.BigDecimal;
import java.util.Date;

public class JRecommendDetail {

    /**
     * @Description 姓名
     */
    private String name;
    /**
     * @Description 补贴金额
     */
    private BigDecimal subsidyAmount;
    /**
     * @Description 直接推荐数
     */
    private Integer directCount;
    /**
     * @Description 简介推荐数
     */
    private Integer inDirectCount;
    /**
     * @Description 创建时间
     */
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public JRecommendDetail setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getName() {
        return name;
    }

    public JRecommendDetail setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getSubsidyAmount() {
        return subsidyAmount;
    }

    public JRecommendDetail setSubsidyAmount(BigDecimal subsidyAmount) {
        this.subsidyAmount = subsidyAmount;
        return this;
    }

    public Integer getDirectCount() {
        return directCount;
    }

    public JRecommendDetail setDirectCount(Integer directCount) {
        this.directCount = directCount;
        return this;
    }

    public Integer getInDirectCount() {
        return inDirectCount;
    }

    public JRecommendDetail setInDirectCount(Integer inDirectCount) {
        this.inDirectCount = inDirectCount;
        return this;
    }
}
