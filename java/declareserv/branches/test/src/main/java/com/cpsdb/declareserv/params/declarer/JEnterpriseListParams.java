package com.cpsdb.declareserv.params.declarer;

public class JEnterpriseListParams {

    /**
     * @Description 服务处ID
     */
    private Long organizId;
    /**
     * @Description 省级服务中心ID
     */
    private Long provenceCenterId;
    /**
     * @Description 申报官ID
     */
    private Long declarerId;
    /**
     * @Description 姓名
     */
    private String name;
    /**
     * @Description 负责人
     */
    private String charger;
    /**
     * @Description 状态
     */
    private String state;

    public Long getProvenceCenterId() {
        return provenceCenterId;
    }

    public JEnterpriseListParams setProvenceCenterId(Long provenceCenterId) {
        this.provenceCenterId = provenceCenterId;
        return this;
    }

    public Long getDeclarerId() {
        return declarerId;
    }

    public JEnterpriseListParams setDeclarerId(Long declarerId) {
        this.declarerId = declarerId;
        return this;
    }



    public Long getOrganizId() {
        return organizId;
    }

    public JEnterpriseListParams setOrganizId(Long organizId) {
        this.organizId = organizId;
        return this;
    }

    public String getName() {
        return name;
    }

    public JEnterpriseListParams setName(String name) {
        this.name = name;
        return this;
    }

    public String getCharger() {
        return charger;
    }

    public JEnterpriseListParams setCharger(String charger) {
        this.charger = charger;
        return this;
    }

    public String getState() {
        return state;
    }

    public JEnterpriseListParams setState(String state) {
        this.state = state;
        return this;
    }
}
