package com.cpsdb.declareserv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * @author 李银
 * @ClassName SubsidyBank
 * @Description
 * @date 2018-09-20 17:21:47
 */
public class SubsidyBank {

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
     * @Description 1-申报官；2-申报机构；3-省级；4-市级
     */
    private Integer userType;
    /**
     * @Description 账号id；
     */
    private Long userId;
    /**
     * @Description 银行id
     */
    private Long fkBankId;
    /**
     * @Description 开户名，30字以内
     */
    private String name;
    /**
     * @Description 开户行，100字以内
     */
    private String bankName;
    /**
     * @Description 开户行账户
     */
    private String account;
    /**
     * @Description
     */
    private Date createTime;
    /**
     * @Description
     */
    private Date modifyTime;


    /**************额外字段*****************/
    /**
     * @Description 银行名称
     */
    private String bank;
    /**
     * @Description 银行logo
     */
    private String logo;

    public Long getId() {
        return id;
    }

    public SubsidyBank setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public SubsidyBank setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public Integer getUserType() {
        return userType;
    }

    public SubsidyBank setUserType(Integer userType) {
        this.userType = userType;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public SubsidyBank setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getFkBankId() {
        return fkBankId;
    }

    public SubsidyBank setFkBankId(Long fkBankId) {
        this.fkBankId = fkBankId;
        return this;
    }

    public String getName() {
        return name;
    }

    public SubsidyBank setName(String name) {
        this.name = name;
        return this;
    }

    public String getBankName() {
        return bankName;
    }

    public SubsidyBank setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public SubsidyBank setAccount(String account) {
        this.account = account;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public SubsidyBank setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public SubsidyBank setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public String getBank() {
        return bank;
    }

    public SubsidyBank setBank(String bank) {
        this.bank = bank;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public SubsidyBank setLogo(String logo) {
        this.logo = logo;
        return this;
    }
}
