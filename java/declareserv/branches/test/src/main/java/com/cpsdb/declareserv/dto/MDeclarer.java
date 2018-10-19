package com.cpsdb.declareserv.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * @ClassName Declarer
 * @Description
 * @author 孔清
 * @date 2018-03-05 14:00:23
 */
public class MDeclarer {

    /**
     * @fieldName id
     * @fieldType Long
     * @Description 主键
     */
    private Long id;
    /**
     * @Description 编号
     */
    private String sn;

    /**
     * 对象版本，乐观锁需要可使用
     */
    @JsonIgnore
    private Integer version;

    /**
     * @fieldName name
     * @fieldType String
     * @Description 申报官姓名
     */
    private String name;
    /**
     * @fieldName cellphone
     * @fieldType String
     * @Description 手机号码
     */
    private String cellphone;
    /**
     * @fieldName portrait
     * @fieldType String
     * @Description 头像
     */
    private String portrait;
    /**
     * @fieldName id_number
     * @fieldType String
     * @Description 证件号码
     */
    private String idNumber;
    /**
     * @fieldName id_front_url
     * @fieldType String
     * @Description 身份证正面
     */
    private String idFrontUrl;
    /**
     * @fieldName id_back_url
     * @fieldType String
     * @Description 身份证反面
     */
    private String idBackUrl;
    /**
     * @fieldName survey_image_url
     * @fieldType String
     * @Description 申报官尽职调查表
     */
    private String surveyImageUrl;
    /**
     * @fieldName letter_image_url
     * @fieldType String
     * @Description 申报官承诺公函
     */
    private String letterImageUrl;
    /**
     * @fieldName state
     * @fieldType String
     * @Description 状态: wait：审核中；passed：通过审核; failed: 未通过审核 ;deleting: 删除中;deleted: 已删除
     */
    private String state;
    /**
     * @fieldName reason
     * @fieldType String
     * @Description 未通过审核原因
     */
    private String reason;
    /**
     * @fieldName score
     * @fieldType Long
     * @Description 考试分数
     */
    private Long score;
    /**
     * @fieldName fk_organiz_id
     * @fieldType Long
     * @Description 申报机构id
     */
    private Long fkOrganizId;
    /**
     * @fieldName code
     * @fieldType String
     * @Description 申报官的二维码
     */
    private String code;
    /**
     * @fieldName code_state
     * @fieldType String
     * @Description 申报官的二维码状态: usable: 可用;unusable: 失效
     */
    private String codeState;
    /**
     * @fieldName code_state
     * @fieldType String
     * @Description 申报官的地址
     */
    private String areaCode;
    /**
     * @fieldName register_time
     * @fieldType Date
     * @Description 通过审核时间
     */
    private Long registerTime;
    /**
     * @fieldName create_time
     * @fieldType Date
     * @Description 
     */
    private Date createTime;
    /**
     * @fieldName modify_time
     * @fieldType Date
     * @Description 
     */
    private Date modifyTime;

    /**************新增字段**********************/

    /**
     * @Description 服务处名称
     */
    private String organizName;

    /**
     * @Description 服务中心名称
     */
    private String serviceName;
    /**
     * @Description 申请项
     */
    private String product;

    /**
     *
     * @Description 考试是否及格
     * @return
     */
    private Boolean ispass;

    public String getServiceName() {
        return serviceName;
    }

    public MDeclarer setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public Boolean getIspass() {
        return ispass;
    }

    public MDeclarer setIspass(Boolean ispass) {
        this.ispass = ispass;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public MDeclarer setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getOrganizName() {
        return organizName;
    }

    public MDeclarer setOrganizName(String organizName) {
        this.organizName = organizName;
        return this;
    }

    public String getProduct() {
        return product;
    }

    public MDeclarer setProduct(String product) {
        this.product = product;
        return this;
    }

    public Long getId() {
       	return id;
    }

    public MDeclarer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSn() {
        return sn;
    }

    public MDeclarer setSn(String sn) {
        this.sn = sn;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public MDeclarer setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getName() {
        return name;
    }

    public MDeclarer setName(String name) {
        this.name = name;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public MDeclarer setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getPortrait() {
        return portrait;
    }

    public MDeclarer setPortrait(String portrait) {
        this.portrait = portrait;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public MDeclarer setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public MDeclarer setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public MDeclarer setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }

    public String getSurveyImageUrl() {
        return surveyImageUrl;
    }

    public MDeclarer setSurveyImageUrl(String surveyImageUrl) {
        this.surveyImageUrl = surveyImageUrl;
        return this;
    }

    public String getLetterImageUrl() {
        return letterImageUrl;
    }

    public MDeclarer setLetterImageUrl(String letterImageUrl) {
        this.letterImageUrl = letterImageUrl;
        return this;
    }

    public String getState() {
        return state;
    }

    public MDeclarer setState(String state) {
        this.state = state;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public MDeclarer setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Long getScore() {
        return score;
    }

    public MDeclarer setScore(Long score) {
        this.score = score;
        return this;
    }

    public Long getFkOrganizId() {
        return fkOrganizId;
    }

    public MDeclarer setFkOrganizId(Long fkOrganizId) {
        this.fkOrganizId = fkOrganizId;
        return this;
    }

    public String getCode() {
        return code;
    }

    public MDeclarer setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCodeState() {
        return codeState;
    }

    public MDeclarer setCodeState(String codeState) {
        this.codeState = codeState;
        return this;
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public MDeclarer setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public MDeclarer setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public MDeclarer setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
