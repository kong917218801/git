package com.cpsdb.declareserv.dto;

import java.math.BigDecimal;
import java.util.Date;

public class JPlatformWithdrewRcord {
    /**
     * @Description 交易号
     */
    private Long sn;
    /**
     * @Description 用户类型
     */
    private Integer userType;

    /**
     * @Description 申请人
     */
    private String username;
    /**
     * @Description 开户名
     */
    private String name;
    /**
     * @Description 银行名称
     */
    private String bank;
    /**
     * @Description 开户行
     */
    private String bankName;
    /**
     * @Description 银行卡号
     */
    private String account;
    /**
     * @Description 银行预留手机号
     */
    private String cellphone;
    /**
     * @Description 金额
     */
    private BigDecimal amount;
    /**
     * @Description 申请补贴时间
     */
    private Date createTime;
    /**
     * @Description 状态 pending-待审核；rejected-未通过；delayed-延后处理；passed-通过审核
     */
    private String state;
    /**
     * @Description 未通过原因
     */
    private String reason;

    public String getReason() {
        return reason;
    }

    public JPlatformWithdrewRcord setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Long getSn() {
        return sn;
    }

    public JPlatformWithdrewRcord setSn(Long sn) {
        this.sn = sn;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JPlatformWithdrewRcord setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public JPlatformWithdrewRcord setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public JPlatformWithdrewRcord setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getState() {
        return state;
    }

    public JPlatformWithdrewRcord setState(String state) {
        this.state = state;
        return this;
    }

    public Integer getUserType() {
        return userType;
    }

    public JPlatformWithdrewRcord setUserType(Integer userType) {
        this.userType = userType;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public JPlatformWithdrewRcord setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getName() {
        return name;
    }

    public JPlatformWithdrewRcord setName(String name) {
        this.name = name;
        return this;
    }

    public String getBankName() {
        return bankName;
    }

    public JPlatformWithdrewRcord setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String getBank() {
        return bank;
    }

    public JPlatformWithdrewRcord setBank(String bank) {
        this.bank = bank;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JPlatformWithdrewRcord setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }
}
