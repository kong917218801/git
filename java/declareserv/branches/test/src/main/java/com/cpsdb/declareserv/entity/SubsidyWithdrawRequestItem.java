package com.cpsdb.declareserv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.math.BigDecimal;

/**
 * @ClassName SubsidyWithdrawRequestItem
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:02
 */
public class SubsidyWithdrawRequestItem {

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
     * @Description 金额
     */
    private BigDecimal amount;
    /**
     * @Description 明细id
     */
    private Long fkSubsidyDetailId;
    /**
     * @Description 申请Id
     */
    private Long fkSubsidyWithdrawRequestId;
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

    public SubsidyWithdrawRequestItem setId(Long id) {
        this.id = id;
        return this;
    }
    public Integer getVersion() {
        return version;
    }

    public SubsidyWithdrawRequestItem setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public SubsidyWithdrawRequestItem setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Long getFkSubsidyDetailId() {
        return fkSubsidyDetailId;
    }

    public SubsidyWithdrawRequestItem setFkSubsidyDetailId(Long fkSubsidyDetailId) {
        this.fkSubsidyDetailId = fkSubsidyDetailId;
        return this;
    }

    public Long getFkSubsidyWithdrawRequestId() {
        return fkSubsidyWithdrawRequestId;
    }

    public SubsidyWithdrawRequestItem setFkSubsidyWithdrawRequestId(Long fkSubsidyWithdrawRequestId) {
        this.fkSubsidyWithdrawRequestId = fkSubsidyWithdrawRequestId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public SubsidyWithdrawRequestItem setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public SubsidyWithdrawRequestItem setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
