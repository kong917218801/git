package com.cpsdb.declareserv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName DeclareToEnterprise
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:01
 */
public class DeclareToEnterprise {

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
     * @Description 企业名称
     */
    private String enterpriseName;
    /**
     * @Description 申报企业id
     */
    private Long fkDeclareEnterpriseId;
    /**
     * @Description 企业外键
     */
    private Long fkEnterpriseId;
    /**
     * @Description 企业状态
     */
    private String enterpriseState;
    /**
     * @Description 二维码支付金额
     */
    private BigDecimal paidAmount;
    /**
     * @Description 二维码数量
     */
    private Long codeCount;
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

    public DeclareToEnterprise setId(Long id) {
        this.id = id;
        return this;
    }
    public Integer getVersion() {
        return version;
    }

    public DeclareToEnterprise setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public DeclareToEnterprise setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
        return this;
    }

    public Long getFkDeclareEnterpriseId() {
        return fkDeclareEnterpriseId;
    }

    public DeclareToEnterprise setFkDeclareEnterpriseId(Long fkDeclareEnterpriseId) {
        this.fkDeclareEnterpriseId = fkDeclareEnterpriseId;
        return this;
    }

    public Long getFkEnterpriseId() {
        return fkEnterpriseId;
    }

    public DeclareToEnterprise setFkEnterpriseId(Long fkEnterpriseId) {
        this.fkEnterpriseId = fkEnterpriseId;
        return this;
    }

    public String getEnterpriseState() {
        return enterpriseState;
    }

    public DeclareToEnterprise setEnterpriseState(String enterpriseState) {
        this.enterpriseState = enterpriseState;
        return this;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public DeclareToEnterprise setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
        return this;
    }

    public Long getCodeCount() {
        return codeCount;
    }

    public DeclareToEnterprise setCodeCount(Long codeCount) {
        this.codeCount = codeCount;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public DeclareToEnterprise setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public DeclareToEnterprise setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
