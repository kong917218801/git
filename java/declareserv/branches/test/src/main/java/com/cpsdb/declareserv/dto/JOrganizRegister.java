package com.cpsdb.declareserv.dto;

import java.util.Date;

/**
 * @ClassName JOrganizEssential
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:02
 */
public class JOrganizRegister {

    /**
     * @Description 未通过原因
     */
    private String reason;
    /**
     * @Description 状态：baseWaitSubmit—基本信息待填写,baseWaitPending—基本信息待初审,baseWaitAudit—基本信息待审核,baseUnPass—基本信息未通过,registWaitSubmit—登记信息待填写,registWaitPending—登记信息待初审,registWaitAudit—登记信息待审核,registUnPass—登记信息未通过,delete—已删除,passed—通过审核
     */
    private String state;
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
     * @Description 省级服务中心id
     */
    private Long fkProvenceCenterId;


    public String getReason() {
        return reason;
    }

    public JOrganizRegister setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getState() {
        return state;
    }

    public JOrganizRegister setState(String state) {
        this.state = state;
        return this;
    }

    public String getSurveyImageUrl() {
        return surveyImageUrl;
    }

    public JOrganizRegister setSurveyImageUrl(String surveyImageUrl) {
        this.surveyImageUrl = surveyImageUrl;
        return this;
    }

    public String getLetterImageUrl() {
        return letterImageUrl;
    }

    public JOrganizRegister setLetterImageUrl(String letterImageUrl) {
        this.letterImageUrl = letterImageUrl;
        return this;
    }

    public String getChargerSurveyImageUrl() {
        return chargerSurveyImageUrl;
    }

    public JOrganizRegister setChargerSurveyImageUrl(String chargerSurveyImageUrl) {
        this.chargerSurveyImageUrl = chargerSurveyImageUrl;
        return this;
    }

    public String getChargerImageUrl() {
        return chargerImageUrl;
    }

    public JOrganizRegister setChargerImageUrl(String chargerImageUrl) {
        this.chargerImageUrl = chargerImageUrl;
        return this;
    }

    public String getCommerceImageUrl() {
        return commerceImageUrl;
    }

    public JOrganizRegister setCommerceImageUrl(String commerceImageUrl) {
        this.commerceImageUrl = commerceImageUrl;
        return this;
    }

    public String getOtherImageUrl() {
        return otherImageUrl;
    }

    public JOrganizRegister setOtherImageUrl(String otherImageUrl) {
        this.otherImageUrl = otherImageUrl;
        return this;
    }

    public Long getFkProvenceCenterId() {
        return fkProvenceCenterId;
    }

    public JOrganizRegister setFkProvenceCenterId(Long fkProvenceCenterId) {
        this.fkProvenceCenterId = fkProvenceCenterId;
        return this;
    }
}
