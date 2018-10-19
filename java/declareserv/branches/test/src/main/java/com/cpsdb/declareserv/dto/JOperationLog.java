package com.cpsdb.declareserv.dto;

import java.util.Date;

/**
 * @ClassName JOperationLog
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:02
 */
public class JOperationLog {

    /**
     * @Description 主键
     */
    private Long id;

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

    public JOperationLog setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStateFrom() {
        return stateFrom;
    }

    public JOperationLog setStateFrom(String stateFrom) {
        this.stateFrom = stateFrom;
        return this;
    }

    public String getEvent() {
        return event;
    }

    public JOperationLog setEvent(String event) {
        this.event = event;
        return this;
    }

    public String getStateTo() {
        return stateTo;
    }

    public JOperationLog setStateTo(String stateTo) {
        this.stateTo = stateTo;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public JOperationLog setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public Long getObjectId() {
        return objectId;
    }

    public JOperationLog setObjectId(Long objectId) {
        this.objectId = objectId;
        return this;
    }

    public String getType() {
        return type;
    }

    public JOperationLog setType(String type) {
        this.type = type;
        return this;
    }

    public String getMark() {
        return mark;
    }

    public JOperationLog setMark(String mark) {
        this.mark = mark;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JOperationLog setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public JOperationLog setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
