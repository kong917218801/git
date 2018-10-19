package com.cpsdb.declareserv.params.organiz;

import java.util.Date;

public class JQueryList {
    /**
     * @Description 省级服务中心id
     */
    private Long fkProvenceCenterId;

    /**
     * @Description 行政区域编码
     */
    private String areaCode;
    /**
     * @Description 申报服务处名称
     */
    private String name;

    /**
     * @Description 状态
     */
    private String state;
    /**
     * @Description 开始时间
     */
    private Date createTimeGE;
    /**
     * @Description 结束时间
     */
    private Date  createTimeLE;



    public Long getFkProvenceCenterId() {
        return fkProvenceCenterId;
    }

    public JQueryList setFkProvenceCenterId(Long fkProvenceCenterId) {
        this.fkProvenceCenterId = fkProvenceCenterId;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public JQueryList setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getName() {
        return name;
    }

    public JQueryList setName(String name) {
        this.name = name;
        return this;
    }

    public String getState() {
        return state;
    }

    public JQueryList setState(String state) {
        this.state = state;
        return this;
    }

    public Date getCreateTimeGE() {
        return createTimeGE;
    }

    public JQueryList setCreateTimeGE(Date createTimeGE) {
        this.createTimeGE = createTimeGE;
        return this;
    }

    public Date getCreateTimeLE() {
        return createTimeLE;
    }

    public JQueryList setCreateTimeLE(Date createTimeLE) {
        this.createTimeLE = createTimeLE;
        return this;
    }
}
