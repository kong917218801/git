package com.cpsdb.declareserv.dto;

public class JDeclarerPublicList {

    /**
     * @Description 主键
     */
    private Long id;
    /**
     * @Description 主键
     */
    private String sn;
    /**
     * @Description 名称
     */
    private String name;

    /**
     * @Description 服务中心名称
     */
    private String organizName;
    /**
     * @Description 服务中心负责区域
     */
    private String organizAddress;

    /**
     * @Description 申报官负责区域
     */
    private String declarerAddress;

    /**
     * @Description 服务中心电话号码
     */
    private String organizCellphone;

    /**
     * @Description 申报官电话
     */
    private String cellphone;


    public String getSn() {
        return sn;
    }

    public JDeclarerPublicList setSn(String sn) {
        this.sn = sn;
        return this;
    }

    public String getDeclarerAddress() {
        return declarerAddress;
    }

    public JDeclarerPublicList setDeclarerAddress(String declarerAddress) {
        this.declarerAddress = declarerAddress;
        return this;
    }

    public Long getId() {
        return id;
    }

    public JDeclarerPublicList setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public JDeclarerPublicList setName(String name) {
        this.name = name;
        return this;
    }

    public String getOrganizName() {
        return organizName;
    }

    public JDeclarerPublicList setOrganizName(String organizName) {
        this.organizName = organizName;
        return this;
    }

    public String getOrganizAddress() {
        return organizAddress;
    }

    public JDeclarerPublicList setOrganizAddress(String organizAddress) {
        this.organizAddress = organizAddress;
        return this;
    }

    public String getOrganizCellphone() {
        return organizCellphone;
    }

    public JDeclarerPublicList setOrganizCellphone(String organizCellphone) {
        this.organizCellphone = organizCellphone;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JDeclarerPublicList setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }
}
