package com.cpsdb.declareserv.dto;

public class JOrganizQuery {

    /**
     * @Description 主键
     */
    private Long id;
    /**
     * @Description 申报服务处名称
     */
    private String name;
    /**
     * @Description 手机号码
     */
    private String cellphone;
    /**
     * @Description 证件号码
     */
    private String idNumber;
    /**
     * @Description 申报服务处注册公司全称
     */
    private String companyName;

    /**
     * @Description 状态：baseWaitSubmit—基本信息待填写,baseWaitPending—基本信息待初审,baseWaitAudit—基本信息待审核,baseUnPass—基本信息未通过,registWaitSubmit—登记信息待填写,registWaitPending—登记信息待初审,registWaitAudit—登记信息待审核,registUnPass—登记信息未通过,delete—已删除,passed—通过审核
     */
    private String state;
    /**
     * @Description 添加时间
     */
    private Long createTime;
    /**
     * @Description 省级服务中心名称
     */
    private String provenceName;
    /**
     * @Description 序号
     */
    private String sn;




    public Long getId() {
        return id;
    }

    public JOrganizQuery setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public JOrganizQuery setName(String name) {
        this.name = name;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JOrganizQuery setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public JOrganizQuery setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public JOrganizQuery setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getState() {
        return state;
    }

    public JOrganizQuery setState(String state) {
        this.state = state;
        return this;
    }

    public JOrganizQuery setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public String getProvenceName() {
        return provenceName;
    }

    public JOrganizQuery setProvenceName(String provenceName) {
        this.provenceName = provenceName;
        return this;
    }

    public String getSn() {
        return sn;
    }

    public JOrganizQuery setSn(String sn) {
        this.sn = sn;
        return this;
    }
}
