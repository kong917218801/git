package com.cpsdb.declareserv.dto;

import com.cpsdb.declareserv.entity.Invoice;
import com.cpsdb.declareserv.entity.OperationLog;
import com.cpsdb.declareserv.entity.Order;

import java.util.Date;
import java.util.List;

/**
 * @ClassName JDeclarer
 * @Description
 * @author 孔清
 * @date 2018-03-05 14:00:23
 */
public class JDeclarerDto {

    /**
     * @fieldName id
     * @fieldType Long
     * @Description 主键
     */
    private Long id;

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
     * @fieldName declare_guide_image_url
     * @fieldType String
     * @Description 申报官申报指南
     */
    private String declareGuideImageUrl;
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
     * @Description 申报官的地区code
     */
    private String areaCode;

    /**
     * @Description 申报服务处名称
     */
    private String organizName;
    /**
     * @Description 省级服务中心名称
     */
    private String provenceName;
    /**
        * @Description 性别
     */
    private String sex;
    /**
     * @Description 名族
     */
    private String nation;
    /**
     * @Description 毕业院校
     */
    private String graduate;
    /**
     * @Description 学历
     */
    private String education;
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
    /**
     * @Description  详细地址
     */
    private String address;

    /**
     * @Description  发票信息
     */
    private Invoice invoice;
    /**
     * @Description  订单信息
     */
    private Order order;
    /**
     * @Description  日志信息
     */
    private List<OperationLog> logs;
    /**
     * @Description 编号
     */
    private String sn;
    /**
     * @Description 推荐人
     */
    private String recommend;

    public String getRecommend() {
        return recommend;
    }

    public JDeclarerDto setRecommend(String recommend) {
        this.recommend = recommend;
        return this;
    }

    public List<OperationLog> getLogs() {
        return logs;
    }

    public JDeclarerDto setLogs(List<OperationLog> logs) {
        this.logs = logs;
        return this;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public JDeclarerDto setInvoice(Invoice invoice) {
        this.invoice = invoice;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public JDeclarerDto setOrder(Order order) {
        this.order = order;
        return this;
    }

    public String getSn() {
        return sn;
    }

    public JDeclarerDto setSn(String sn) {
        this.sn = sn;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public JDeclarerDto setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getNation() {
        return nation;
    }

    public JDeclarerDto setNation(String nation) {
        this.nation = nation;
        return this;
    }

    public String getGraduate() {
        return graduate;
    }

    public JDeclarerDto setGraduate(String graduate) {
        this.graduate = graduate;
        return this;
    }

    public String getEducation() {
        return education;
    }

    public JDeclarerDto setEducation(String education) {
        this.education = education;
        return this;
    }

    public String getOrganizName() {
        return organizName;
    }

    public JDeclarerDto setOrganizName(String organizName) {
        this.organizName = organizName;
        return this;
    }

    public String getProvenceName() {
        return provenceName;
    }

    public JDeclarerDto setProvenceName(String provenceName) {
        this.provenceName = provenceName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public JDeclarerDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDeclareGuideImageUrl() {
        return declareGuideImageUrl;
    }

    public JDeclarerDto setDeclareGuideImageUrl(String declareGuideImageUrl) {
        this.declareGuideImageUrl = declareGuideImageUrl;
        return this;
    }


    public String getAreaCode() {
        return areaCode;
    }

    public JDeclarerDto setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public Long getId() {
       	return id;
    }

    public JDeclarerDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public JDeclarerDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JDeclarerDto setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getPortrait() {
        return portrait;
    }

    public JDeclarerDto setPortrait(String portrait) {
        this.portrait = portrait;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public JDeclarerDto setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public JDeclarerDto setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public JDeclarerDto setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }

    public String getSurveyImageUrl() {
        return surveyImageUrl;
    }

    public JDeclarerDto setSurveyImageUrl(String surveyImageUrl) {
        this.surveyImageUrl = surveyImageUrl;
        return this;
    }

    public String getLetterImageUrl() {
        return letterImageUrl;
    }

    public JDeclarerDto setLetterImageUrl(String letterImageUrl) {
        this.letterImageUrl = letterImageUrl;
        return this;
    }

    public String getState() {
        return state;
    }

    public JDeclarerDto setState(String state) {
        this.state = state;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public JDeclarerDto setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Long getScore() {
        return score;
    }

    public JDeclarerDto setScore(Long score) {
        this.score = score;
        return this;
    }

    public Long getFkOrganizId() {
        return fkOrganizId;
    }

    public JDeclarerDto setFkOrganizId(Long fkOrganizId) {
        this.fkOrganizId = fkOrganizId;
        return this;
    }

    public String getCode() {
        return code;
    }

    public JDeclarerDto setCode(String code) {
        this.code = code;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JDeclarerDto setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public JDeclarerDto setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

}
