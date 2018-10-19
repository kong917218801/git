package com.cpsdb.declareserv.params.authofficer;

import com.cpsdb.declareserv.utils.SnUtils;

import java.util.Date;

/**
 * @author 孔清
 * @ClassName JAuthofficer
 * @Description
 * @date 2018-01-05 14:58:52
 */
public class JAuthofficer {

    private Long id;

    /**
     * @Description 申报官头像
     */
    private String avator;
    /**
     * @Description 申报认证官姓名
     */
    private String name;
    /**
     * @fieldName idCard
     * @fieldType String
     * @Description 申报认证官的身份证号
     */
    private String idCard;
    /**
     * @Description 所属机构
     */
    private String organiz;
    /**
     * @Description 所属机构电话
     */
    private String organizTel;
    /**
     * @Description 考试分数
     */
    private String score;
    /**
     * @Description 身份证照正面
     */
    private String idFrontUrl;
    /**
     * @Description 身份证照反面
     */
    private String idBackUrl;
    /**
     * @Description 公函签字照
     */
    private String agreement;

    private String cellphone;

    private Boolean state;

    private Date createTime;

    private String qrcode;

    public String getAuthCode() {
        return SnUtils.getAuthofficer(this.id.toString());
    }

    public String getAvator() {
        return avator;
    }

    public JAuthofficer setAvator(String avator) {
        this.avator = avator;
        return this;
    }

    public String getName() {
        return name;
    }

    public JAuthofficer setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdCard() {
        return idCard;
    }

    public JAuthofficer setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    public String getOrganiz() {
        return organiz;
    }

    public JAuthofficer setOrganiz(String organiz) {
        this.organiz = organiz;
        return this;
    }

    public String getOrganizTel() {
        return organizTel;
    }

    public JAuthofficer setOrganizTel(String organizTel) {
        this.organizTel = organizTel;
        return this;
    }

    public String getScore() {
        return score;
    }

    public JAuthofficer setScore(String score) {
        this.score = score;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public JAuthofficer setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public JAuthofficer setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }

    public String getAgreement() {
        return agreement;
    }

    public JAuthofficer setAgreement(String agreement) {
        this.agreement = agreement;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JAuthofficer setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public Boolean getState() {
        return state;
    }

    public JAuthofficer setState(Boolean state) {
        this.state = state;
        return this;
    }

    public Long getId() {
        return id;
    }

    public JAuthofficer setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JAuthofficer setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getQrcode() {
        return qrcode;
    }

    public JAuthofficer setQrcode(String qrcode) {
        this.qrcode = qrcode;
        return this;
    }
}
