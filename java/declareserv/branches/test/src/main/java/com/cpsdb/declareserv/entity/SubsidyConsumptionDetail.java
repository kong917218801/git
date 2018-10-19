package com.cpsdb.declareserv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.math.BigDecimal;

/**
 * @ClassName SubsidyConsumptionDetail
 * @Description
 * @author 李银
 * @date 2018-09-20 17:21:47
 */
public class SubsidyConsumptionDetail {

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
     * @Description 1-入库费用；2-二维码使用费用；3-粉丝推送费用；其他待拓展
     */
    private Integer type;
    /**
     * @Description 和type组合使用，用于重新记录过滤；比如type=1时，key为企业支付订单id
     */
    private String key;
    /**
     * @Description 数量
     */
    private Integer quantity;
    /**
     * @Description 金额，小数点后两位
     */
    private BigDecimal amount;
    /**
     * @Description 所属企业外键id
     */
    private Long fkEnterpriseId;
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

    public SubsidyConsumptionDetail setId(Long id) {
        this.id = id;
        return this;
    }
    public Integer getVersion() {
        return version;
    }

    public SubsidyConsumptionDetail setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public SubsidyConsumptionDetail setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getKey() {
        return key;
    }

    public SubsidyConsumptionDetail setKey(String key) {
        this.key = key;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public SubsidyConsumptionDetail setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public SubsidyConsumptionDetail setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Long getFkEnterpriseId() {
        return fkEnterpriseId;
    }

    public SubsidyConsumptionDetail setFkEnterpriseId(Long fkEnterpriseId) {
        this.fkEnterpriseId = fkEnterpriseId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public SubsidyConsumptionDetail setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public SubsidyConsumptionDetail setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
