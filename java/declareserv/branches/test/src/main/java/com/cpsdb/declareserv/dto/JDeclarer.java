package com.cpsdb.declareserv.dto;

import java.util.Date;

/**
 * @ClassName JDeclarer
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:01
 */
public class JDeclarer {

    /**
     * @Description 主键
     */
    private Long id;

    /**
     * @Description 编号
     */
    private String sn;

    /**
     * @Description 申报官姓名
     */
    private String name;
    /**
     * @Description 手机号码
     */
    private String cellphone;
    /**
     * @Description 性别
     */
    private String sex;
    /**
     * @Description 名族
     */
    private String nation;
    /**
     * @Description 毕业院校
     */
    private String graduate;
    /**
     * @Description 学历
     */
    private String education;
    /**
     * @Description 头像
     */
    private String portrait;
    /**
     * @Description 证件号码
     */
    private String idNumber;
    /**
     * @Description 身份证正面
     */
    private String idFrontUrl;
    /**
     * @Description 身份证反面
     */
    private String idBackUrl;
    /**
     * @Description 申报官尽职调查表
     */
    private String surveyImageUrl;
    /**
     * @Description 申报官承诺公函
     */
    private String letterImageUrl;
    /**
     * @Description 状态：waitPay—待支付，waitAuthen—认证信息待填写，waitPending—待初审，waitAudit—待审核，unpass—未通过，passed—已通过，deleting—删除中，deleted—已删除
     */
    private String state;
    /**
     * @Description 未通过审核原因
     */
    private String reason;
    /**
     * @Description 常住区域
     */
    private String areaCode;
    /**
     * @Description 常住详细地址
     */
    private String address;
    /**
     * @Description 考试分数
     */
    private Long score;
    /**
     * @Description 是否通过考试
     */
    private Boolean ispass;
    /**
     * @Description 申报指南
     */
    private String declareGuideImageUrl;
    /**
     * @Description 申报服务处id
     */
    private Long fkOrganizId;
    /**
     * @Description 省级服务中心id
     */
    private Long fkProvenceCenterId;
    /**
     * @Description 申报服务处名称
     */
    private String organizName;
    /**
     * @Description 省级服务中心名称
     */
    private String serviceName;
    /**
     * @Description 
     */
    private Date createTime;
    /**
     * @Description 
     */
    private Date modifyTime;

    public String getSn() {
        return sn;
    }

    public JDeclarer setSn(String sn) {
        this.sn = sn;
        return this;
    }

    public String getOrganizName() {
        return organizName;
    }

    public JDeclarer setOrganizName(String organizName) {
        this.organizName = organizName;
        return this;
    }

    public String getServiceName() {
        return serviceName;
    }

    public JDeclarer setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public Long getId() {
       	return id;
    }

    public JDeclarer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public JDeclarer setName(String name) {
        this.name = name;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JDeclarer setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public JDeclarer setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getNation() {
        return nation;
    }

    public JDeclarer setNation(String nation) {
        this.nation = nation;
        return this;
    }

    public String getGraduate() {
        return graduate;
    }

    public JDeclarer setGraduate(String graduate) {
        this.graduate = graduate;
        return this;
    }

    public String getEducation() {
        return education;
    }

    public JDeclarer setEducation(String education) {
        this.education = education;
        return this;
    }

    public String getPortrait() {
        return portrait;
    }

    public JDeclarer setPortrait(String portrait) {
        this.portrait = portrait;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public JDeclarer setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public JDeclarer setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public JDeclarer setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }

    public String getSurveyImageUrl() {
        return surveyImageUrl;
    }

    public JDeclarer setSurveyImageUrl(String surveyImageUrl) {
        this.surveyImageUrl = surveyImageUrl;
        return this;
    }

    public String getLetterImageUrl() {
        return letterImageUrl;
    }

    public JDeclarer setLetterImageUrl(String letterImageUrl) {
        this.letterImageUrl = letterImageUrl;
        return this;
    }

    public String getState() {
        return state;
    }

    public JDeclarer setState(String state) {
        this.state = state;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public JDeclarer setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public JDeclarer setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public JDeclarer setAddress(String address) {
        this.address = address;
        return this;
    }

    public Long getScore() {
        return score;
    }

    public JDeclarer setScore(Long score) {
        this.score = score;
        return this;
    }

    public Boolean getIspass() {
        return ispass;
    }

    public JDeclarer setIspass(Boolean ispass) {
        this.ispass = ispass;
        return this;
    }

    public String getDeclareGuideImageUrl() {
        return declareGuideImageUrl;
    }

    public JDeclarer setDeclareGuideImageUrl(String declareGuideImageUrl) {
        this.declareGuideImageUrl = declareGuideImageUrl;
        return this;
    }

    public Long getFkOrganizId() {
        return fkOrganizId;
    }

    public JDeclarer setFkOrganizId(Long fkOrganizId) {
        this.fkOrganizId = fkOrganizId;
        return this;
    }

    public Long getFkProvenceCenterId() {
        return fkProvenceCenterId;
    }

    public JDeclarer setFkProvenceCenterId(Long fkProvenceCenterId) {
        this.fkProvenceCenterId = fkProvenceCenterId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JDeclarer setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public JDeclarer setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
