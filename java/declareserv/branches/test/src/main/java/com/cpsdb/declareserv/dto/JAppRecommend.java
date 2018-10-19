package com.cpsdb.declareserv.dto;

import java.util.Date;

public class JAppRecommend {
    /**
     * @Decription id
     */
    private Long id;
    /**
     * @Decription 姓名
     */
    private String name;
    /**
     * @Decription 电话号码
     */
    private String cellphone;
    /**
     * @Decription 创建时间
     */
    private Date createTime;
    /**
     * @Decription 状态
     */
    private String state;

    public Long getId() {
        return id;
    }

    public JAppRecommend setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public JAppRecommend setName(String name) {
        this.name = name;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JAppRecommend setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JAppRecommend setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getState() {
        return state;
    }

    public JAppRecommend setState(String state) {
        this.state = state;
        return this;
    }
}
