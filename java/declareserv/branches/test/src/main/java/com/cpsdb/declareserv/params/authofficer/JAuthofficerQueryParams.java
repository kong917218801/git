package com.cpsdb.declareserv.params.authofficer;

import java.util.Date;

public class JAuthofficerQueryParams {

    private Long id;
    /**
     * @Description 申报认证官姓名
     */
    private String name;
    /**
     * @Description 所属机构
     */
    private String organiz;
    /**
     * @Description 服务处电话
     */
    private String organizTel;

    /**
     * @Description 电话号码
     */
    private String cellphone;

    /**
     * @Description 状态
     */
    private Boolean state;

    /**
     * @Description 创建时间
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public JAuthofficerQueryParams setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public JAuthofficerQueryParams setName(String name) {
        this.name = name;
        return this;
    }

    public String getOrganiz() {
        return organiz;
    }

    public JAuthofficerQueryParams setOrganiz(String organiz) {
        this.organiz = organiz;
        return this;
    }

    public String getOrganizTel() {
        return organizTel;
    }

    public JAuthofficerQueryParams setOrganizTel(String organizTel) {
        this.organizTel = organizTel;
        return this;
    }

    public Boolean getState() {
        return state;
    }

    public JAuthofficerQueryParams setState(Boolean state) {
        this.state = state;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JAuthofficerQueryParams setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JAuthofficerQueryParams setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
