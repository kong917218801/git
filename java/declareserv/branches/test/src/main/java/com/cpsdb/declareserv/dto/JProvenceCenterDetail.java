package com.cpsdb.declareserv.dto;

import java.util.Date;

/**
 * @author 老周
 * @ClassName JProvenceCenter
 * @Description
 * @date 2018-09-20 17:26:02
 */
public class JProvenceCenterDetail {

    /**
     * @Description 主键
     */
    private Long id;

    /**
     * @Description 手机号码
     * @Required
     */
    private String cellphone;
    /**
     * @Description 负责人姓名
     * @Required
     */
    private String chargerName;

    /**
     * @Description 授权类型：a-A类, b-B类, c-C类，d-D类
     */
    private String type;
    /**
     * @Description 公司名称
     * @Required
     */
    private String companyName;
    /**
     * @Description 法人证件号码
     * @Required
     */
    private String idNumber;
    /**
     * @Description 法人身份证正面
     * @Required
     */
    private String idFrontUrl;
    /**
     * @Description 法人身份证反面
     * @Required
     */
    private String idBackUrl;
    /**
     * @Description 支付凭证
     */
    private String paymentUrl;
    /**
     * @Description 负责人尽职调查表
     */
    private String chargerInvestigationUrl;
    /**
     * @Description 单位尽职调查表
     */
    private String companyInvestigationUrl;
    /**
     * @Description 承诺公函
     * @Required
     */
    private String promiseLetterUrl;
    /**
     * @Description 申请公函
     * @Required
     */
    private String applyLetterUrl;
    /**
     * @Description 状态：waitPending--待初审，waitAudit--待审核，unpass--未通过，passed--已通过，deleting--删除中，deleted--已删除;
     */
    private String state;
    /**
     * @Description 负责区域
     * @Required
     */
    private String area;
    /**
     * @Description 负责区域code
     * @Required
     */
    private String areaCode;
    /**
     * @Description 详细地址
     */
    private String address;
    /**
     * @Description 推荐工号
     */
    private String recommend;

    /**
     * @Description 创建时间
     */
    private Date createTime;

    /**
     * @Description 更新时间
     */
    private Date modifyTime;
    /**
     * @Description 未通过原因
     */
    private String reason;

    /**
     * @Description 营业执照
     */
    private String businessLicenseUrl;
    public Date getModifyTime() {
        return modifyTime;
    }

    public JProvenceCenterDetail setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }
    public String getBusinessLicenseUrl() {
        return businessLicenseUrl;
    }

    public JProvenceCenterDetail setBusinessLicenseUrl(String businessLicenseUrl) {
        this.businessLicenseUrl = businessLicenseUrl;
        return this;
    }
    public String getPaymentUrl() {
        return paymentUrl;
    }

    public JProvenceCenterDetail setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public JProvenceCenterDetail setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getRecommend() {
        return recommend;
    }

    public JProvenceCenterDetail setRecommend(String recommend) {
        this.recommend = recommend;
        return this;
    }

    public Long getId() {
        return id;
    }

    public JProvenceCenterDetail setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public JProvenceCenterDetail setType(String type) {
        this.type = type;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JProvenceCenterDetail setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getChargerName() {
        return chargerName;
    }

    public JProvenceCenterDetail setChargerName(String chargerName) {
        this.chargerName = chargerName;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public JProvenceCenterDetail setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public JProvenceCenterDetail setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public JProvenceCenterDetail setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public JProvenceCenterDetail setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }

    public String getChargerInvestigationUrl() {
        return chargerInvestigationUrl;
    }

    public JProvenceCenterDetail setChargerInvestigationUrl(String chargerInvestigationUrl) {
        this.chargerInvestigationUrl = chargerInvestigationUrl;
        return this;
    }

    public String getCompanyInvestigationUrl() {
        return companyInvestigationUrl;
    }

    public JProvenceCenterDetail setCompanyInvestigationUrl(String companyInvestigationUrl) {
        this.companyInvestigationUrl = companyInvestigationUrl;
        return this;
    }

    public String getPromiseLetterUrl() {
        return promiseLetterUrl;
    }

    public JProvenceCenterDetail setPromiseLetterUrl(String promiseLetterUrl) {
        this.promiseLetterUrl = promiseLetterUrl;
        return this;
    }

    public String getApplyLetterUrl() {
        return applyLetterUrl;
    }

    public JProvenceCenterDetail setApplyLetterUrl(String applyLetterUrl) {
        this.applyLetterUrl = applyLetterUrl;
        return this;
    }

    public String getState() {
        return state;
    }

    public JProvenceCenterDetail setState(String state) {
        this.state = state;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public JProvenceCenterDetail setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public JProvenceCenterDetail setAddress(String address) {
        this.address = address;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JProvenceCenterDetail setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getArea() {
        return area;
    }

    public JProvenceCenterDetail setArea(String area) {
        this.area = area;
        return this;
    }
}
