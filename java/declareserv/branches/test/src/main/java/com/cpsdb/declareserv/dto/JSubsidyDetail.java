package com.cpsdb.declareserv.dto;

import java.math.BigDecimal;

/**
 * @author 李银
 * @ClassName JSubsidyDetail
 * @Description
 * @date 2018-09-20 17:52:30
 */
public class JSubsidyDetail {
    /**
     * @Description 补贴名称 -- 直接显示
     */
    private String name;
    /**
     * @Description 补贴类型 -- 直接显示 （企业入库， 购买二维码， 推荐补贴其中一项）;
     */
    private String type;
    /**
     * @Description 数量
     */
    private Integer quantity;
    /**
     * @Description 总金额，小数点后两位
     */
    private String amount;

    public String getName() {
        return name;
    }

    public JSubsidyDetail setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public JSubsidyDetail setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public JSubsidyDetail setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getAmount() {
        return amount;
    }

    public JSubsidyDetail setAmount(String amount) {
        this.amount = amount;
        return this;
    }
}
