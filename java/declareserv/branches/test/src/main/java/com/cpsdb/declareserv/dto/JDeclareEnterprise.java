package com.cpsdb.declareserv.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName JDeclareEnterprise
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:01
 */
public class JDeclareEnterprise {

    /**
     * @Description 主键
     */
    private Long id;

    /**
     * @Description 申报企业名称
     */
    private String name;
    /**
     * @Description 负责人姓名
     */
    private String charger;
    /**
     * @Description 手机号码
     */
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
     * @Description 营业执照
     */
    private String licenseImageUrl;
    /**
     * @Description 生产许可证
     */
    private String productionImageUrl;
    /**
     * @Description 资金补贴申请表
     */
    private String capitalImageUrl;
    /**
     * @Description 企业尽职调查表
     */
    private String enterpriseSurveyImageUrl;
    /**
     * @Description 企业负责人尽职调查表
     */
    private String enterpriseChargerSurveyImageUrl;
    /**
     * @Description 企业入库申请函
     */
    private String enterpriseShindImageUrl;
    /**
     * @Description 授权委托书
     */
    private String authorizationImageUrl;
    /**
     * @Description 其他补充材料
     */
    private String otherImageUrl;
    /**
     * @Description 状态：wait—待审核，passed—通过审核， failed—未通过审核，pending—待初审，collectting—待认证官上门采集，confirmFailed—初审未通过，reject2—认证官采集未通过，pending2—待复审，confirm2Failed—复审未通过，passed—通过审核，delete—已删除
     */
    private String state;
    /**
     * @Description 未通过审核原因
     */
    private String reason;
    /**
     * @Description 用户id
     */
    private Long fkUserId;
    /**
     * @Description 用户id
     */
    private Long fkDeclarerId;
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
     * @Description  申报官姓名
     */
    private String declarerName;
    /**
     * @fieldName reason
     * @fieldType String
     * @Description 二维码数量
     */
    private Integer codeCount;

    /**
     * @fieldName reason
     * @fieldType String
     * @Description 补贴金额
     */
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public JDeclareEnterprise setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Integer getCodeCount() {
        return codeCount;
    }

    public JDeclareEnterprise setCodeCount(Integer codeCount) {
        this.codeCount = codeCount;
        return this;
    }

    public String getDeclarerName() {
        return declarerName;
    }

    public JDeclareEnterprise setDeclarerName(String declarerName) {
        this.declarerName = declarerName;
        return this;
    }

    public Long getId() {
       	return id;
    }

    public JDeclareEnterprise setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public JDeclareEnterprise setName(String name) {
        this.name = name;
        return this;
    }

    public String getCharger() {
        return charger;
    }

    public JDeclareEnterprise setCharger(String charger) {
        this.charger = charger;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JDeclareEnterprise setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public JDeclareEnterprise setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public JDeclareEnterprise setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public JDeclareEnterprise setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }

    public String getLicenseImageUrl() {
        return licenseImageUrl;
    }

    public JDeclareEnterprise setLicenseImageUrl(String licenseImageUrl) {
        this.licenseImageUrl = licenseImageUrl;
        return this;
    }

    public String getProductionImageUrl() {
        return productionImageUrl;
    }

    public JDeclareEnterprise setProductionImageUrl(String productionImageUrl) {
        this.productionImageUrl = productionImageUrl;
        return this;
    }

    public String getCapitalImageUrl() {
        return capitalImageUrl;
    }

    public JDeclareEnterprise setCapitalImageUrl(String capitalImageUrl) {
        this.capitalImageUrl = capitalImageUrl;
        return this;
    }

    public String getEnterpriseSurveyImageUrl() {
        return enterpriseSurveyImageUrl;
    }

    public JDeclareEnterprise setEnterpriseSurveyImageUrl(String enterpriseSurveyImageUrl) {
        this.enterpriseSurveyImageUrl = enterpriseSurveyImageUrl;
        return this;
    }

    public String getEnterpriseChargerSurveyImageUrl() {
        return enterpriseChargerSurveyImageUrl;
    }

    public JDeclareEnterprise setEnterpriseChargerSurveyImageUrl(String enterpriseChargerSurveyImageUrl) {
        this.enterpriseChargerSurveyImageUrl = enterpriseChargerSurveyImageUrl;
        return this;
    }

    public String getEnterpriseShindImageUrl() {
        return enterpriseShindImageUrl;
    }

    public JDeclareEnterprise setEnterpriseShindImageUrl(String enterpriseShindImageUrl) {
        this.enterpriseShindImageUrl = enterpriseShindImageUrl;
        return this;
    }

    public String getAuthorizationImageUrl() {
        return authorizationImageUrl;
    }

    public JDeclareEnterprise setAuthorizationImageUrl(String authorizationImageUrl) {
        this.authorizationImageUrl = authorizationImageUrl;
        return this;
    }

    public String getOtherImageUrl() {
        return otherImageUrl;
    }

    public JDeclareEnterprise setOtherImageUrl(String otherImageUrl) {
        this.otherImageUrl = otherImageUrl;
        return this;
    }

    public String getState() {
        return state;
    }

    public JDeclareEnterprise setState(String state) {
        this.state = state;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public JDeclareEnterprise setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Long getFkUserId() {
        return fkUserId;
    }

    public JDeclareEnterprise setFkUserId(Long fkUserId) {
        this.fkUserId = fkUserId;
        return this;
    }

    public Long getFkDeclarerId() {
        return fkDeclarerId;
    }

    public JDeclareEnterprise setFkDeclarerId(Long fkDeclarerId) {
        this.fkDeclarerId = fkDeclarerId;
        return this;
    }

    public Long getFkOrganizId() {
        return fkOrganizId;
    }

    public JDeclareEnterprise setFkOrganizId(Long fkOrganizId) {
        this.fkOrganizId = fkOrganizId;
        return this;
    }

    public Long getFkProvenceCenterId() {
        return fkProvenceCenterId;
    }

    public JDeclareEnterprise setFkProvenceCenterId(Long fkProvenceCenterId) {
        this.fkProvenceCenterId = fkProvenceCenterId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JDeclareEnterprise setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public JDeclareEnterprise setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
