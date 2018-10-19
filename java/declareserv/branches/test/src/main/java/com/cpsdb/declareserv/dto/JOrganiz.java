package com.cpsdb.declareserv.dto;

import com.cpsdb.declareserv.entity.OperationLog;

import java.util.Date;
import java.util.List;

/**
 * @ClassName JOrganiz
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:02
 */
public class JOrganiz {

    /**
     * @Description 主键
     */
    private Long id;

    /**
     * @Description 申报服务处名称
     */
    private String name;
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
     * 具体所在地址
     * */
    private String address;
    /**
     * @Description 日志信息
     */
    private List<OperationLog> logs;
    /**
     * @Description 常驻地址code
     */
    private String liveAddress;
    /**
     * @Description 申请省级code
     */
    private String applyAddress;
    /**
     * @Description 推荐者账号
     * @Required
     */
    private String recommend;










    public Long getId() {
       	return id;
    }

    public JOrganiz setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public JOrganiz setName(String name) {
        this.name = name;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JOrganiz setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public JOrganiz setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public JOrganiz setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public JOrganiz setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public JOrganiz setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public JOrganiz setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getState() {
        return state;
    }

    public JOrganiz setState(String state) {
        this.state = state;
        return this;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public JOrganiz setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
        return this;
    }

    public String getSurveyImageUrl() {
        return surveyImageUrl;
    }

    public JOrganiz setSurveyImageUrl(String surveyImageUrl) {
        this.surveyImageUrl = surveyImageUrl;
        return this;
    }

    public String getLetterImageUrl() {
        return letterImageUrl;
    }

    public JOrganiz setLetterImageUrl(String letterImageUrl) {
        this.letterImageUrl = letterImageUrl;
        return this;
    }

    public String getChargerSurveyImageUrl() {
        return chargerSurveyImageUrl;
    }

    public JOrganiz setChargerSurveyImageUrl(String chargerSurveyImageUrl) {
        this.chargerSurveyImageUrl = chargerSurveyImageUrl;
        return this;
    }

    public String getChargerImageUrl() {
        return chargerImageUrl;
    }

    public JOrganiz setChargerImageUrl(String chargerImageUrl) {
        this.chargerImageUrl = chargerImageUrl;
        return this;
    }

    public String getCommerceImageUrl() {
        return commerceImageUrl;
    }

    public JOrganiz setCommerceImageUrl(String commerceImageUrl) {
        this.commerceImageUrl = commerceImageUrl;
        return this;
    }

    public String getOtherImageUrl() {
        return otherImageUrl;
    }

    public JOrganiz setOtherImageUrl(String otherImageUrl) {
        this.otherImageUrl = otherImageUrl;
        return this;
    }

    public Date getBaseTime() {
        return baseTime;
    }

    public JOrganiz setBaseTime(Date baseTime) {
        this.baseTime = baseTime;
        return this;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public JOrganiz setRegistTime(Date registTime) {
        this.registTime = registTime;
        return this;
    }

    public Long getFkProvenceCenterId() {
        return fkProvenceCenterId;
    }

    public JOrganiz setFkProvenceCenterId(Long fkProvenceCenterId) {
        this.fkProvenceCenterId = fkProvenceCenterId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JOrganiz setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public JOrganiz setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }
    /**********新增字段*******************/


    public String getAddress() {
        return address;
    }

    public JOrganiz setAddress(String address) {
        this.address = address;
        return this;
    }

    public List<OperationLog> getLogs() {
        return logs;
    }

    public JOrganiz setLogs(List<OperationLog> logs) {
        this.logs = logs;
        return this;
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public JOrganiz setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
        return this;
    }

    public String getApplyAddress() {
        return applyAddress;
    }

    public JOrganiz setApplyAddress(String applyAddress) {
        this.applyAddress = applyAddress;
        return this;
    }

    public String getRecommend() {
        return recommend;
    }

    public JOrganiz setRecommend(String recommend) {
        this.recommend = recommend;
        return this;
    }
}
