package com.cpsdb.declareserv.params.organiz;

import java.util.Date;

/**
 * @ClassName JOrganizQueryParams
 * @Description
 * @author zdr
 * @date 2018-09-20 10:51:50
 */
public class JOrganizQueryParams {

    /**
     * @Description 申报服务处名称
     */
    private String name;
    /**
     * @Description 手机号码
     */
    private String cellphone;
    /**
     * 状态：baseWaitSubmit—基本信息待填写,baseWaitPending—基本信息待初审,baseWaitAudit—基本信息待审核,baseUnPass—基本信息未通过,registWaitSubmit—登记信息待填写,registWaitPending—登记信息待初审,registWaitAudit—登记信息待审核,registUnPass—登记信息未通过,delete—已删除,passed—通过审核
     */
    private String state;
    /**
     * @Description 开始时间
     */
    private Date createTimeGE;
    /**
     * @Description 结束时间
     */
    private Date  createTimeLE;
    /**
     * @Description 申报服务处注册公司全称
     */
    private String companyName;



    public String getName() {
        return name;
    }

    public JOrganizQueryParams setName(String name) {
        this.name = name;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JOrganizQueryParams setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getState() {
        return state;
    }

    public JOrganizQueryParams setState(String state) {
        this.state = state;
        return this;
    }

    public Date getCreateTimeGE() {
        return createTimeGE;
    }

    public JOrganizQueryParams setCreateTimeGE(Date createTimeGE) {
        this.createTimeGE = createTimeGE;
        return this;
    }

    public Date getCreateTimeLE() {
        return createTimeLE;
    }

    public JOrganizQueryParams setCreateTimeLE(Date createTimeLE) {
        this.createTimeLE = createTimeLE;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public JOrganizQueryParams setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }
}
