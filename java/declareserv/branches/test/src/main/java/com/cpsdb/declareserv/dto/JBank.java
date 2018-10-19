package com.cpsdb.declareserv.dto;

import java.util.Date;

/**
 * @author 李银
 * @ClassName JBank
 * @Description
 * @date 2018-09-20 17:26:01
 */
public class JBank {

    /**
     * @Description 主键
     */
    private Long id;

    /**
     * @Description 银行名称
     */
    private String name;
    /**
     * @Description 银行logo
     */
    private String logo;

    public Long getId() {
        return id;
    }

    public JBank setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public JBank setName(String name) {
        this.name = name;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public JBank setLogo(String logo) {
        this.logo = logo;
        return this;
    }
}
