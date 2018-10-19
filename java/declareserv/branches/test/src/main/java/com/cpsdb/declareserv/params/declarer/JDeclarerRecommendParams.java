package com.cpsdb.declareserv.params.declarer;

import java.util.Date;

public class JDeclarerRecommendParams {

    /**
     * @Description id
     */
    private Long id;
    /**
     * @Description 姓名
     */
    private String name;
    /**
     * @Description 电话号码
     */
    private String cellphone;
    /**
     * @Description 开始时间
     */
    private Date startDate;
    /**
     * @Description 结束时间
     */
    private Date endDate;
    /**
     * @Description 申报官状态
     */
    private String declarerState;
    /**
     * @Description 推荐人
     */
    private String recommender;
    /**
     * @Description 补贴状态
     */
    private String subsidyState;

    public Long getId() {
        return id;
    }

    public JDeclarerRecommendParams setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public JDeclarerRecommendParams setName(String name) {
        this.name = name;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JDeclarerRecommendParams setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public JDeclarerRecommendParams setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public JDeclarerRecommendParams setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public String getDeclarerState() {
        return declarerState;
    }

    public JDeclarerRecommendParams setDeclarerState(String declarerState) {
        this.declarerState = declarerState;
        return this;
    }

    public String getRecommender() {
        return recommender;
    }

    public JDeclarerRecommendParams setRecommender(String recommender) {
        this.recommender = recommender;
        return this;
    }

    public String getSubsidyState() {
        return subsidyState;
    }

    public JDeclarerRecommendParams setSubsidyState(String subsidyState) {
        this.subsidyState = subsidyState;
        return this;
    }
}
