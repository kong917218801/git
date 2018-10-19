package com.cpsdb.declareserv.params.subsidy;

import java.util.List;

public class JSubsidyWithdrawQueryParams {
    /**
     * @Description 申请人id
     */
    private Long userId;
    /**
     * @Description 申请人
     */
    private String username;
    /**
     * @Description 提现卡号
     */
    private String account;
    /**
     * @Description 交易号
     */
    private String sn;
    /**
     * @Description 状态
     */
    private String state;

    /**
     * @Description 选中的id
     */
    private List<Long> idList;

    public Long getUserId() {
        return userId;
    }

    public JSubsidyWithdrawQueryParams setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public JSubsidyWithdrawQueryParams setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public JSubsidyWithdrawQueryParams setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getSn() {
        return sn;
    }

    public JSubsidyWithdrawQueryParams setSn(String sn) {
        this.sn = sn;
        return this;
    }

    public String getState() {
        return state;
    }

    public JSubsidyWithdrawQueryParams setState(String state) {
        this.state = state;
        return this;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public JSubsidyWithdrawQueryParams setIdList(List<Long> idList) {
        this.idList = idList;
        return this;
    }
}
