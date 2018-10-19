package com.cpsdb.declareserv.params.organiz;

public class BaseInfoParams {
    /**
     * @Description 申报服务处名称
     * @Required
     */
    private String name;
    /**
     * @Description 证件号码
     * @Required
     */
    private String idNumber;
    /**
     * @Description 身份证正面
     * @Required
     */
    private String idFrontUrl;
    /**
     * @Description 身份证反面
     * @Required
     */
    private String idBackUrl;
    /**
     * @Description 支付凭证
     */
//    private String paymentUrl;


    /**
     * @Description 申报服务处注册公司全称
     * @Required
     */
    private String companyName;

    /**
     * @Description 地址
     * @Required
     */
    private String address;

    /**
     * @Description 常驻地址
     */
    private String liveAddress;

    /**
     * @Description 申报申请区域
     */
    private String applyAddress;

    /**
     * @Description 推荐者账号
     * @Required
     */
    private String recommend;




    public String getName() {
        return name;
    }

    public BaseInfoParams setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public BaseInfoParams setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public BaseInfoParams setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public BaseInfoParams setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }


    public String getCompanyName() {
        return companyName;
    }

    public BaseInfoParams setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public BaseInfoParams setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public BaseInfoParams setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getApplyAddress() {
        return applyAddress;
    }

    public BaseInfoParams setApplyAddress(String applyAddress) {
        this.applyAddress = applyAddress;
        return this;
    }

    public String getRecommend() {
        return recommend;
    }

    public BaseInfoParams setRecommend(String recommend) {
        this.recommend = recommend;
        return this;
    }
}
