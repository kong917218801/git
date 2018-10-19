package com.cpsdb.declareserv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

/**
 * @ClassName Declarer
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:01
 */
public class Declarer {

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
     * @Description 
     */
    private Date createTime;
    /**
     * @Description 
     */
    private Date modifyTime;

    /**
     * @Description 服务处名称
     */
    private String organizName;
    /**
     * @Description 服务中心所属区域
     */
    private String organizAreaCode;
    /**
     * @Description 服务中心详细地址
     */
    private String organizAddress;

    /**
     * @Description 注册方式:publics("普通注册")，other("其他注册")
     */
    private String registMethod;

    /**
     * @Description 服务中心名称
     */
    private String serviceName;

    /**
     * @Description 支付凭证
     */
    private String paymentUrl;

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public Declarer setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
        return this;
    }

    public String getServiceName() {
        return serviceName;
    }

    public Declarer setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public String getRegistMethod() {
        return registMethod;
    }

    public Declarer setRegistMethod(String registMethod) {
        this.registMethod = registMethod;
        return this;
    }

    public String getOrganizAreaCode() {
        return organizAreaCode;
    }

    public Declarer setOrganizAreaCode(String organizAreaCode) {
        this.organizAreaCode = organizAreaCode;
        return this;
    }

    public String getOrganizAddress() {
        return organizAddress;
    }

    public Declarer setOrganizAddress(String organizAddress) {
        this.organizAddress = organizAddress;
        return this;
    }

    public String getOrganizName() {
        return organizName;
    }

    public Declarer setOrganizName(String organizName) {
        this.organizName = organizName;
        return this;
    }

    public Long getId() {
       	return id;
    }

    public Declarer setId(Long id) {
        this.id = id;
        return this;
    }
    public Integer getVersion() {
        return version;
    }

    public Declarer setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getName() {
        return name;
    }

    public Declarer setName(String name) {
        this.name = name;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public Declarer setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public Declarer setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getNation() {
        return nation;
    }

    public Declarer setNation(String nation) {
        this.nation = nation;
        return this;
    }

    public String getGraduate() {
        return graduate;
    }

    public Declarer setGraduate(String graduate) {
        this.graduate = graduate;
        return this;
    }

    public String getEducation() {
        return education;
    }

    public Declarer setEducation(String education) {
        this.education = education;
        return this;
    }

    public String getPortrait() {
        return portrait;
    }

    public Declarer setPortrait(String portrait) {
        this.portrait = portrait;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public Declarer setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public Declarer setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public Declarer setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }

    public String getSurveyImageUrl() {
        return surveyImageUrl;
    }

    public Declarer setSurveyImageUrl(String surveyImageUrl) {
        this.surveyImageUrl = surveyImageUrl;
        return this;
    }

    public String getLetterImageUrl() {
        return letterImageUrl;
    }

    public Declarer setLetterImageUrl(String letterImageUrl) {
        this.letterImageUrl = letterImageUrl;
        return this;
    }

    public String getState() {
        return state;
    }

    public Declarer setState(String state) {
        this.state = state;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public Declarer setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public Declarer setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Declarer setAddress(String address) {
        this.address = address;
        return this;
    }

    public Long getScore() {
        return score;
    }

    public Declarer setScore(Long score) {
        this.score = score;
        return this;
    }

    public Boolean getIspass() {
        return ispass;
    }

    public Declarer setIspass(Boolean ispass) {
        this.ispass = ispass;
        return this;
    }

    public String getDeclareGuideImageUrl() {
        return declareGuideImageUrl;
    }

    public Declarer setDeclareGuideImageUrl(String declareGuideImageUrl) {
        this.declareGuideImageUrl = declareGuideImageUrl;
        return this;
    }

    public Long getFkOrganizId() {
        return fkOrganizId;
    }

    public Declarer setFkOrganizId(Long fkOrganizId) {
        this.fkOrganizId = fkOrganizId;
        return this;
    }

    public Long getFkProvenceCenterId() {
        return fkProvenceCenterId;
    }

    public Declarer setFkProvenceCenterId(Long fkProvenceCenterId) {
        this.fkProvenceCenterId = fkProvenceCenterId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Declarer setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public Declarer setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
