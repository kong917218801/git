package com.cpsdb.declareserv.params.subsidy;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.common.CPSStringUtils;
import com.cpsdb.base.exception.CustomException;

public class JSubsidyBankParams {

    private Long bankId;
    private String bankBranch;
    private String name;
    private String bankCard;
    private String cellphone;
    private String code;

    public Long getBankId() {
        return bankId;
    }

    public JSubsidyBankParams setBankId(Long bankId) {
        this.bankId = bankId;
        return this;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public JSubsidyBankParams setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
        return this;
    }

    public String getName() {
        return name;
    }

    public JSubsidyBankParams setName(String name) {
        this.name = name;
        return this;
    }

    public String getBankCard() {
        return bankCard;
    }

    public JSubsidyBankParams setBankCard(String bankCard) {
        this.bankCard = bankCard;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JSubsidyBankParams setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getCode() {
        return code;
    }

    public JSubsidyBankParams setCode(String code) {
        this.code = code;
        return this;
    }

    public JSubsidyBankParams validate() {
        AssertUtils.isTrue(
                CPSStringUtils.isNotBlank(this.bankBranch)
                        && CPSStringUtils.isNotBlank(this.bankCard)
                        && CPSStringUtils.isNotBlank(this.cellphone)
                        && CPSStringUtils.isNotBlank(this.code)
                        && CPSStringUtils.isNotBlank(this.name)
                , new CustomException("参数不正确"));
        return this;
    }
}
