package com.cpsdb.declareserv.params.declarer;

import com.cpsdb.validator.Validate;

public class JInvoiceParams {

    /**
     * @Description 发票类型：person-个人,enterprise-企业
     */
    @Validate
    private String invoiceType;
    /**
     * @Description 姓名/企业名称
     */
    @Validate
    private String name;
    /**
     * @Description 手机号码
     */
    private String cellphone;
    /**
     * @Description 企业税号
     */
    private String revenueNumber;
    /**
     * @Description 邮箱地址
     */
    @Validate
    private String email;

    public String getInvoiceType() {
        return invoiceType;
    }

    public JInvoiceParams setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
        return this;
    }

    public String getName() {
        return name;
    }

    public JInvoiceParams setName(String name) {
        this.name = name;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JInvoiceParams setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getRevenueNumber() {
        return revenueNumber;
    }

    public JInvoiceParams setRevenueNumber(String revenueNumber) {
        this.revenueNumber = revenueNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public JInvoiceParams setEmail(String email) {
        this.email = email;
        return this;
    }
}
