package com.cpsdb.declareserv.dto;

import java.util.Date;
import java.math.BigDecimal;

/**
 * @ClassName JOrder
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:02
 */
public class JOrder {

    /**
     * @Description 主键
     */
    private Long id;

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

    public JOrder setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public JOrder setPayMethod(String payMethod) {
        this.payMethod = payMethod;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public JOrder setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public String getState() {
        return state;
    }

    public JOrder setState(String state) {
        this.state = state;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public JOrder setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Long getFkDeclarerId() {
        return fkDeclarerId;
    }

    public JOrder setFkDeclarerId(Long fkDeclarerId) {
        this.fkDeclarerId = fkDeclarerId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JOrder setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getPayTime() {
        return payTime;
    }

    public JOrder setPayTime(Date payTime) {
        this.payTime = payTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public JOrder setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
