package com.cpsdb.declareserv.dto;

import java.util.Date;

/**
 * @ClassName JDeclareToEnterprise
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:01
 */
public class JDeclareToEnterprise {

    /**
     * @Description 主键
     */
    private Long id;

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
    private Long paidAmount;
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

    public JDeclareToEnterprise setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public JDeclareToEnterprise setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
        return this;
    }

    public Long getFkDeclareEnterpriseId() {
        return fkDeclareEnterpriseId;
    }

    public JDeclareToEnterprise setFkDeclareEnterpriseId(Long fkDeclareEnterpriseId) {
        this.fkDeclareEnterpriseId = fkDeclareEnterpriseId;
        return this;
    }

    public Long getFkEnterpriseId() {
        return fkEnterpriseId;
    }

    public JDeclareToEnterprise setFkEnterpriseId(Long fkEnterpriseId) {
        this.fkEnterpriseId = fkEnterpriseId;
        return this;
    }

    public String getEnterpriseState() {
        return enterpriseState;
    }

    public JDeclareToEnterprise setEnterpriseState(String enterpriseState) {
        this.enterpriseState = enterpriseState;
        return this;
    }

    public Long getPaidAmount() {
        return paidAmount;
    }

    public JDeclareToEnterprise setPaidAmount(Long paidAmount) {
        this.paidAmount = paidAmount;
        return this;
    }

    public Long getCodeCount() {
        return codeCount;
    }

    public JDeclareToEnterprise setCodeCount(Long codeCount) {
        this.codeCount = codeCount;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JDeclareToEnterprise setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public JDeclareToEnterprise setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
