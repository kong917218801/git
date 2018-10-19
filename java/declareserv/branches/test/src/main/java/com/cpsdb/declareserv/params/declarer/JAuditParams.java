package com.cpsdb.declareserv.params.declarer;

public class JAuditParams {

    /**
     * @Description 审核状态
     */
    private Boolean state;
    /**
     * @Description 原因
     */
    private String reason;

    public Boolean getState() {
        return state;
    }

    public JAuditParams setState(Boolean state) {
        this.state = state;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public JAuditParams setReason(String reason) {
        this.reason = reason;
        return this;
    }
}
