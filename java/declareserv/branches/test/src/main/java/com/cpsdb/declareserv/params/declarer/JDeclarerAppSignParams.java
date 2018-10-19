package com.cpsdb.declareserv.params.declarer;

import com.cpsdb.validator.Validate;

public class JDeclarerAppSignParams {
    /**
     * @Description 姓名
     *
     */
    @Validate
    private String name;

    /**
     * @Description 性别
     */
    @Validate
    private String sex;

    /**
     * @Description 民族
     */
    @Validate
    private String volk;

    /**
     * @Description 地址
     */
    @Validate
    private String address;

    /**
     * @Description 学历
     */
    @Validate
    private String education;

    /**
     * @Description 毕业院校
     */
    @Validate
    private String university;

    /**
     * @Description 简介
     */
    @Validate
    private String profile;

    /**
     * @Description 法院判决
     */
    @Validate
    private boolean judicialDecision;

    /**
     * @Description 法院执行
     */
    @Validate
    private boolean judicialExecution;

    /**
     * @Description 行政处罚
     */
    @Validate
    private boolean administrativePenalty;

    /**
     * @Description 欠缴税款
     */
    @Validate
    private boolean arrearsOfTaxes;

    /**
     * @Description 是否有荣誉记录
     */
    @Validate
    private boolean hasHonor;

    /**
     * @Description 是否有获奖
     */
    @Validate
    private boolean hasAward;

    /**
     * @Description 荣誉说明
     */
    @Validate(lengthMax = 150)
    private String honorDetail;

    /**
     * @Description 原因
     */
    @Validate(lengthMax = 150)
    private String reason;

    public String getName() {
        return name;
    }

    public JDeclarerAppSignParams setName(String name) {
        this.name = name;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public JDeclarerAppSignParams setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getVolk() {
        return volk;
    }

    public JDeclarerAppSignParams setVolk(String volk) {
        this.volk = volk;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public JDeclarerAppSignParams setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEducation() {
        return education;
    }

    public JDeclarerAppSignParams setEducation(String education) {
        this.education = education;
        return this;
    }

    public String getUniversity() {
        return university;
    }

    public JDeclarerAppSignParams setUniversity(String university) {
        this.university = university;
        return this;
    }

    public String getProfile() {
        return profile;
    }

    public JDeclarerAppSignParams setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    public boolean isJudicialDecision() {
        return judicialDecision;
    }

    public JDeclarerAppSignParams setJudicialDecision(boolean judicialDecision) {
        this.judicialDecision = judicialDecision;
        return this;
    }

    public boolean isJudicialExecution() {
        return judicialExecution;
    }

    public JDeclarerAppSignParams setJudicialExecution(boolean judicialExecution) {
        this.judicialExecution = judicialExecution;
        return this;
    }

    public boolean isAdministrativePenalty() {
        return administrativePenalty;
    }

    public JDeclarerAppSignParams setAdministrativePenalty(boolean administrativePenalty) {
        this.administrativePenalty = administrativePenalty;
        return this;
    }

    public boolean isArrearsOfTaxes() {
        return arrearsOfTaxes;
    }

    public JDeclarerAppSignParams setArrearsOfTaxes(boolean arrearsOfTaxes) {
        this.arrearsOfTaxes = arrearsOfTaxes;
        return this;
    }

    public boolean isHasHonor() {
        return hasHonor;
    }

    public JDeclarerAppSignParams setHasHonor(boolean hasHonor) {
        this.hasHonor = hasHonor;
        return this;
    }

    public boolean isHasAward() {
        return hasAward;
    }

    public JDeclarerAppSignParams setHasAward(boolean hasAward) {
        this.hasAward = hasAward;
        return this;
    }

    public String getHonorDetail() {
        return honorDetail;
    }

    public JDeclarerAppSignParams setHonorDetail(String honorDetail) {
        this.honorDetail = honorDetail;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public JDeclarerAppSignParams setReason(String reason) {
        this.reason = reason;
        return this;
    }
}
