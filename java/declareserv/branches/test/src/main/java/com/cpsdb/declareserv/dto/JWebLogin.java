package com.cpsdb.declareserv.dto;


/**
 * @author 李银
 * @ClassName JPlatformLogin
 * @Description 总后台登录返回数据
 * @date 2018年1月14日 12:05:00
 */
public class JWebLogin {


    private Long id;
    /**
     * @Description 姓名/机构名称/省级名称
     */
    private String name;

    /**
     * @Description 电话号码，也是推荐工号
     */
    private String cellphone;

    /**
     * @Description 寸照，网页端显示logo时候如果此项为空，则显示中华搜logo
     */
    private String portrait;

    /**
     * @Description token
     */
    private String token;

    /**
     * @Description 当前账号状态
     */
    private String state;
    /**
     * @Description 账号类型： 1-申报服务处; 2-申报官; 3-省级服务中心
     */
    private Integer type;

    /**
     * @Description 负责的地区
     */
    private String areaName;

    public String getAreaName() {
        return areaName;
    }

    public JWebLogin setAreaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    public String getName() {
        return name;
    }

    public JWebLogin setName(String name) {
        this.name = name;
        return this;
    }

    public Long getId() {
        return id;
    }

    public JWebLogin setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JWebLogin setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getPortrait() {
        return portrait;
    }

    public JWebLogin setPortrait(String portrait) {
        this.portrait = portrait;
        return this;
    }

    public String getToken() {
        return token;
    }

    public JWebLogin setToken(String token) {
        this.token = token;
        return this;
    }

    public String getState() {
        return state;
    }

    public JWebLogin setState(String state) {
        this.state = state;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public JWebLogin setType(Integer type) {
        this.type = type;
        return this;
    }
}
