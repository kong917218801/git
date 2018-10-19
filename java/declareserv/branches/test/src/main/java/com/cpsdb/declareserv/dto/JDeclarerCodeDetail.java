package com.cpsdb.declareserv.dto;

import java.util.Date;
import java.math.BigDecimal;

/**
 * @ClassName JDeclarerCodeDetail
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:02
 */
public class JDeclarerCodeDetail {

    /**
     * @Description 主键
     */
    private Long id;

    /**
     * @Description 申报官外键
     */
    private Long fkDeclarerId;
    /**
     * @Description 扫码地的经度
     */
    private BigDecimal longitude;
    /**
     * @Description 扫码地的纬度
     */
    private BigDecimal latitude;
    /**
     * @Description 扫码地的区域code
     */
    private String areaCode;
    /**
     * @Description 具体的地址信息
     */
    private String address;
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

    public JDeclarerCodeDetail setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getFkDeclarerId() {
        return fkDeclarerId;
    }

    public JDeclarerCodeDetail setFkDeclarerId(Long fkDeclarerId) {
        this.fkDeclarerId = fkDeclarerId;
        return this;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public JDeclarerCodeDetail setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public JDeclarerCodeDetail setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public JDeclarerCodeDetail setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public JDeclarerCodeDetail setAddress(String address) {
        this.address = address;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JDeclarerCodeDetail setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public JDeclarerCodeDetail setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
