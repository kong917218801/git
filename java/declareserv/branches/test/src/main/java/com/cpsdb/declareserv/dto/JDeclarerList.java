package com.cpsdb.declareserv.dto;

public class JDeclarerList {

    /**
     * @Description 主键
     */
    private Long id;
    /**
     * @Description 名称
     */
    private String name;
    /**
     * @Description 头像
     */
    private String portrait;
    /**
     * @Description 身份证号码
     */
    private String idNumber;
    /**
     * @Description 分数
     */
    private String score;
    /**
     * @Description 电话号码
     */
    private String cellphone;
    /**
     * @Description 承诺公函图片地址
     */
    private String letterImageUrl;
    /**
     * @Description
     */
    private String declareGuideImageUrl;
    /**
     * @Description 服务中心名称
     */
    private String organizName;
    /**
     * @Description 编号
     */
    private String sn;
    /**
     * @Description 身份证正面
     */
    private String idFrontUrl;
    /**
     * @Description 身份证反面
     */
    private String idBackUrl;
    /**
     * @Description 状态
     */
    private String state;
    /**
     * @Description 服务中心号码
     */
    private String organizPhone;

    public String getState() {
        return state;
    }

    public JDeclarerList setState(String state) {
        this.state = state;
        return this;
    }

    public String getOrganizPhone() {
        return organizPhone;
    }

    public JDeclarerList setOrganizPhone(String organizPhone) {
        this.organizPhone = organizPhone;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public JDeclarerList setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public JDeclarerList setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }

    public String getSn() {
        return sn;
    }

    public JDeclarerList setSn(String sn) {
        this.sn = sn;
        return this;
    }

    public String getDeclareGuideImageUrl() {
        return declareGuideImageUrl;
    }

    public JDeclarerList setDeclareGuideImageUrl(String declareGuideImageUrl) {
        this.declareGuideImageUrl = declareGuideImageUrl;
        return this;
    }

    public Long getId() {
        return id;
    }

    public JDeclarerList setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public JDeclarerList setName(String name) {
        this.name = name;
        return this;
    }

    public String getPortrait() {
        return portrait;
    }

    public JDeclarerList setPortrait(String portrait) {
        this.portrait = portrait;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public JDeclarerList setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getScore() {
        return score;
    }

    public JDeclarerList setScore(String score) {
        this.score = score;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JDeclarerList setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getLetterImageUrl() {
        return letterImageUrl;
    }

    public JDeclarerList setLetterImageUrl(String letterImageUrl) {
        this.letterImageUrl = letterImageUrl;
        return this;
    }

    public String getOrganizName() {
        return organizName;
    }

    public JDeclarerList setOrganizName(String organizName) {
        this.organizName = organizName;
        return this;
    }
}
