package com.cpsdb.declareserv.params.provence;


import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.common.CPSStringUtils;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.validator.Validate;
import com.cpsdb.validator.ValidateType;

public class JProvenceCenterAddParams {

    /**
     * @Description 手机号码
     * @Required
     */
    @Validate(type = ValidateType.cellphone)
    private String cellphone;
    /**
     * @Description 负责人姓名
     * @Required
     */

    @Validate(type = ValidateType.notNull,message = "负责人姓名为空！")
    private String chargerName;

    /**
     * @Description 公司名称
     * @Required
     */
    @Validate(type = ValidateType.notNull,message = "公司名称为空！")
    private String companyName;
    /**
     * @Description 法人证件号码
     * @Required
     */
    @Validate(type = ValidateType.notNull,message = "法人证件号码为空！")
    private String idNumber;
    /**
     * @Description 法人身份证正面
     * @Required
     */
    @Validate(type = ValidateType.notNull,message = "法人身份证正面为空！")
    private String idFrontUrl;
    /**
     * @Description 法人身份证反面
     * @Required
     */
    @Validate(type = ValidateType.notNull,message = "法人身份证反面为空！")
    private String idBackUrl;
    /**
     * @Description 负责人尽职调查表
     */
    @Validate(type = ValidateType.notNull,message = "负责人尽职调查表为空！")
    private String chargerInvestigationUrl;
    /**
     * @Description 单位尽职调查表
     */
    @Validate(type = ValidateType.notNull,message = "单位尽职调查表为空！")
    private String companyInvestigationUrl;
    /**
     * @Description 承诺公函
     * @Required
     */
    @Validate(type = ValidateType.notNull,message = "承诺公函为空！")
    private String promiseLetterUrl;
    /**
     * @Description 申请公函
     * @Required
     */
    @Validate(type = ValidateType.notNull,message = "申请公函为空！")
    private String applyLetterUrl;

    /**
     * @Description 负责区域
     * @Required
     */
    @Validate(type = ValidateType.notNull,message = "负责区域为空！")
    private String areaCode;
    /**
     * @Description 详细地址
     */
    @Validate(type = ValidateType.notNull,message = "详细地址为空！")
    private String address;
    /**
     * @Description 推荐工号
     */
    private String recommend;

    /**
     * @Description 验证码
     */
    private String code;
    /**
     * @Description 密码
     */
    @Validate(type = ValidateType.notNull,message = "密码为空！")
    private String password;
    /**
     * @Description 授权类型：a-A类, b-B类, c-C类，d-D类
     */
    private String type;

    /**
     * @Description 营业执照
     */
    private String businessLicenseUrl;

    public String getType() {
        return type;
    }

    public JProvenceCenterAddParams setType(String type) {
        this.type = type;
        return this;
    }
    public String getBusinessLicenseUrl() {
        return businessLicenseUrl;
    }

    public JProvenceCenterAddParams setBusinessLicenseUrl(String businessLicenseUrl) {
        this.businessLicenseUrl = businessLicenseUrl;
        return this;
    }

    public String getRecommend() {
        return recommend;
    }

    public JProvenceCenterAddParams setRecommend(String recommend) {
        this.recommend = recommend;
        return this;
    }

    public String getCode() {
        return code;
    }

    public JProvenceCenterAddParams setCode(String code) {
        this.code = code;
        return this;
    }


    public String getCellphone() {
        return cellphone;
    }

    public JProvenceCenterAddParams setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getChargerName() {
        return chargerName;
    }

    public JProvenceCenterAddParams setChargerName(String chargerName) {
        this.chargerName = chargerName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public JProvenceCenterAddParams setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public JProvenceCenterAddParams setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public JProvenceCenterAddParams setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public JProvenceCenterAddParams setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public JProvenceCenterAddParams setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }

    public String getChargerInvestigationUrl() {
        return chargerInvestigationUrl;
    }

    public JProvenceCenterAddParams setChargerInvestigationUrl(String chargerInvestigationUrl) {
        this.chargerInvestigationUrl = chargerInvestigationUrl;
        return this;
    }

    public String getCompanyInvestigationUrl() {
        return companyInvestigationUrl;
    }

    public JProvenceCenterAddParams setCompanyInvestigationUrl(String companyInvestigationUrl) {
        this.companyInvestigationUrl = companyInvestigationUrl;
        return this;
    }

    public String getPromiseLetterUrl() {
        return promiseLetterUrl;
    }

    public JProvenceCenterAddParams setPromiseLetterUrl(String promiseLetterUrl) {
        this.promiseLetterUrl = promiseLetterUrl;
        return this;
    }

    public String getApplyLetterUrl() {
        return applyLetterUrl;
    }

    public JProvenceCenterAddParams setApplyLetterUrl(String applyLetterUrl) {
        this.applyLetterUrl = applyLetterUrl;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public JProvenceCenterAddParams setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public JProvenceCenterAddParams setAddress(String address) {
        this.address = address;
        return this;
    }

}
