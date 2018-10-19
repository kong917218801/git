package com.cpsdb.declareserv.dto;

/**
 * @Author 老周
 * @Description 给申报自主管理后台注册页面提供公共访问
 * @CreateDate 2018.7.1
 * @Version v1.0
 */
public class JPublicsProvence {

    /**
     * @Description 主键
     */
    private Long id;
    /**
     * @Description 电话号码
     */
    private String cellphone;
    /**
     * @Description 负责人姓名
     * @Required
     */
    private String chargerName;

    /**
     * @Description 公司名称
     * @Required
     */
    private String companyName;

    /**
     * @Description 区域名称
     * @Required
     */
    private String area;
    /**
     * @Description 编号
     * @Required
     */
    private String sn;

    public String getSn() {
        return sn;
    }

    public JPublicsProvence setSn(String sn) {
        this.sn = sn;
        return this;
    }

    public String getArea() {
        return area;
    }

    public JPublicsProvence setArea(String area) {
        this.area = area;
        return this;
    }



    public Long getId() {
        return id;
    }

    public JPublicsProvence setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JPublicsProvence setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getChargerName() {
        return chargerName;
    }

    public JPublicsProvence setChargerName(String chargerName) {
        this.chargerName = chargerName;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public JPublicsProvence setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }
}
