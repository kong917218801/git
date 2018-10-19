package com.cpsdb.declareserv.dto;

import java.math.BigDecimal;

public class JPlatformWithdrewRcordAmounts {
    /**
     * @Description 对应状态的金额
     */
    private BigDecimal amount;
    /**
     * @Description 对应状态的笔数
     */
    private Integer count;
    /**
     * @Description 状态
     */
    private String state;

    public BigDecimal getAmount() {
        return amount;
    }

    public JPlatformWithdrewRcordAmounts setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public JPlatformWithdrewRcordAmounts setCount(Integer count) {
        this.count = count;
        return this;
    }

    public String getState() {
        return state;
    }

    public JPlatformWithdrewRcordAmounts setState(String state) {
        this.state = state;
        return this;
    }
}
