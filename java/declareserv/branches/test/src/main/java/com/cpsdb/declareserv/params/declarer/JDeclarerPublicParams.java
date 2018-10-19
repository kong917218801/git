package com.cpsdb.declareserv.params.declarer;

public class JDeclarerPublicParams {

    /**
     * @Description 负责区域
     */
    private String areaCode;

    /**
     * @Description 名称
     */
    private String name;

    public String getAreaCode() {
        return areaCode;
    }

    public JDeclarerPublicParams setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getName() {
        return name;
    }

    public JDeclarerPublicParams setName(String name) {
        this.name = name;
        return this;
    }
}
