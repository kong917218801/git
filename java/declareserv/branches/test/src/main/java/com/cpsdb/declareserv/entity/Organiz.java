package com.cpsdb.declareserv.entity;

import com.cpsdb.validator.Validate;
import com.cpsdb.validator.ValidateType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

/**
 * @ClassName Organiz
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:02
 */
public class Organiz {

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
     * @Description 申报服务处名称
     */
    private String name;
    /**
     * @Description 手机号码
     */
    @Validate(type = ValidateType.cellphone)
    private String cellphone;
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
     * @Description 申报服务处注册公司全称
     */
    private String companyName;
    /**
     * @Description 未通过原因
     */
    private String reason;
    /**
     * @Description 状态：baseWaitSubmit—基本信息待填写,baseWaitPending—基本信息待初审,baseWaitAudit—基本信息待审核,baseUnPass—基本信息未通过,registWaitSubmit—登记信息待填写,registWaitPending—登记信息待初审,registWaitAudit—登记信息待审核,registUnPass—登记信息未通过,delete—已删除,passed—通过审核
     */
    private String state;
    /**
     * @Description 支付凭证
     */
    private String paymentUrl;
    /**
     * @Description 申报服务处尽职调查表
     */
    private String surveyImageUrl;
    /**
     * @Description 申报服务处承诺公函
     */
    private String letterImageUrl;
    /**
     * @Description 负责人尽职调查表
     */
    private String chargerSurveyImageUrl;
    /**
     * @Description 负责人诺公函
     */
    private String chargerImageUrl;
    /**
     * @Description 企业工商营业执照
     */
    private String commerceImageUrl;
    /**
     * @Description 其他补充材料
     */
    private String otherImageUrl;
    /**
     * @Description 基础资料填写时间
     */
    private Date baseTime;
    /**
     * @Description 登记资料填写时间
     */
    private Date registTime;
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
    /**********新增字段*******************/

    /**
     * @Description 负责区域code
     * */
    private String areaCode;
    /**
     * @Description 常驻区域code
     * */
    private String liveCode;
    /**
     * @Description 具体所在地址
     * */
    private String address;
    /**
     * @Description 省级服务中心名称
     */
    private String provenceName;



    public Long getId() {
       	return id;
    }

    public Organiz setId(Long id) {
        this.id = id;
        return this;
    }
    public Integer getVersion() {
        return version;
    }

    public Organiz setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getName() {
        return name;
    }

    public Organiz setName(String name) {
        this.name = name;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public Organiz setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public Organiz setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public Organiz setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public Organiz setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Organiz setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public Organiz setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getState() {
        return state;
    }

    public Organiz setState(String state) {
        this.state = state;
        return this;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public Organiz setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
        return this;
    }

    public String getSurveyImageUrl() {
        return surveyImageUrl;
    }

    public Organiz setSurveyImageUrl(String surveyImageUrl) {
        this.surveyImageUrl = surveyImageUrl;
        return this;
    }

    public String getLetterImageUrl() {
        return letterImageUrl;
    }

    public Organiz setLetterImageUrl(String letterImageUrl) {
        this.letterImageUrl = letterImageUrl;
        return this;
    }

    public String getChargerSurveyImageUrl() {
        return chargerSurveyImageUrl;
    }

    public Organiz setChargerSurveyImageUrl(String chargerSurveyImageUrl) {
        this.chargerSurveyImageUrl = chargerSurveyImageUrl;
        return this;
    }

    public String getChargerImageUrl() {
        return chargerImageUrl;
    }

    public Organiz setChargerImageUrl(String chargerImageUrl) {
        this.chargerImageUrl = chargerImageUrl;
        return this;
    }

    public String getCommerceImageUrl() {
        return commerceImageUrl;
    }

    public Organiz setCommerceImageUrl(String commerceImageUrl) {
        this.commerceImageUrl = commerceImageUrl;
        return this;
    }

    public String getOtherImageUrl() {
        return otherImageUrl;
    }

    public Organiz setOtherImageUrl(String otherImageUrl) {
        this.otherImageUrl = otherImageUrl;
        return this;
    }

    public Date getBaseTime() {
        return baseTime;
    }

    public Organiz setBaseTime(Date baseTime) {
        this.baseTime = baseTime;
        return this;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public Organiz setRegistTime(Date registTime) {
        this.registTime = registTime;
        return this;
    }

    public Long getFkProvenceCenterId() {
        return fkProvenceCenterId;
    }

    public Organiz setFkProvenceCenterId(Long fkProvenceCenterId) {
        this.fkProvenceCenterId = fkProvenceCenterId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Organiz setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public Organiz setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public Organiz setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getLiveCode() {
        return liveCode;
    }

    public Organiz setLiveCode(String liveCode) {
        this.liveCode = liveCode;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Organiz setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getProvenceName() {
        return provenceName;
    }

    public Organiz setProvenceName(String provenceName) {
        this.provenceName = provenceName;
        return this;
    }
}
