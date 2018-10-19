package com.cpsdb.declareserv.dto;

import com.cpsdb.declareserv.entity.OperationLog;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName JDeclareEnterpriseDetail
 * @Description 申报企业返回详情
 * @author 孔清
 * @date 2018-03-05 14:00:22
 */
public class JDeclareEnterpriseDetail {

    /**
     * @fieldName id
     * @fieldType Long
     * @Description 主键
     */
    private Long id;

    /**
     * 对象版本，乐观锁需要可使用
     */
    @JsonIgnore
    private Integer version;

    /**
     * @fieldName name
     * @fieldType String
     * @Description 申报企业名称
     */
    private String name;
    /**
     * @fieldName charger
     * @fieldType String
     * @Description 负责人姓名
     */
    private String charger;
    /**
     * @fieldName cellphone
     * @fieldType String
     * @Description 手机号码
     */
    private String cellphone;
    /**
     * @fieldName id_number
     * @fieldType String
     * @Description 证件号码
     */
    private String idNumber;
    /**
     * @fieldName id_front_url
     * @fieldType String
     * @Description 身份证正面
     */
    private String idFrontUrl;
    /**
     * @fieldName id_back_url
     * @fieldType String
     * @Description 身份证反面
     */
    private String idBackUrl;
    /**
     * @fieldName license_image_url
     * @fieldType String
     * @Description 营业执照
     */
    private String licenseImageUrl;
    /**
     * @fieldName production_image_url
     * @fieldType String
     * @Description 生产许可证
     */
    private String productionImageUrl;
    /**
     * @fieldName capital_image_url
     * @fieldType String
     * @Description 资金补贴申请表
     */
    private String capitalImageUrl;
    /**
     * @fieldName enterprise_survey_image_url
     * @fieldType String
     * @Description 企业尽职调查表
     */
    private String enterpriseSurveyImageUrl;
    /**
     * @fieldName enterprise_charger_survey_image_url
     * @fieldType String
     * @Description 企业负责人尽职调查表
     */
    private String enterpriseChargerSurveyImageUrl;
    /**
     * @fieldName enterprise_shind_image_url
     * @fieldType String
     * @Description 企业入库申请函
     */
    private String enterpriseShindImageUrl;
    /**
     * @fieldName authorization_image_url
     * @fieldType String
     * @Description 授权委托书
     */
    private String authorizationImageUrl;
    /**
     * @fieldName other_image_url
     * @fieldType String
     * @Description 其他补充材料
     */
    private String otherImageUrl;
    /**
     * @fieldName state
     * @fieldType String
     * @Description 状态: 
  wait：待审核;passed：通过审核; failed: 未通过审核;pending:待初审;collectting:待认证官上门采集;confirmFailed:初审未通过;reject2:认证官采集未通过,pending2:待复审,confirm2Failed:复审未通过,passed:通过审核,deleteDeclarer:已删除;
     */
    private String state;
    /**
     * @fieldName reason
     * @fieldType String
     * @Description 未通过审核原因
     */
    private String reason;
    /**
     * @fieldName codeCount
     * @fieldType Long
     * @Description 二维码数量
     */
    private Long codeCount;
    /**
     * @fieldName reason
     * @fieldType String
     * @Description 补贴金额
     */
    private BigDecimal paidAmount;
    /**
     * @fieldName fk_user_id
     * @fieldType Long
     * @Description 关联申报机构id
     */
    private Long fkOrganizId;
    /**
    /**
     * @fieldName fk_user_id
     * @fieldType Long
     * @Description 具体用户
     */
    private Long fkUserId;
    /**
     * @fieldName create_time
     * @fieldType Date
     * @Description 
     */
    private Date createTime;
    /**
     * @fieldName modify_time
     * @fieldType Date
     * @Description 
     */
    private Date modifyTime;

    /**************新增字段******************/
    /**
     * @Description 申报机构名称
     */
    private String organizName;

    /**
     * @Description 申报机构负责人
     */
    private String organizUsername;

    /**
     * @Description 申报机构联系电话
     */
    private String organizPhone;

    /**
     * @Description 申报官联系电话
     */
    private String declarerPhone;
    /**
     * @Description 申报官负责人
     */
    private String declarerUsername;
    /**
     * @Description 申报官名称
     */

    private String declarerName;

