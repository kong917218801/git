package com.cpsdb.declareserv.dto;

import java.util.Date;

/**
 * @ClassName JAreaLimit
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:01
 */
public class JAreaLimit {

    /**
     * @Description 主键
     */
    private Long id;

    /**
     * @Description 地区code
     */
    private String areaCode;
    /**
     * @Description 剩余数
     */
    private Integer remainNumber;
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

    public JAreaLimit setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public JAreaLimit setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public Integer getRemainNumber() {
        return remainNumber;
    }

    public JAreaLimit setRemainNumber(Integer remainNumber) {
        this.remainNumber = remainNumber;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JAreaLimit setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public JAreaLimit setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
