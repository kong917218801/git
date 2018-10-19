package com.cpsdb.declareserv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

/**
 * @ClassName Bank
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:01
 */
public class Bank {

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
     * @Description 银行名称
     */
    private String name;
    /**
     * @Description 银行logo
     */
    private String logo;
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

    public Bank setId(Long id) {
        this.id = id;
        return this;
    }
    public Integer getVersion() {
        return version;
    }

    public Bank setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getName() {
        return name;
    }

    public Bank setName(String name) {
        this.name = name;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public Bank setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Bank setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public Bank setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
