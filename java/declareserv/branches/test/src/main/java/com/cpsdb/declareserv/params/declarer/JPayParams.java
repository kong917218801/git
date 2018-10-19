package com.cpsdb.declareserv.params.declarer;

public class JPayParams {

    /**
     * Description 是否需要发票
     */
    private Boolean needInvoice = false;
    /**
     * Description 支付方式
     */
    private String payMethod;
    /**
     * Description 返回页面
     */
    private String returnPage;
    /**
     * Description 客户端IP
     */
    private String clientIP;

    public String getClientIP() {
        return clientIP;
    }

    public JPayParams setClientIP(String clientIP) {
        this.clientIP = clientIP;
        return this;
    }

    public Boolean getNeedInvoice() {
        return needInvoice;
    }

    public JPayParams setNeedInvoice(Boolean needInvoice) {
        this.needInvoice = needInvoice;
        return this;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public JPayParams setPayMethod(String payMethod) {
        this.payMethod = payMethod;
        return this;
    }

    public String getReturnPage() {
        return returnPage;
    }

    public JPayParams setReturnPage(String returnPage) {
        this.returnPage = returnPage;
        return this;
    }
}
