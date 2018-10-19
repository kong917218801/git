package com.cpsdb.declareserv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.math.BigDecimal;

/**
 * @ClassName Order
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:02
 */
public class Order {

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
     * @Description 支付方式， 见pay-api中Paymethod枚举
     */
    private String payMethod;
    /**
     * @Description 金额
     */
    private BigDecimal amount;
    /**
     * @Description 状态: wait：待支付；success：支付成功; failed: 支付失败
     */
    private String state;
    /**
     * @Description 支付失败原因
     */
    private String reason;
    /**
     * @Description 申报官id
     */
    private Long fkDeclarerId;
    /**
     * @Description 
     */
    private Date createTime;
    /**
     * @Description 
     */
    private Date payTime;
    /**
     * @Description 
     */
    private Date modifyTime;

    public Long getId() {
       	return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }
    public Integer getVersion() {
        return version;
    }

    public Order setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public Order setPayMethod(String payMethod) {
        this.payMethod = payMethod;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Order setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public String getState() {
        return state;
    }

    public Order setState(String state) {
        this.state = state;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public Order setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Long getFkDeclarerId() {
        return fkDeclarerId;
    }

    public Order setFkDeclarerId(Long fkDeclarerId) {
        this.fkDeclarerId = fkDeclarerId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Order setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getPayTime() {
        return payTime;
    }

    public Order setPayTime(Date payTime) {
        this.payTime = payTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public Order setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
