package com.cpsdb.declareserv.params.provence;

/**
 * @Author 老周
 * @Description 功能描述
 * @CreateDate 2018.7.1
 * @Version v1.0
 */
public class JProvenceCenterEditParams {

    /**
     * @Description 电话号码
     * @Required
     */
    private String cellphone;
    /**
     * @Description 负责人姓名
     * @Required
     */
    private String chargerName;

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
     * @Description 负责人尽职调查表
     */
    private String chargerInvestigationUrl;
    /**
     * @Description 单位尽职调查表
     */
    private String companyInvestigationUrl;
    /**
     * @Description 营业执照
     */
    private String businessLicenseUrl;
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
     * @Description 负责区域
     * @Required
     */
    private String areaCode;
    /**
     * @Description 详细地址
     */
    private String address;
    /**
     * @Description 授权类型：a-A类, b-B类, c-C类，d-D类
     */
    private String type;
    public String getType() {
        return type;
    }

    public JProvenceCenterEditParams setType(String type) {
        this.type = type;
        return this;
    }
    public String getCellphone() {
        return cellphone;
    }

    public JProvenceCenterEditParams setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }
    public String getBusinessLicenseUrl() {
        return businessLicenseUrl;
    }

    public JProvenceCenterEditParams setBusinessLicenseUrl(String businessLicenseUrl) {
        this.businessLicenseUrl = businessLicenseUrl;
        return this;
    }

    public String getChargerName() {
        return chargerName;
    }

    public JProvenceCenterEditParams setChargerName(String chargerName) {
        this.chargerName = chargerName;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public JProvenceCenterEditParams setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public JProvenceCenterEditParams setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public JProvenceCenterEditParams setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public JProvenceCenterEditParams setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }

    public String getChargerInvestigationUrl() {
        return chargerInvestigationUrl;
    }

    public JProvenceCenterEditParams setChargerInvestigationUrl(String chargerInvestigationUrl) {
        this.chargerInvestigationUrl = chargerInvestigationUrl;
        return this;
    }

    public String getCompanyInvestigationUrl() {
        return companyInvestigationUrl;
    }

    public JProvenceCenterEditParams setCompanyInvestigationUrl(String companyInvestigationUrl) {
        this.companyInvestigationUrl = companyInvestigationUrl;
        return this;
    }

    public String getPromiseLetterUrl() {
        return promiseLetterUrl;
    }

    public JProvenceCenterEditParams setPromiseLetterUrl(String promiseLetterUrl) {
        this.promiseLetterUrl = promiseLetterUrl;
        return this;
    }

    public String getApplyLetterUrl() {
        return applyLetterUrl;
    }

    public JProvenceCenterEditParams setApplyLetterUrl(String applyLetterUrl) {
        this.applyLetterUrl = applyLetterUrl;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public JProvenceCenterEditParams setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public JProvenceCenterEditParams setAddress(String address) {
        this.address = address;
        return this;
    }
}
