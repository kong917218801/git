package com.cpsdb.declareserv.params.provence;

import com.cpsdb.baseservapi.utils.AreaUtils;

import java.util.Date;

public class JProvenceQueryParams {
    /**
     * @Description 主键
     */
    private Long id;
    /**
     * @Description 手机号码
     */
    private String cellphone;
    /**
     * @Description 负责人姓名
     */
    private String chargerName;

    /**
     * @Description 授权类型：a-A类, b-B类, c-C类，d-D类
     */
    private String type;
    /**
     * @Description 公司名称
     */
    private String companyName;
    /**
     * @Description 法人证件号码
     * @Required
     */
    private String idNumber;
    /**
     * @Description 状态：waitPending--待初审，waitAudit--待审核，unpass--未通过，passed--已通过，deleting--删除中，deleted--已删除;
     */
    private String state;
    /**
     * @Description 负责区域
     * @Required
     */
    private String areaCode;
    /**
     * @Description 详细地址
     */
    private String address;
    /**
     * @Description 创建时间起始
     */
    private Date createTimeGE;
    /**
     * @Description 创建时间截至
     */
    private Date createTimeLE;


    public Long getId() {
        return id;
    }

    public JProvenceQueryParams setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JProvenceQueryParams setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getChargerName() {
        return chargerName;
    }

    public JProvenceQueryParams setChargerName(String chargerName) {
        this.chargerName = chargerName;
        return this;
    }

    public String getType() {
        return type;
    }

    public JProvenceQueryParams setType(String type) {
        this.type = type;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public JProvenceQueryParams setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public JProvenceQueryParams setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getState() {
        return state;
    }

    public JProvenceQueryParams setState(String state) {
        this.state = state;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public JProvenceQueryParams setAreaCode(String areaCode) {
        this.areaCode = AreaUtils.simplizedCode(areaCode);
        return this;
    }

    public String getAddress() {
        return address;
    }

    public JProvenceQueryParams setAddress(String address) {
        this.address = address;
        return this;
    }

    public Date getCreateTimeGE() {
        return createTimeGE;
    }

    public JProvenceQueryParams setCreateTimeGE(Date createTimeGE) {
        this.createTimeGE = createTimeGE;
        return this;
    }

    public Date getCreateTimeLE() {
        return createTimeLE;
    }

    public JProvenceQueryParams setCreateTimeLE(Date createTimeLE) {
        this.createTimeLE = createTimeLE;
        return this;
    }
}
