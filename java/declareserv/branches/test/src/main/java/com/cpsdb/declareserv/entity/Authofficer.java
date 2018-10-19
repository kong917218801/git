package com.cpsdb.declareserv.entity;

import java.util.Date;

/**
 * @ClassName Authofficer
 * @Description
 * @author 李银
 * @date 2018-09-20 17:26:01
 */
public class Authofficer {

    /**
     * @Description 主键
     */
    private Integer id;


    /**
     * @Description 申报官头像
     */
    private String Avator;
    /**
     * @Description 申报认证官姓名
     */
    private String Name;
    /**
     * @Description 申报认证官的身份证号
     */
    private String idCard;
    /**
     * @Description 所属服务处
     */
    private String Organiz;
    /**
     * @Description 考试分数
     */
    private String Sore;
    /**
     * @Description 身份证照正面
     */
    private String CardY;
    /**
     * @Description 身份证照反面
     */
    private String CardN;
    /**
     * @Description 申报认证官电话
     */
    private String Tel;
    /**
     * @Description 公函签字照
     */
    private String Agreement;
    /**
     * @Description 是否通过审核
     */
    private Boolean Ispass;
    /**
     * @Description 申报认证官录入信息
     */
    private Long insertTime;
    /**
     * @Description 审核时间
     */
    private Long updateTime;
    /**
     * @Description 认证编码
     */
    private String Authcode;
    /**
     * @Description 
     */
    private String oPhone;
    /**
     * @Description 
     */
    private Date createTime;

    public Integer getId() {
       	return id;
    }

    public Authofficer setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getAvator() {
        return Avator;
    }

    public Authofficer setAvator(String Avator) {
        this.Avator = Avator;
        return this;
    }

    public String getName() {
        return Name;
    }

    public Authofficer setName(String Name) {
        this.Name = Name;
        return this;
    }

    public String getIdCard() {
        return idCard;
    }

    public Authofficer setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    public String getOrganiz() {
        return Organiz;
    }

    public Authofficer setOrganiz(String Organiz) {
        this.Organiz = Organiz;
        return this;
    }

    public String getSore() {
        return Sore;
    }

    public Authofficer setSore(String Sore) {
        this.Sore = Sore;
        return this;
    }

    public String getCardY() {
        return CardY;
    }

    public Authofficer setCardY(String CardY) {
        this.CardY = CardY;
        return this;
    }

    public String getCardN() {
        return CardN;
    }

    public Authofficer setCardN(String CardN) {
        this.CardN = CardN;
        return this;
    }

    public String getTel() {
        return Tel;
    }

    public Authofficer setTel(String Tel) {
        this.Tel = Tel;
        return this;
    }

    public String getAgreement() {
        return Agreement;
    }

    public Authofficer setAgreement(String Agreement) {
        this.Agreement = Agreement;
        return this;
    }

    public Boolean getIspass() {
        return Ispass;
    }

    public Authofficer setIspass(Boolean Ispass) {
        this.Ispass = Ispass;
        return this;
    }

    public Long getInsertTime() {
        return insertTime;
    }

    public Authofficer setInsertTime(Long insertTime) {
        this.insertTime = insertTime;
        return this;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public Authofficer setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getAuthcode() {
        return Authcode;
    }

    public Authofficer setAuthcode(String Authcode) {
        this.Authcode = Authcode;
        return this;
    }

    public String getOPhone() {
        return oPhone;
    }

    public Authofficer setOPhone(String oPhone) {
        this.oPhone = oPhone;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Authofficer setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

}
