package com.cpsdb.declareserv.dto;

import java.math.BigDecimal;
import java.util.Date;

public class JOrganizWithdrewRcord {
    /**
     * @Description 交易号
     */
    private Long sn;
    /**
     * @Description 申请补贴时间
     */
    private Date createTime;
    /**
     * @Description 金额
     */
    private BigDecimal amount;
    /**
     * @Description 银行卡号
     */
    private String account;
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

    public JOrganizWithdrewRcord setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Long getSn() {
        return sn;
    }

    public JOrganizWithdrewRcord setSn(Long sn) {
        this.sn = sn;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JOrganizWithdrewRcord setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public JOrganizWithdrewRcord setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public JOrganizWithdrewRcord setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getState() {
        return state;
    }

    public JOrganizWithdrewRcord setState(String state) {
        this.state = state;
        return this;
    }
}
