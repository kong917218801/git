package com.cpsdb.declareserv.params.declarer;

import java.util.Date;
import java.util.Set;

public class JEnterpriseQueryParams {

    /**
     * @Description 申报官ID
     */
    private Long organizId;
    /**
     * @Description 企业名称
     */
    private String name;
    /**
     * @Description 申报机构
     */
    private String organiz;
    /**
     * @Description 搜索开始时间
     */
    private Date startTime;
    /**
     * @Description 搜索结束时间
     */
    private Date endTime;
    /**
     * @Description 申报官状态
     */
    private String state;
    /**
     * @Description 申报机构名称
     */
    private String organizName;
    /**
     * @Description 所属区域
     */
    private Set<String> areaCode;

    public Set<String> getAreaCode() {
        return areaCode;
    }

    public JEnterpriseQueryParams setAreaCode(Set<String> areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getOrganizName() {
        return organizName;
    }

    public JEnterpriseQueryParams setOrganizName(String organizName) {
        this.organizName = organizName;
        return this;
    }


    public Long getOrganizId() {
        return organizId;
    }

    public JEnterpriseQueryParams setOrganizId(Long organizId) {
        this.organizId = organizId;
        return this;
    }

    public String getName() {
        return name;
    }

    public JEnterpriseQueryParams setName(String name) {
        this.name = name;
        return this;
    }

    public String getOrganiz() {
        return organiz;
    }

    public JEnterpriseQueryParams setOrganiz(String organiz) {
        this.organiz = organiz;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public JEnterpriseQueryParams setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public JEnterpriseQueryParams setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getState() {
        return state;
    }

    public JEnterpriseQueryParams setState(String state) {
        this.state = state;
        return this;
    }
}
