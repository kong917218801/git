package com.cpsdb.declareserv.entity;

import com.cpsdb.validator.Validate;
import com.cpsdb.validator.ValidateType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * @author 李银
 * @ClassName ProvenceCenter
 * @Description
 * @date 2018-09-20 17:26:02
 */
public class ProvenceCenter {

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
     * @Description 授权类型：a-A类, b-B类, c-C类，d-D类
     */
    private String type;
    /**
     * @Description 手机号码
     */
    @Validate(type = ValidateType.cellphone)
    private String cellphone;
    /**
     * @Description 负责人姓名
     */
    private String chargerName;
    /**
     * @Description 公司名称
     */
    private String companyName;
    /**
     * @Description 法人证件号码
     */
    private String idNumber;
    /**
     * @Description 法人身份证正面
     */
    private String idFrontUrl;
    /**
     * @Description 法人身份证反面
     */
    private String idBackUrl;

    /**
     * @Description 支付凭证
     */
    private String paymentUrl;

    /**
     * @Description 营业执照
     */
    private String businessLicenseUrl;

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
     */
    private String promiseLetterUrl;
    /**
     * @Description 申请公函
     */
    private String applyLetterUrl;
    /**
     * @Description 状态：waitPending--待初审，waitAudit--待审核，unpass--未通过，passed--已通过，deleting--删除中，deleted--已删除;
     */
    private String state;
    /**
     * @Description 负责区域
     */
    private String areaCode;
    /**
     * @Description 详细地址
     */
    private String address;
    /**
     * @Description 创建时间
     */
    private Date createTime;
    /**
     * @Description 更新时间
     */
    private Date modifyTime;
    /**
     * @Description 验证码
     */
    private String code;
    /**
     * @Description 推荐工号
     */
    private String recommend;
    /**
     * @Description 密码
     */
    private String password;

    /**
     * @Description 未通过原因
     */
    private String reason;
    public String getReason() {
        return reason;
    }

    public ProvenceCenter setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public ProvenceCenter setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ProvenceCenter setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ProvenceCenter setCode(String code) {
        this.code = code;
        return this;
    }
    public String getBusinessLicenseUrl() {
        return businessLicenseUrl;
    }

    public ProvenceCenter setBusinessLicenseUrl(String businessLicenseUrl) {
        this.businessLicenseUrl = businessLicenseUrl;
        return this;
    }


    public String getRecommend() {
        return recommend;
    }

    public ProvenceCenter setRecommend(String recommend) {
        this.recommend = recommend;
        return this;
    }



    public Long getId() {
        return id;
    }

    public ProvenceCenter setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public ProvenceCenter setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public ProvenceCenter setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getChargerName() {
        return chargerName;
    }

    public ProvenceCenter setChargerName(String chargerName) {
        this.chargerName = chargerName;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public ProvenceCenter setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public ProvenceCenter setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public ProvenceCenter setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public ProvenceCenter setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }

    public String getChargerInvestigationUrl() {
        return chargerInvestigationUrl;
    }

    public ProvenceCenter setChargerInvestigationUrl(String chargerInvestigationUrl) {
        this.chargerInvestigationUrl = chargerInvestigationUrl;
        return this;
    }

    public String getCompanyInvestigationUrl() {
        return companyInvestigationUrl;
    }

    public ProvenceCenter setCompanyInvestigationUrl(String companyInvestigationUrl) {
        this.companyInvestigationUrl = companyInvestigationUrl;
        return this;
    }

    public String getPromiseLetterUrl() {
        return promiseLetterUrl;
    }

    public ProvenceCenter setPromiseLetterUrl(String promiseLetterUrl) {
        this.promiseLetterUrl = promiseLetterUrl;
        return this;
    }

    public String getApplyLetterUrl() {
        return applyLetterUrl;
    }

    public ProvenceCenter setApplyLetterUrl(String applyLetterUrl) {
        this.applyLetterUrl = applyLetterUrl;
        return this;
    }

    public String getState() {
        return state;
    }

    public ProvenceCenter setState(String state) {
        this.state = state;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public ProvenceCenter setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ProvenceCenter setAddress(String address) {
        this.address = address;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ProvenceCenter setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public ProvenceCenter setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public String getType() {
        return type;
    }

    public ProvenceCenter setType(String type) {
        this.type = type;
        return this;
    }


}
