package com.cpsdb.declareserv.params.declarer;


import com.cpsdb.declareserv.utils.SnUtils;

import java.util.Date;
import java.util.Set;

/**
 * @author 李银
 * @ClassName JDeclarerQueryParams
 * @Description
 * @date 2018年1月14日 10:31:28
 */
public class JDeclarerQueryParams {

    /**
     * @Description id
     */
    private Long id;
    /**
     * @Description 服务处ID
     */
    private Long organizId;
    /**
     * @Description 名称
     */
    private String name;
    /**
     * @Description 电话号码
     */
    private String cellphone;
    /**
     * @Description 开始时间
     */
    private Date startTime;
    /**
     * @Description 结束时间
     */
    private Date endTime;
    /**
     * @Description 服务处名称
     */
    private String organizName;
    /**
     * @Description 状态
     */
    private String state;
    /**
     * @Description 负责区域
     */
    private Set<String> areaCode;

    public JDeclarerQueryParams setSn(String sn) {
        this.id = SnUtils.resolveDeclarer(sn);
        return this;
    }

    public Long getId() {
        return id;
    }

    public JDeclarerQueryParams setId(Long id) {
        this.id = id;
        return this;
    }

    public Set<String> getAreaCode() {
        return areaCode;
    }

    public JDeclarerQueryParams setAreaCode(Set<String> areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getOrganizName() {
        return organizName;
    }

    public JDeclarerQueryParams setOrganizName(String organizName) {
        this.organizName = organizName;
        return this;
    }

    public Long getOrganizId() {
        return organizId;
    }

    public JDeclarerQueryParams setOrganizId(Long organizId) {
        this.organizId = organizId;
        return this;
    }

    public String getName() {
        return name;
    }

    public JDeclarerQueryParams setName(String name) {
        this.name = name;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JDeclarerQueryParams setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public JDeclarerQueryParams setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public JDeclarerQueryParams setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getState() {
        return state;
    }

    public JDeclarerQueryParams setState(String state) {
        this.state = state;
        return this;
    }
}
