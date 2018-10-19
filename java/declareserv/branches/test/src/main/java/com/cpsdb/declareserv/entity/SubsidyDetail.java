package com.cpsdb.declareserv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.math.BigDecimal;

/**
 * @author 李银
 * @ClassName SubsidyDetail
 * @Description
 * @date 2018-09-20 17:52:30
 */
public class SubsidyDetail {

    /**
     * @Description 主键
     */
    private Long id;

    /**
     * @Description 对象版本，乐观锁需要可使用
     */
    @JsonIgnore
    private Integer version;

    /**
     * @Description 拥有者id
     */
    private Long userId;
    /**
     * @Description 补贴名称
     */
    private String name;
    /**
     * @Description 补贴类型
     */
    private String type;
    /**
     * @Description 补贴带来方： 比如企业入库则是企业id，如果是推荐则是被推荐方的userId
     */
    private Long targetId;
    /**
     * @Description 数量
     */
    private Integer quantity;
    /**
     * @Description 总金额，小数点后两位
     */
    private BigDecimal amount;
    /**
     * @Description 提取总金额的百分比
     */
    private BigDecimal percent;
    /**
     * @Description unwithdrawed-待提取；withdrawing-提取中；withdrawed-已提取
     */
    private String state;
    /**
     * @Description
     */
    private Date createTime;
    /**
     * @Description
     */
    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public SubsidyDetail setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public SubsidyDetail setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public SubsidyDetail setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public SubsidyDetail setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public SubsidyDetail setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public SubsidyDetail setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public SubsidyDetail setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public SubsidyDetail setPercent(BigDecimal percent) {
        this.percent = percent;
        return this;
    }

    public String getState() {
        return state;
    }

    public SubsidyDetail setState(String state) {
        this.state = state;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public SubsidyDetail setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public SubsidyDetail setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public Long getTargetId() {
        return targetId;
    }

    public SubsidyDetail setTargetId(Long targetId) {
        this.targetId = targetId;
        return this;
    }
}
