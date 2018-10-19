package com.cpsdb.declareserv.dto;

/**
 * @author 李银
 * @ClassName JSubsidyBank
 * @Description
 * @date 2018-09-20 17:21:47
 */
public class JSubsidyBank {

    /**
     * @Description 主键
     */
    private Long id;
    /**
     * @Description 银行id
     */
    private Long bankId;
    /**
     * @Description 银行名称
     */
    private String bank;
    /**
     * @Description 银行logo
     */
    private String logo;
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
     * @Description 预留手机号
     */
    private String cellphone;

    public Long getId() {
        return id;
    }

    public JSubsidyBank setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getBankId() {
        return bankId;
    }

    public JSubsidyBank setBankId(Long bankId) {
        this.bankId = bankId;
        return this;
    }

    public String getBank() {
        return bank;
    }

    public JSubsidyBank setBank(String bank) {
        this.bank = bank;
        return this;
    }

    public String getName() {
        return name;
    }

    public JSubsidyBank setName(String name) {
        this.name = name;
        return this;
    }

    public String getBankName() {
        return bankName;
    }

    public JSubsidyBank setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public JSubsidyBank setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public JSubsidyBank setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JSubsidyBank setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }
}
