package com.cpsdb.declareserv.dto;

/**
 * @ClassName JOrganizEssential
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:02
 */
public class JOrganizEssential {

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
     * @Description 支付凭证
     */
//    private String paymentUrl;

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

    /**********新增字段*******************/

    /**
     * 具体所在地址
     * */
    private String address;
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

    public JOrganizEssential setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public JOrganizEssential setName(String name) {
        this.name = name;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JOrganizEssential setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public JOrganizEssential setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public JOrganizEssential setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public JOrganizEssential setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public JOrganizEssential setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public JOrganizEssential setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getState() {
        return state;
    }

    public JOrganizEssential setState(String state) {
        this.state = state;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public JOrganizEssential setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public JOrganizEssential setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
        return this;
    }

    public String getApplyAddress() {
        return applyAddress;
    }

    public JOrganizEssential setApplyAddress(String applyAddress) {
        this.applyAddress = applyAddress;
        return this;
    }

    public String getRecommend() {
        return recommend;
    }

    public JOrganizEssential setRecommend(String recommend) {
        this.recommend = recommend;
        return this;
    }
}
