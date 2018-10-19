package com.cpsdb.declareserv.dto;

import java.util.Date;

/**
 * @ClassName JDeclareOrganiz
 * @Description
 * @author zdr
 * @date 2018-09-20 10:51:50
 */
public class JDeclareOrganiz {
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
     * @Description 申报服务处注册公司全称
     */
    private String companyName;
    /**
     * @Description 状态：baseWaitSubmit—基本信息待填写,baseWaitPending—基本信息待初审,baseWaitAudit—基本信息待审核,baseUnPass—基本信息未通过,registWaitSubmit—登记信息待填写,registWaitPending—登记信息待初审,registWaitAudit—登记信息待审核,registUnPass—登记信息未通过,delete—已删除,passed—通过审核
     */
    private String state;
    /**
     * @Description 申报服务处申报区域
     */
    private String chargeAddress;
    /**
     * @Description 申报服务处地址
     */
    private String address;
    /**
     * @Description 省级服务中心姓名
     * */
    private String provenceName;
    /**
     * @Description 序号
     */
    private String sn;
    /**
     * @Description 创建时间
     */
    private Date createTime;



    public Long getId() {
        return id;
    }

    public JDeclareOrganiz setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public JDeclareOrganiz setName(String name) {
        this.name = name;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JDeclareOrganiz setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public JDeclareOrganiz setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getState() {
        return state;
    }

    public JDeclareOrganiz setState(String state) {
        this.state = state;
        return this;
    }

    public String getChargeAddress() {
        return chargeAddress;
    }

    public JDeclareOrganiz setChargeAddress(String chargeAddress) {
        this.chargeAddress = chargeAddress;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public JDeclareOrganiz setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getProvenceName() {
        return provenceName;
    }

    public JDeclareOrganiz setProvenceName(String provenceName) {
        this.provenceName = provenceName;
        return this;
    }

    public String getSn() {
        return sn;
    }

    public JDeclareOrganiz setSn(String sn) {
        this.sn = sn;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JDeclareOrganiz setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
