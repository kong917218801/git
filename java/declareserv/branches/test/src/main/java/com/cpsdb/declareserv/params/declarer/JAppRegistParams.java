package com.cpsdb.declareserv.params.declarer;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.common.CPSStringUtils;
import com.cpsdb.base.exception.CustomException;

public class JAppRegistParams {

    /**
     * @Description 姓名
     */
    private String name;
    /**
     * @Description 身份证
     */
    private String idnumber;
    /**
     * @Description 电话号码
     */
    private String cellphone;
    /**
     * @Description 密码
     */
    private String password;
    /**
     * @Description 推荐人
     */
    private String recommend;
    /**
     * @Description 二维码
     */
    private String code;

    public String getName() {
        return name;
    }

    public JAppRegistParams setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public JAppRegistParams setIdnumber(String idnumber) {
        this.idnumber = idnumber;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JAppRegistParams setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public JAppRegistParams setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRecommend() {
        return recommend;
    }

    public JAppRegistParams setRecommend(String recommend) {
        this.recommend = recommend;
        return this;
    }

    public String getCode() {
        return code;
    }

    public JAppRegistParams setCode(String code) {
        this.code = code;
        return this;
    }

    public JAppRegistParams validate() {

        AssertUtils.isTrue(CPSStringUtils.isNotBlank(this.name), new CustomException("请输入姓名！"));
        AssertUtils.isTrue(CPSStringUtils.isNotBlank(this.password), new CustomException("请输入密码！"));
        AssertUtils.isTrue(CPSStringUtils.isNotBlank(this.code), new CustomException("请输入验证码！"));
        AssertUtils.isTrue(CPSStringUtils.isNotBlank(this.cellphone), new CustomException("请输入电话号码！"));
        AssertUtils.isTrue(CPSStringUtils.isNotBlank(this.recommend), new CustomException("请输入推荐码！"));
        AssertUtils.isTrue(CPSStringUtils.isNotBlank(this.idnumber), new CustomException("请输入身份证号码！"));
        return this;
    }
}
