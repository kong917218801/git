package com.cpsdb.declareserv.params.provence;

public class JPublicsProvenceParams {
    /**
     * @Description 负责区域的区域code
     */
    private String areaCode;
    /**
     * @Description 公司名称
     */
    private String name;


    public String getAreaCode() {
        return areaCode;
    }

    public JPublicsProvenceParams setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getName() {
        return name;
    }

    public JPublicsProvenceParams setName(String name) {
        this.name = name;
        return this;
    }
}
