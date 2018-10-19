package com.cpsdb.declareserv.dto;

public class JOrganizList {
    /**
     * @Description 主键
     */
    private Long id;

    /**
     * @Description 申报服务处名称
     */
    private String name;

    public Long getId() {
        return id;
    }

    public JOrganizList setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public JOrganizList setName(String name) {
        this.name = name;
        return this;
    }
}
