package com.cpsdb.declareserv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

/**
 * @ClassName User
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:03
 */
public class User {

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
     * @Description 用户名
     */
    private String username;
    /**
     * @Description 密码
     */
    private String password;
    /**
     * @Description 随机数
     */
    private String random;
    /**
     * @Description 账户类型： 1、申报服务处 2、申报官 3、省级服务中心
     */
    private Integer type;
    /**
     * @Description 对应账户类型的外键
     */
    private Long objectId;
    /**
     * @Description 
     */
    private Integer state;
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

    public User setId(Long id) {
        this.id = id;
        return this;
    }
    public Integer getVersion() {
        return version;
    }

    public User setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRandom() {
        return random;
    }

    public User setRandom(String random) {
        this.random = random;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public User setType(Integer type) {
        this.type = type;
        return this;
    }

    public Long getObjectId() {
        return objectId;
    }

    public User setObjectId(Long objectId) {
        this.objectId = objectId;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public User setState(Integer state) {
        this.state = state;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public User setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public User setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
