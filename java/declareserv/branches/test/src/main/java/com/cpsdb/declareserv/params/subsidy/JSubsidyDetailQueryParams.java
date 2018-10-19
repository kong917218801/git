package com.cpsdb.declareserv.params.subsidy;

import java.util.Date;

/**
 * @ClassName JSubsidyDetailQueryParams
 * @Description
 * @author 李银
 * @date 2018年9月20日 20:09:12
 */
public class JSubsidyDetailQueryParams {
    /**
     * @Description 拥有者id
     */
    private Long userId;
    /**
     * @Description 补贴名称
     */
    private String name;
    /**
     * @Description 补贴类型
     */
    private String type;
    /**
     * @Description unwithdrawed-待提取；withdrawing-提取中；withdrawed-已提取
     */
    private String state;
    /**
     * @Description 生成时间起始
     */
    private Date createTimeGE;
    /**
     * @Description 生成时间截至
     */
    private Date createTimeLE;

    public Long getUserId() {
        return userId;
    }

    public JSubsidyDetailQueryParams setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public JSubsidyDetailQueryParams setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public JSubsidyDetailQueryParams setType(String type) {
        this.type = type;
        return this;
    }

    public String getState() {
        return state;
    }

    public JSubsidyDetailQueryParams setState(String state) {
        this.state = state;
        return this;
    }

    public Date getCreateTimeGE() {
        return createTimeGE;
    }

    public JSubsidyDetailQueryParams setCreateTimeGE(Date createTimeGE) {
        this.createTimeGE = createTimeGE;
        return this;
    }

    public Date getCreateTimeLE() {
        return createTimeLE;
    }

    public JSubsidyDetailQueryParams setCreateTimeLE(Date createTimeLE) {
        this.createTimeLE = createTimeLE;
        return this;
    }
}
