package com.cpsdb.declareserv.params.declarer;

public class JDeclarerAuthenParams {

    /**
     * @Description 地区代码
     * @Required
     */
    private String areaCode;
    /**
     * @Description 头像路径
     */
    private String portrait;
    /**
     * @Description 身份证正面
     */
    private String idFrontUrl;
    /**
     * @Description 身份证反面
     */
    private String idBackUrl;
    /**
     * @Description 申报官尽职调查表
     */
    private String surveyImageUrl;
    /**
     * @Description 申报官承诺公函
     */
    private String letterImageUrl;


    public String getAreaCode() {
        return areaCode;
    }

    public JDeclarerAuthenParams setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getSurveyImageUrl() {
        return surveyImageUrl;
    }

    public JDeclarerAuthenParams setSurveyImageUrl(String surveyImageUrl) {
        this.surveyImageUrl = surveyImageUrl;
        return this;
    }

    public String getLetterImageUrl() {
        return letterImageUrl;
    }

    public JDeclarerAuthenParams setLetterImageUrl(String letterImageUrl) {
        this.letterImageUrl = letterImageUrl;
        return this;
    }


    public String getPortrait() {
        return portrait;
    }

    public JDeclarerAuthenParams setPortrait(String portrait) {
        this.portrait = portrait;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public JDeclarerAuthenParams setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public JDeclarerAuthenParams setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }
}
