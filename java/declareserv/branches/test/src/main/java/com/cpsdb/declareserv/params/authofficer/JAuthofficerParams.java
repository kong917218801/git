package com.cpsdb.declareserv.params.authofficer;

public class JAuthofficerParams {

    /**
     * @Description 申报官头像
     */
    private String avator;
    /**
     * @Description 申报认证官姓名
     */
    private String name;
    /**
     * @fieldName idCard
     * @fieldType String
     * @Description 申报认证官的身份证号
     */
    private String idCard;
    /**
     * @Description 所属机构
     */
    private String organiz;
    /**
     * @Description 所属机构电话
     */
    private String organizTel;
    /**
     * @Description 考试分数
     */
    private String score;
    /**
     * @Description 身份证照正面
     */
    private String idFrontUrl;
    /**
     * @Description 身份证照反面
     */
    private String idBackUrl;
    /**
     * @Description 公函签字照
     */
    private String agreement;

    private String cellphone;

    public String getAvator() {
        return avator;
    }

    public JAuthofficerParams setAvator(String avator) {
        this.avator = avator;
        return this;
    }

    public String getName() {
        return name;
    }

    public JAuthofficerParams setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdCard() {
        return idCard;
    }

    public JAuthofficerParams setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    public String getOrganiz() {
        return organiz;
    }

    public JAuthofficerParams setOrganiz(String organiz) {
        this.organiz = organiz;
        return this;
    }

    public String getOrganizTel() {
        return organizTel;
    }

    public JAuthofficerParams setOrganizTel(String organizTel) {
        this.organizTel = organizTel;
        return this;
    }

    public String getScore() {
        return score;
    }

    public JAuthofficerParams setScore(String score) {
        this.score = score;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public JAuthofficerParams setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public JAuthofficerParams setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }

    public String getAgreement() {
        return agreement;
    }

    public JAuthofficerParams setAgreement(String agreement) {
        this.agreement = agreement;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JAuthofficerParams setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }
}
