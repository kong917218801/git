package com.cpsdb.declareserv.params.authofficer;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.common.CPSStringUtils;
import com.cpsdb.base.exception.CustomException;

public class JAuthofficerLoginParams {

    /**
     * @Description 电话号码
     */
    private String cellPhone;
    /**
     * @Description 二维码
     */
    private String code;

    public String getCellPhone() {
        return cellPhone;
    }

    public JAuthofficerLoginParams setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
        return this;
    }

    public String getCode() {
        return code;
    }

    public JAuthofficerLoginParams setCode(String code) {
        this.code = code;
        return this;
    }
    public JAuthofficerLoginParams validate() {
        AssertUtils.isTrue(CPSStringUtils.isNotBlank(this.cellPhone)
                        && CPSStringUtils.isNotBlank(this.code)
                , new CustomException("参数不正确"));
        return this;
    }

}
