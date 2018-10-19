package com.cpsdb.declareserv.dto;

public class JAreaLimitOrganiz {

    /**
     * @Description 主键
     */
    private Long id;

    /**
     * @Description 地区code
     */
    private String areaCode;
    /**
     * @Description 剩余数
     */
    private Integer remainNumber;

    public Long getId() {
        return id;
    }

    public JAreaLimitOrganiz setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public JAreaLimitOrganiz setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public Integer getRemainNumber() {
        return remainNumber;
    }

    public JAreaLimitOrganiz setRemainNumber(Integer remainNumber) {
        this.remainNumber = remainNumber;
        return this;
    }
}
