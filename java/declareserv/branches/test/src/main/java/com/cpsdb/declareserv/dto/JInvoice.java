package com.cpsdb.declareserv.dto;

import java.util.Date;

/**
 * @ClassName JInvoice
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:02
 */
public class JInvoice {

    /**
     * @Description 主键
     */
    private Long id;

    /**
     * @Description 发票类型：person-个人,enterprise-企业
     */
    private String invoiceType;
    /**
     * @Description 支付方
     */
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
    private String email;
    /**
     * @Description order 外键
     */
    private Long fkOrderId;
    /**
     * @Description 
     */
    private Date createTime;
    /**
     * @Description 
     */
    private Date modifyTime;

    public Long getId() {
       	return id;
    }

    public JInvoice setId(Long id) {
        this.id = id;
        return this;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public JInvoice setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
        return this;
    }

    public String getName() {
        return name;
    }

    public JInvoice setName(String name) {
        this.name = name;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JInvoice setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getRevenueNumber() {
        return revenueNumber;
    }

    public JInvoice setRevenueNumber(String revenueNumber) {
        this.revenueNumber = revenueNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public JInvoice setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getFkOrderId() {
        return fkOrderId;
    }

    public JInvoice setFkOrderId(Long fkOrderId) {
        this.fkOrderId = fkOrderId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JInvoice setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public JInvoice setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
