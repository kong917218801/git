package com.cpsdb.declareserv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

/**
 * @ClassName AreaLimit
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:01
 */
public class AreaLimit {

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

    public AreaLimit setId(Long id) {
        this.id = id;
        return this;
    }
    public Integer getVersion() {
        return version;
    }

    public AreaLimit setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public AreaLimit setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public Integer getRemainNumber() {
        return remainNumber;
    }

    public AreaLimit setRemainNumber(Integer remainNumber) {
        this.remainNumber = remainNumber;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public AreaLimit setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public AreaLimit setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
