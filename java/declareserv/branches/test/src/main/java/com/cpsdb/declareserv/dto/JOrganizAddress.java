package com.cpsdb.declareserv.dto;

import java.util.Date;
import java.math.BigDecimal;

/**
 * @ClassName JOrganizAddress
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:02
 */
public class JOrganizAddress {

    /**
     * @Description 主键
     */
    private Long id;

    /**
     * @Description 行政区域编码
     */
    private String areaCode;
    /**
     * @Description 地址类型：1、常驻地址 2、申报申请区域
     */
    private Integer type;
    /**
     * @Description 申报服务处id
     */
    private Long fkOrganizId;
    /**
     * @Description 详细地址
     */
    private String address;
    /**
     * @Description 经度
     */
    private BigDecimal longitude;
    /**
     * @Description 纬度
     */
    private BigDecimal latitude;
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

    public JOrganizAddress setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public JOrganizAddress setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public JOrganizAddress setType(Integer type) {
        this.type = type;
        return this;
    }

    public Long getFkOrganizId() {
        return fkOrganizId;
    }

    public JOrganizAddress setFkOrganizId(Long fkOrganizId) {
        this.fkOrganizId = fkOrganizId;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public JOrganizAddress setAddress(String address) {
        this.address = address;
        return this;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public JOrganizAddress setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public JOrganizAddress setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JOrganizAddress setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public JOrganizAddress setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
