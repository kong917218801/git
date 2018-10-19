package com.cpsdb.declareserv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

/**
 * @ClassName Invoice
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:02
 */
public class Invoice {

    /**
     * @Description 主键
     */
    private Long id;

    /**
     * @Description 对象版本，乐观锁需要可使用
     */
    @JsonIgnore
    private Integer version;

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

    public Invoice setId(Long id) {
        this.id = id;
        return this;
    }
    public Integer getVersion() {
        return version;
    }

    public Invoice setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public Invoice setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
        return this;
    }

    public String getName() {
        return name;
    }

    public Invoice setName(String name) {
        this.name = name;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public Invoice setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getRevenueNumber() {
        return revenueNumber;
    }

    public Invoice setRevenueNumber(String revenueNumber) {
        this.revenueNumber = revenueNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Invoice setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getFkOrderId() {
        return fkOrderId;
    }

    public Invoice setFkOrderId(Long fkOrderId) {
        this.fkOrderId = fkOrderId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Invoice setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public Invoice setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
