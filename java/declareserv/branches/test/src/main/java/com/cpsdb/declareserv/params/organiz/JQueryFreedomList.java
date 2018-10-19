package com.cpsdb.declareserv.params.organiz;

public class JQueryFreedomList {
    /**
     * @Description 行政区域编码
     */
    private String areaCode;
    /**
     * @Description 申报服务处名称
     */
    private String name;

    public String getAreaCode() {
        return areaCode;
    }

    public JQueryFreedomList setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getName() {
        return name;
    }

    public JQueryFreedomList setName(String name) {
        this.name = name;
        return this;
    }
}
