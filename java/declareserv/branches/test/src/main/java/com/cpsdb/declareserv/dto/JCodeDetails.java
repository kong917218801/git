package com.cpsdb.declareserv.dto;

public class JCodeDetails {

    /**
     * @Description id
     */
    private Long id;
    /**
     * @Description 地址
     */
    private String address;
    /**
     * @Description 创建时间
     */
    private Long createTime;

    public Long getId() {
        return id;
    }

    public JCodeDetails setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public JCodeDetails setAddress(String address) {
        this.address = address;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public JCodeDetails setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }
}
