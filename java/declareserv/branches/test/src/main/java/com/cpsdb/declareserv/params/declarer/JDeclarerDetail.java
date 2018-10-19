package com.cpsdb.declareserv.params.declarer;

import com.cpsdb.declareserv.entity.Invoice;
import com.cpsdb.declareserv.entity.OperationLog;
import com.cpsdb.declareserv.entity.Order;

import java.util.Date;
import java.util.List;

/**
 * @author 孔清
 * @ClassName Declarer
 * @Description
 * @date 2018-03-05 14:00:23
 */
public class JDeclarerDetail {

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
     * @fieldName letter_image_url
     * @fieldType String
     * @Description 申报官承诺公函
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
     * @fieldName payTime
     * @fieldType Date
     * @Description
     */
    private Date payTime;

    /**************新增字段**********************/
    private String organizName;

    /**
     * 申请项
     */
    private String product;

    /**
     * 考试是否及格
     *
     * @return
     */
    private Boolean ispass;

    private String address;

    private String area;

    private String code;

    /**
     * @Description 我的推荐人
     */
    private String recommend;

    /**
     * 服务协议
     * @return
     */
    private String serviceAgreement;
    /**
     * 性别
     * @return
     */
    private String sex;
    /**
     * 民族
     * @return
     */
    private String nation;
    /**
     * 学历
     * @return
     */
    private String education;
    /**
     * 毕业院校
     * @return
     */
    private String graduate;
    /**
     * 发票信息
     * @return
     */
    private Invoice invoice;
    /**
     * 订单信息
     * @return
     */
    private Order order;
    /**
     * @Description 编号
     */
    private String sn;
    /**
     * @Description 隶属机构
     */
    private String organiz;

    /**
     * @Description 财务凭证
     */
    private String paymentUrl;

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public JDeclarerDetail setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
        return this;
    }

    public Date getPayTime() {
        return payTime;
    }

    public JDeclarerDetail setPayTime(Date payTime) {
        this.payTime = payTime;
        return this;
    }

    public String getOrganiz() {
        return organiz;
    }

    public JDeclarerDetail setOrganiz(String organiz) {
        this.organiz = organiz;
        return this;
    }

    public String getSn() {
        return sn;
    }

    public JDeclarerDetail setSn(String sn) {
        this.sn = sn;
        return this;
    }

    public String getServiceAgreement() {
        return serviceAgreement;
    }

    public JDeclarerDetail setServiceAgreement(String serviceAgreement) {
        this.serviceAgreement = serviceAgreement;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public JDeclarerDetail setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getNation() {
        return nation;
    }

    public JDeclarerDetail setNation(String nation) {
        this.nation = nation;
        return this;
    }

    public String getEducation() {
        return education;
    }

    public JDeclarerDetail setEducation(String education) {
        this.education = education;
        return this;
    }

    public String getGraduate() {
        return graduate;
    }

    public JDeclarerDetail setGraduate(String graduate) {
        this.graduate = graduate;
        return this;
    }

    public String getRecommend() {
        return recommend;
    }

    public JDeclarerDetail setRecommend(String recommend) {
        this.recommend = recommend;
        return this;
    }

    public String getCode() {
        return code;
    }

    public JDeclarerDetail setCode(String code) {
        this.code = code;
        return this;
    }

    private List<OperationLog> logs;

    public String getAddress() {
        return address;
    }

    public JDeclarerDetail setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDeclareGuideImageUrl() {
        return declareGuideImageUrl;
    }

    public JDeclarerDetail setDeclareGuideImageUrl(String declareGuideImageUrl) {
        this.declareGuideImageUrl = declareGuideImageUrl;
        return this;
    }

    public List<OperationLog> getLogs() {
        return logs;
    }

    public JDeclarerDetail setLogs(List<OperationLog> logs) {
        this.logs = logs;
        return this;
    }


    public Boolean getIspass() {
        return ispass;
    }

    public JDeclarerDetail setIspass(Boolean ispass) {
        this.ispass = ispass;
        return this;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public JDeclarerDetail setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public String getOrganizName() {
        return organizName;
    }

    public JDeclarerDetail setOrganizName(String organizName) {
        this.organizName = organizName;
        return this;
    }

    public String getProduct() {
        return product;
    }

    public JDeclarerDetail setProduct(String product) {
        this.product = product;
        return this;
    }

    public Long getId() {
        return id;
    }

    public JDeclarerDetail setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public JDeclarerDetail setName(String name) {
        this.name = name;
        return this;
    }

    public String getCellphone() {
        return cellphone;
    }

    public JDeclarerDetail setCellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public String getPortrait() {
        return portrait;
    }

    public JDeclarerDetail setPortrait(String portrait) {
        this.portrait = portrait;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public JDeclarerDetail setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public JDeclarerDetail setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
        return this;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public JDeclarerDetail setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
        return this;
    }

    public String getSurveyImageUrl() {
        return surveyImageUrl;
    }

    public JDeclarerDetail setSurveyImageUrl(String surveyImageUrl) {
        this.surveyImageUrl = surveyImageUrl;
        return this;
    }

    public String getLetterImageUrl() {
        return letterImageUrl;
    }

    public JDeclarerDetail setLetterImageUrl(String letterImageUrl) {
        this.letterImageUrl = letterImageUrl;
        return this;
    }

    public String getState() {
        return state;
    }

    public JDeclarerDetail setState(String state) {
        this.state = state;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public JDeclarerDetail setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Long getScore() {
        return score;
    }

    public JDeclarerDetail setScore(Long score) {
        this.score = score;
        return this;
    }

    public Long getFkOrganizId() {
        return fkOrganizId;
    }

    public JDeclarerDetail setFkOrganizId(Long fkOrganizId) {
        this.fkOrganizId = fkOrganizId;
        return this;
    }

    public String getCodeState() {
        return codeState;
    }

    public JDeclarerDetail setCodeState(String codeState) {
        this.codeState = codeState;
        return this;
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public JDeclarerDetail setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JDeclarerDetail setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getArea() {
        return area;
    }

    public JDeclarerDetail setArea(String area) {
        this.area = area;
        return this;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public JDeclarerDetail setInvoice(Invoice invoice) {
        this.invoice = invoice;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public JDeclarerDetail setOrder(Order order) {
        this.order = order;
        return this;
    }
}