    /**
     @Description 入库系统企业Id
     */
    private Long enterpriseId;

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public JDeclareEnterpriseDetail setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
        return this;
    }

    public String getOrganizUsername() {
        return organizUsername;
    }

    public JDeclareEnterpriseDetail setOrganizUsername(String organizUsername) {
        this.organizUsername = organizUsername;
        return this;
    }

    public String getDeclarerUsername() {
        return declarerUsername;
    }

    public JDeclareEnterpriseDetail setDeclarerUsername(String declarerUsername) {
        this.declarerUsername = declarerUsername;
        return this;
    }

    public String getOrganizPhone() {
        return organizPhone;
    }

    public JDeclareEnterpriseDetail setOrganizPhone(String organizPhone) {
        this.organizPhone = organizPhone;
        return this;
    }

    public String getDeclarerPhone() {
        return declarerPhone;
    }

    public JDeclareEnterpriseDetail setDeclarerPhone(String declarerPhone) {
        this.declarerPhone = declarerPhone;
        return this;
    }

    private List<OperationLog> logs;

    public List<OperationLog> getLogs() {
        return logs;
    }

    public JDeclareEnterpriseDetail setLogs(List<OperationLog> logs) {
        this.logs = logs;
        return this;
    }


    public String getDeclarerName() {
        return declarerName;
    }

    public JDeclareEnterpriseDetail setDeclarerName(String declarerName) {
        this.declarerName = declarerName;
        return this;
    }

    public Long getFkUserId() {
        return fkUserId;
    }

    public JDeclareEnterpriseDetail setFkUserId(Long fkUserId) {
        this.fkUserId = fkUserId;
        return this;
    }

    public String getOrganizName() {
        return organizName;
    }

    public JDeclareEnterpriseDetail setOrganizName(String organizName) {
        this.organizName = organizName;
        return this;
    }

    public Long getId() {
       	return id;
    }

    public JDeclareEnterpriseDetail setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCodeCount() {
        return codeCount;
    }

    public JDeclareEnterpriseDetail setCodeCount(Long codeCount) {
        this.codeCount = codeCount;
        return this;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public JDeclareEnterpriseDetail setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public JDeclareEnterpriseDetail setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getName() {
        return name;
    }

    public JDeclareEnterpriseDetail setName(String name) {
        this.name = name;
        return this;
    }

    public String getCharger() {
        return charger;
    }

    public JDeclareEnterpriseDetail setCharger(String charger) {
        this.charger = charger;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JDeclareEnterpriseDetail setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public JDeclareEnterpriseDetail setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public JDeclareEnterpriseDetail setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public JDeclareEnterpriseDetail setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }

    public String getLicenseImageUrl() {
        return licenseImageUrl;
    }

    public JDeclareEnterpriseDetail setLicenseImageUrl(String licenseImageUrl) {
        this.licenseImageUrl = licenseImageUrl;
        return this;
    }

    public String getProductionImageUrl() {
        return productionImageUrl;
    }

    public JDeclareEnterpriseDetail setProductionImageUrl(String productionImageUrl) {
        this.productionImageUrl = productionImageUrl;
        return this;
    }

    public String getCapitalImageUrl() {
        return capitalImageUrl;
    }

    public JDeclareEnterpriseDetail setCapitalImageUrl(String capitalImageUrl) {
        this.capitalImageUrl = capitalImageUrl;
        return this;
    }

    public String getEnterpriseSurveyImageUrl() {
        return enterpriseSurveyImageUrl;
    }

    public JDeclareEnterpriseDetail setEnterpriseSurveyImageUrl(String enterpriseSurveyImageUrl) {
        this.enterpriseSurveyImageUrl = enterpriseSurveyImageUrl;
        return this;
    }

    public String getEnterpriseChargerSurveyImageUrl() {
        return enterpriseChargerSurveyImageUrl;
    }

    public JDeclareEnterpriseDetail setEnterpriseChargerSurveyImageUrl(String enterpriseChargerSurveyImageUrl) {
        this.enterpriseChargerSurveyImageUrl = enterpriseChargerSurveyImageUrl;
        return this;
    }

    public String getEnterpriseShindImageUrl() {
        return enterpriseShindImageUrl;
    }

    public JDeclareEnterpriseDetail setEnterpriseShindImageUrl(String enterpriseShindImageUrl) {
        this.enterpriseShindImageUrl = enterpriseShindImageUrl;
        return this;
    }

    public String getAuthorizationImageUrl() {
        return authorizationImageUrl;
    }

    public JDeclareEnterpriseDetail setAuthorizationImageUrl(String authorizationImageUrl) {
        this.authorizationImageUrl = authorizationImageUrl;
        return this;
    }

    public String getOtherImageUrl() {
        return otherImageUrl;
    }

    public JDeclareEnterpriseDetail setOtherImageUrl(String otherImageUrl) {
        this.otherImageUrl = otherImageUrl;
        return this;
    }

    public String getState() {
        return state;
    }

    public JDeclareEnterpriseDetail setState(String state) {
        this.state = state;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public JDeclareEnterpriseDetail setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Long getFkOrganizId() {
        return fkOrganizId;
    }

    public JDeclareEnterpriseDetail setFkOrganizId(Long fkOrganizId) {
        this.fkOrganizId = fkOrganizId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JDeclareEnterpriseDetail setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public JDeclareEnterpriseDetail setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
