package com.cpsdb.declareserv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.math.BigDecimal;

/**
 * @author 李银
 * @ClassName SubsidyWithdrawRequest
 * @Description
 * @date 2018-09-20 17:26:02
 */
public class SubsidyWithdrawRequest {

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
     * @Description 拥有者id
     */
    private Long userId;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * @Description 账号名称
     */
    private String username;
    /**
     * @Description 提取补贴到的账户外键
     */
    private Long fkSubsidyBankId;
    /**
     * @Description pending-待审核；passed-通过；rejected-未通过;delayed-延后；要用状态机哈
     */
    private String state;
    /**
     * @Description 延后，不通过原因；200字以内
     */
    private String reason;
    /**
     * @Description 提取金额总和，subsidy_withdraw_request_item汇总
     */
    private BigDecimal amount;
    /**
     * @Description
     */
    private Date createTime;
    /**
     * @Description
     */
    private Date modifyTime;
    /**
     * @Description 手机号
     */
    private String cellphone;

    /********额外字段*********/
    /**
     * @Description 提现卡号
     */
    private String account;
    /**
     * @Description 银行名称
     */
    private String bank;
    /**
     * @Description 开户行
     */
    private String bankName;

    /**
     * @Description 开户名
     */
    private String name;

    public Long getId() {
        return id;
    }

    public SubsidyWithdrawRequest setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public SubsidyWithdrawRequest setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public SubsidyWithdrawRequest setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Integer getUserType() {
        return userType;
    }

    public SubsidyWithdrawRequest setUserType(Integer userType) {
        this.userType = userType;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public SubsidyWithdrawRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getFkSubsidyBankId() {
        return fkSubsidyBankId;
    }

    public SubsidyWithdrawRequest setFkSubsidyBankId(Long fkSubsidyBankId) {
        this.fkSubsidyBankId = fkSubsidyBankId;
        return this;
    }

    public String getState() {
        return state;
    }

    public SubsidyWithdrawRequest setState(String state) {
        this.state = state;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public SubsidyWithdrawRequest setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public SubsidyWithdrawRequest setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public SubsidyWithdrawRequest setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public SubsidyWithdrawRequest setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public SubsidyWithdrawRequest setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public SubsidyWithdrawRequest setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getBankName() {
        return bankName;
    }

    public SubsidyWithdrawRequest setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String getName() {
        return name;
    }

    public SubsidyWithdrawRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getBank() {
        return bank;
    }

    public SubsidyWithdrawRequest setBank(String bank) {
        this.bank = bank;
        return this;
    }
}
