package com.cpsdb.declareserv.dto;

import java.math.BigDecimal;

/**
 * @author 孔青
 * @ClassName JAppLogin
 * @Description 申报App登录返回数据
 * @date 2018年1月14日 12:05:00
 */
public class JAppLogin {

    /**
     * @Description 姓名
     */
    private String name;

    /**
     * @Description 寸照
     */
    private String portrait;

    /**
     * @Description token
     */
    private String token;

    /**
     * @Description 申报官状态
     */
    private String state;

    /**
     * @Description 申报机构
     */
    private String organiz;

    /**
     * @Description 是否需要考试
     */
    private boolean examination;

    /**
     * @Description 编号
     */
    private String sn;

    /**
     * @Description 二维码
     */
    private String code;
    /**
     * @Description 账号类型：1-申报服务处; 2-申报官; 3-省级服务中心
     */
    private Integer type;

    /**
     * @Description 推荐号
     */
    private String recommendId;

    /**
     * @Description 推荐人
     */
    private String recommendName;

    /**
     * @Description 支付金额
     */
    private BigDecimal amount;

    /**
     * @Description 审核未通过的原因
     */
    private String reason;
    /**
     * @Description 考试分数
     */
    private String score;

    public String getScore() {
        return score;
    }

    public JAppLogin setScore(String score) {
        this.score = score;
        return this;
    }

    public String getRecommendName() {
        return recommendName;
    }

    public JAppLogin setRecommendName(String recommendName) {
        this.recommendName = recommendName;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public JAppLogin setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public JAppLogin setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public String getCode() {
        return code;
    }

    public JAppLogin setCode(String code) {
        this.code = code;
        return this;
    }

    public boolean isExamination() {
        return examination;
    }

    public JAppLogin setExamination(boolean examination) {
        this.examination = examination;
        return this;
    }

    public String getSn() {
        return sn;
    }

    public JAppLogin setSn(String sn) {
        this.sn = sn;
        return this;
    }

    public String getOrganiz() {
        return organiz;
    }

    public JAppLogin setOrganiz(String organiz) {
        this.organiz = organiz;
        return this;
    }

    public String getRecommendId() {
        return recommendId;
    }

    public JAppLogin setRecommendId(String recommendId) {
        this.recommendId = recommendId;
        return this;
    }

    public String getName() {
        return name;
    }

    public JAppLogin setName(String name) {
        this.name = name;
        return this;
    }

    public String getPortrait() {
        return portrait;
    }

    public JAppLogin setPortrait(String portrait) {
        this.portrait = portrait;
        return this;
    }

    public String getToken() {
        return token;
    }

    public JAppLogin setToken(String token) {
        this.token = token;
        return this;
    }

    public String getState() {
        return state;
    }

    public JAppLogin setState(String state) {
        this.state = state;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public JAppLogin setType(Integer type) {
        this.type = type;
        return this;
    }
}
