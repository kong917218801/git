package com.cpsdb.declareserv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName DeclareEnterprise
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:01
 */
public class DeclareEnterprise {

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
     * @fieldName reason
     * @fieldType String
     * @Description 申报官名称
     */

    private String declarerName;

    /**
     * @fieldName reason
     * @fieldType String
     * @Description 申报服务处名称
     */

    private String organizName;

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
    private BigDecimal paidAmount;

    public String getOrganizName() {
        return organizName;
    }

    public DeclareEnterprise setOrganizName(String organizName) {
        this.organizName = organizName;
        return this;
    }

    public String getDeclarerName() {
        return declarerName;
    }

    public DeclareEnterprise setDeclarerName(String declarerName) {
        this.declarerName = declarerName;
        return this;
    }


    public Integer getCodeCount() {
        return codeCount;
    }

    public DeclareEnterprise setCodeCount(Integer codeCount) {
        this.codeCount = codeCount;
        return this;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public DeclareEnterprise setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
        return this;
    }

    public Long getId() {
       	return id;
    }

    public DeclareEnterprise setId(Long id) {
        this.id = id;
        return this;
    }
    public Integer getVersion() {
        return version;
    }

    public DeclareEnterprise setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getName() {
        return name;
    }

    public DeclareEnterprise setName(String name) {
        this.name = name;
        return this;
    }

    public String getCharger() {
        return charger;
    }

    public DeclareEnterprise setCharger(String charger) {
        this.charger = charger;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public DeclareEnterprise setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public DeclareEnterprise setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public DeclareEnterprise setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public DeclareEnterprise setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }

    public String getLicenseImageUrl() {
        return licenseImageUrl;
    }

    public DeclareEnterprise setLicenseImageUrl(String licenseImageUrl) {
        this.licenseImageUrl = licenseImageUrl;
        return this;
    }

    public String getProductionImageUrl() {
        return productionImageUrl;
    }

    public DeclareEnterprise setProductionImageUrl(String productionImageUrl) {
        this.productionImageUrl = productionImageUrl;
        return this;
    }

    public String getCapitalImageUrl() {
        return capitalImageUrl;
    }

    public DeclareEnterprise setCapitalImageUrl(String capitalImageUrl) {
        this.capitalImageUrl = capitalImageUrl;
        return this;
    }

    public String getEnterpriseSurveyImageUrl() {
        return enterpriseSurveyImageUrl;
    }

    public DeclareEnterprise setEnterpriseSurveyImageUrl(String enterpriseSurveyImageUrl) {
        this.enterpriseSurveyImageUrl = enterpriseSurveyImageUrl;
        return this;
    }

    public String getEnterpriseChargerSurveyImageUrl() {
        return enterpriseChargerSurveyImageUrl;
    }

    public DeclareEnterprise setEnterpriseChargerSurveyImageUrl(String enterpriseChargerSurveyImageUrl) {
        this.enterpriseChargerSurveyImageUrl = enterpriseChargerSurveyImageUrl;
        return this;
    }

    public String getEnterpriseShindImageUrl() {
        return enterpriseShindImageUrl;
    }

    public DeclareEnterprise setEnterpriseShindImageUrl(String enterpriseShindImageUrl) {
        this.enterpriseShindImageUrl = enterpriseShindImageUrl;
        return this;
    }

    public String getAuthorizationImageUrl() {
        return authorizationImageUrl;
    }

    public DeclareEnterprise setAuthorizationImageUrl(String authorizationImageUrl) {
        this.authorizationImageUrl = authorizationImageUrl;
        return this;
    }

    public String getOtherImageUrl() {
        return otherImageUrl;
    }

    public DeclareEnterprise setOtherImageUrl(String otherImageUrl) {
        this.otherImageUrl = otherImageUrl;
        return this;
    }

    public String getState() {
        return state;
    }

    public DeclareEnterprise setState(String state) {
        this.state = state;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public DeclareEnterprise setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Long getFkUserId() {
        return fkUserId;
    }

    public DeclareEnterprise setFkUserId(Long fkUserId) {
        this.fkUserId = fkUserId;
        return this;
    }

    public Long getFkDeclarerId() {
        return fkDeclarerId;
    }

    public DeclareEnterprise setFkDeclarerId(Long fkDeclarerId) {
        this.fkDeclarerId = fkDeclarerId;
        return this;
    }

    public Long getFkOrganizId() {
        return fkOrganizId;
    }

    public DeclareEnterprise setFkOrganizId(Long fkOrganizId) {
        this.fkOrganizId = fkOrganizId;
        return this;
    }

    public Long getFkProvenceCenterId() {
        return fkProvenceCenterId;
    }

    public DeclareEnterprise setFkProvenceCenterId(Long fkProvenceCenterId) {
        this.fkProvenceCenterId = fkProvenceCenterId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public DeclareEnterprise setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public DeclareEnterprise setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }


    @Override
    public String toString() {
        return "DeclareEnterprise{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", charger='" + charger + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", idFrontUrl='" + idFrontUrl + '\'' +
                ", idBackUrl='" + idBackUrl + '\'' +
                ", licenseImageUrl='" + licenseImageUrl + '\'' +
                ", productionImageUrl='" + productionImageUrl + '\'' +
                ", capitalImageUrl='" + capitalImageUrl + '\'' +
                ", enterpriseSurveyImageUrl='" + enterpriseSurveyImageUrl + '\'' +
                ", enterpriseChargerSurveyImageUrl='" + enterpriseChargerSurveyImageUrl + '\'' +
                ", enterpriseShindImageUrl='" + enterpriseShindImageUrl + '\'' +
                ", authorizationImageUrl='" + authorizationImageUrl + '\'' +
                ", otherImageUrl='" + otherImageUrl + '\'' +
                ", state='" + state + '\'' +
                ", reason='" + reason + '\'' +
                ", fkUserId=" + fkUserId +
                ", fkDeclarerId=" + fkDeclarerId +
                ", fkOrganizId=" + fkOrganizId +
                ", fkProvenceCenterId=" + fkProvenceCenterId +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", declarerName='" + declarerName + '\'' +
                ", codeCount=" + codeCount +
                ", paidAmount=" + paidAmount +
                '}';
    }
}
