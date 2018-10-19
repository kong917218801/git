package com.cpsdb.declareserv.dto;

import java.util.Date;

/**
 * @ClassName JProvenceCenter
 * @Description 给总后台查询使用
 * @author 老周
 * @date 2018-09-20 17:26:02
 */
public class JProvenceCenterList {

    /**
     * @Description 主键
     */
    private Long id;

    /**
     * @Description 编号
     */
    private String sn;

    /**
     * @Description 手机号码
     */
    private String cellphone;
    /**
     * @Description 负责人姓名
     */
    private String chargerName;

    /**
     * @Description 授权类型：A-A类, B-B类, C-C类，D-D类
     */
    private String type;
    /**
     * @Description 公司名称
     */
    private String companyName;
    /**
     * @Description 状态：waitPending--待初审，waitAudit--待审核，unpass--未通过，passed--已通过，deleting--删除中，deleted--已删除;
     */
    private String state;
    /**
     * @Description 负责区域
     */
    private String area;
    /**
     * @Description 详细地址
     */
    private String address;
    /**
     * @Description 创建时间
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public JProvenceCenterList setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSn() {
        return sn;
    }

    public JProvenceCenterList setSn(String sn) {
        this.sn = sn;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JProvenceCenterList setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getChargerName() {
        return chargerName;
    }

    public JProvenceCenterList setChargerName(String chargerName) {
        this.chargerName = chargerName;
        return this;
    }

    public String getType() {
        return type;
    }

    public JProvenceCenterList setType(String type) {
        this.type = type;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public JProvenceCenterList setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getState() {
        return state;
    }

    public JProvenceCenterList setState(String state) {
        this.state = state;
        return this;
    }

    public String getArea() {
        return area;
    }

    public JProvenceCenterList setArea(String area) {
        this.area = area;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public JProvenceCenterList setAddress(String address) {
        this.address = address;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JProvenceCenterList setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
