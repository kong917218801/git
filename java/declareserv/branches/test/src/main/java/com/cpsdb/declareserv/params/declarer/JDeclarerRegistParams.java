package com.cpsdb.declareserv.params.declarer;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.common.CPSStringUtils;
import com.cpsdb.base.exception.CustomException;

public class JDeclarerRegistParams {

    private String name;
    private String idNumber;
    private String areaCode;
    private String address;
    private String cellphone;

    public String getName() {
        return name;
    }

    public JDeclarerRegistParams setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public JDeclarerRegistParams setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public JDeclarerRegistParams setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public JDeclarerRegistParams setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JDeclarerRegistParams setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public JDeclarerRegistParams validates() {

        AssertUtils.isTrue(CPSStringUtils.isNotBlank(this.areaCode), new CustomException("请选择常驻区域！"));
        AssertUtils.isTrue(CPSStringUtils.isNotBlank(this.name), new CustomException("请输入名字！"));
        AssertUtils.isTrue(CPSStringUtils.isNotBlank(this.cellphone), new CustomException("请输入电话号码！"));
        return this;
    }
}
