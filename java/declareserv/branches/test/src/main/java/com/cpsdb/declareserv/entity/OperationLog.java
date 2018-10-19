package com.cpsdb.declareserv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

/**
 * @ClassName OperationLog
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:02
 */
public class OperationLog {

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
     * @Description 初始状态
     */
    private String stateFrom;
    /**
     * @Description 操作事件
     */
    private String event;
    /**
     * @Description 到达状态
     */
    private String stateTo;
    /**
     * @Description 谁操作的
     */
    private String owner;
    /**
     * @Description 操作对象id
     */
    private Long objectId;
    /**
     * @Description 操作对象的业务类型
     */
    private String type;
    /**
     * @Description 备注
     */
    private String mark;
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

    public OperationLog setId(Long id) {
        this.id = id;
        return this;
    }
    public Integer getVersion() {
        return version;
    }

    public OperationLog setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getStateFrom() {
        return stateFrom;
    }

    public OperationLog setStateFrom(String stateFrom) {
        this.stateFrom = stateFrom;
        return this;
    }

    public String getEvent() {
        return event;
    }

    public OperationLog setEvent(String event) {
        this.event = event;
        return this;
    }

    public String getStateTo() {
        return stateTo;
    }

    public OperationLog setStateTo(String stateTo) {
        this.stateTo = stateTo;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public OperationLog setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public Long getObjectId() {
        return objectId;
    }

    public OperationLog setObjectId(Long objectId) {
        this.objectId = objectId;
        return this;
    }

    public String getType() {
        return type;
    }

    public OperationLog setType(String type) {
        this.type = type;
        return this;
    }

    public String getMark() {
        return mark;
    }

    public OperationLog setMark(String mark) {
        this.mark = mark;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public OperationLog setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public OperationLog setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
