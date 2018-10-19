package com.cpsdb.declareserv.dto;

public class DeclareEnterpriseQuery {

    /**
     * @Description 序号
     */
    private Long id;
    /**
     * @Description 企业名称
     */
    private String name;
    /**
     * @Description 申报机构
     */
    private String organizName;
    /**
     * @Description 申报官
     */
    private String declarerName;

    /**
     * @Description 状态
     */
    private String state;
    /**
     * @Description 提交时间
     */
    private Long createTime;

    public Long getId() {
        return id;
    }

    public DeclareEnterpriseQuery setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DeclareEnterpriseQuery setName(String name) {
        this.name = name;
        return this;
    }

    public String getOrganizName() {
        return organizName;
    }

    public DeclareEnterpriseQuery setOrganizName(String organizName) {
        this.organizName = organizName;
        return this;
    }

    public String getDeclarerName() {
        return declarerName;
    }

    public DeclareEnterpriseQuery setDeclarerName(String declarerName) {
        this.declarerName = declarerName;
        return this;
    }

    public String getState() {
        return state;
    }

    public DeclareEnterpriseQuery setState(String state) {
        this.state = state;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public DeclareEnterpriseQuery setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }
}
