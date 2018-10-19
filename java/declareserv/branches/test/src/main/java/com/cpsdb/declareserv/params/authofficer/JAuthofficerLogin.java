package com.cpsdb.declareserv.params.authofficer;


/**
 * @author 李银
 * @ClassName JAuthofficerLogin
 * @Description
 * @date 2017-12-13 10:08:08
 */
public class JAuthofficerLogin {

    /**
     * @Description logo
     */
    private String logo;
    /**
     * @Description token
     */
    private String token;
    /**
     * @Description 头像
     */
    private String avator;
    /**
     * @Description 姓名
     */
    private String name;
    /**
     * @Description 身份证
     */
    private String idCard;
    /**
     * @Description 申报服务处
     */
    private String Organiz;
    /**
     * @Description 是否通过
     */
    private Boolean Ispass;
    /**
     * @Description 授权码
     */
    private String Authcode;
    /**
     * @Description 编号
     */
    private String sn;
    /**
     * @Description 认证官二维码
     */
    private String code;

    public String getCode() {
        return code;
    }

    public JAuthofficerLogin setCode(String code) {
        this.code = code;
        return this;
    }

    public String getSn() {
        return sn;
    }

    public JAuthofficerLogin setSn(String sn) {
        this.sn = sn;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public JAuthofficerLogin setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    public String getToken() {
        return token;
    }

    public JAuthofficerLogin setToken(String token) {
        this.token = token;
        return this;
    }


    public String getAvator() {
        return avator;
    }

    public JAuthofficerLogin setAvator(String avator) {
        this.avator = avator;
        return this;
    }

    public String getName() {
        return name;
    }

    public JAuthofficerLogin setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdCard() {
        return idCard;
    }

    public JAuthofficerLogin setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    public String getOrganiz() {
        return Organiz;
    }

    public JAuthofficerLogin setOrganiz(String organiz) {
        Organiz = organiz;
        return this;
    }

    public Boolean getIspass() {
        return Ispass;
    }

    public JAuthofficerLogin setIspass(Boolean ispass) {
        Ispass = ispass;
        return this;
    }

    public String getAuthcode() {
        return Authcode;
    }

    public JAuthofficerLogin setAuthcode(String authcode) {
        Authcode = authcode;
        return this;
    }
}
