package com.cpsdb.declareserv.params.organiz;

public class RegistParams {
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

    public String getSurveyImageUrl() {
        return surveyImageUrl;
    }

    public RegistParams setSurveyImageUrl(String surveyImageUrl) {
        this.surveyImageUrl = surveyImageUrl;
        return this;
    }

    public String getLetterImageUrl() {
        return letterImageUrl;
    }

    public RegistParams setLetterImageUrl(String letterImageUrl) {
        this.letterImageUrl = letterImageUrl;
        return this;
    }

    public String getChargerSurveyImageUrl() {
        return chargerSurveyImageUrl;
    }

    public RegistParams setChargerSurveyImageUrl(String chargerSurveyImageUrl) {
        this.chargerSurveyImageUrl = chargerSurveyImageUrl;
        return this;
    }

    public String getChargerImageUrl() {
        return chargerImageUrl;
    }

    public RegistParams setChargerImageUrl(String chargerImageUrl) {
        this.chargerImageUrl = chargerImageUrl;
        return this;
    }

    public String getCommerceImageUrl() {
        return commerceImageUrl;
    }

    public RegistParams setCommerceImageUrl(String commerceImageUrl) {
        this.commerceImageUrl = commerceImageUrl;
        return this;
    }

    public String getOtherImageUrl() {
        return otherImageUrl;
    }

    public RegistParams setOtherImageUrl(String otherImageUrl) {
        this.otherImageUrl = otherImageUrl;
        return this;
    }
}
