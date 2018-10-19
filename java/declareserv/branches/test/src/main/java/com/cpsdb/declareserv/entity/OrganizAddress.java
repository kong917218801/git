package com.cpsdb.declareserv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.math.BigDecimal;

/**
 * @ClassName OrganizAddress
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:02
 */
public class OrganizAddress {

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
    private Double longitude;
    /**
     * @Description 纬度
     */
    private Double latitude;
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

    public OrganizAddress setId(Long id) {
        this.id = id;
        return this;
    }
    public Integer getVersion() {
        return version;
    }

    public OrganizAddress setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public OrganizAddress setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public OrganizAddress setType(Integer type) {
        this.type = type;
        return this;
    }

    public Long getFkOrganizId() {
        return fkOrganizId;
    }

    public OrganizAddress setFkOrganizId(Long fkOrganizId) {
        this.fkOrganizId = fkOrganizId;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrganizAddress setAddress(String address) {
        this.address = address;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public OrganizAddress setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public OrganizAddress setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public OrganizAddress setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public OrganizAddress setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
