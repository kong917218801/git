package com.cpsdb.declareserv.utils;

import com.cpsdb.base.common.ProjectUtils;

/**
 * Created by 李银 2017年12月3日 08:43:06
 */
public class DeclarerUtils {


    public static String DECLARE_CONSUMER_GROUP = "DECLARESERV_CONSUMER_GROUP";

    public enum DeclarerEvent {

        create("创建"),
        pay("待支付"),
        waitAuthen("认证信息待填写"),
        confirm("审核通过"),
        reject("审核不通过"),
        delete("删除"),
        submit("提交");

        private String cn;

        DeclarerEvent(String cn) {
            this.cn = cn;
        }

        public String CN() {
            return cn;
        }

        public static String getCn(String event) {
            try {
                return valueOf(event).cn;
            } catch (Exception e) {
                return "未知操作";
            }
        }
    }

    public enum DeclareEnterpriseEvent {

        create("创建"),
        confirm("审核通过"),
        reject("审核不通过"),
        delete("删除"),
        submit("提交"),
        waitAudit("待审核"),
        pass("已通过"),

        wait("待支付"),
        pending("待初审"),
        collectting("待认证官上门采集"),
        confirmFailed("初审未通过"),
        reject2("认证官采集未通过"),
        pending2("待复审"),
        confirm2Failed("复审未通过"),
        passed("通过审核");

        private String cn;

        DeclareEnterpriseEvent(String cn) {
            this.cn = cn;
        }

        public String CN() {
            return cn;
        }

        public static String getCn(String event) {
            try {
                return valueOf(event).cn;
            } catch (Exception e) {
                return "未知操作";
            }
        }
    }

    /**
     * 申报企业状态
     */
    public enum EnterpirseState {
        waitPending("申报材料待初审"),
        waitUnPending("申报材料初审未通过"),
        waitPended("申报材料初审通过"),
        waitAudit("申报材料待复审"),
        unPass("申报材料复审未通过"),
        pass("申报材料复审已通过"),
        wait("系统服务费待支付"),
        pending("入库材料待初审"),
        collectting("待认证官上门采集"),
        confirmFailed("入库材料初审未通过"),
        reject2("认证官采集未完成"),
        pending2("认证材料待复审"),
        confirm2Failed("认证材料复审未通过"),
        passed("认证材料复审已通过");

        private String state;

        EnterpirseState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public EnterpirseState setState(String state) {
            this.state = state;
            return this;
        }

        public static String getState(String state) {
            EnterpirseState s = EnterpirseState.get(state);
            if (s != null) {
                return s.state;
            }
            return "未知状态";
        }

        public static EnterpirseState get(String state) {
            try {
                return valueOf(state);
            } catch (Exception e) {
                return null;
            }
        }

    }

    /**
     * 申报官状态
     */
    public enum DeclarerState {
        waitPay("待支付"),
        waitAuthen("认证信息待填写"),
        financeAudit("财务审核"),
        waitPending("待初审"),
        waitAudit("待审核"),
        unpass("未通过"),
        passed("已通过"),
        delete("删除中"),
        deleted("已删除");

        private String state;

        DeclarerState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public DeclarerState setState(String state) {
            this.state = state;
            return this;
        }

        public static DeclarerState get(String state) {
            try {
                return valueOf(state);
            } catch (Exception e) {
                return null;
            }
        }
        public static String getState(String state) {
            DeclarerState s = DeclarerState.get(state);
            if (s != null) {
                return s.state;
            }
            return "未知状态";
        }

    }

    //发票类型
    public enum InvoiceType {
        person("个人"),
        enterprise("企业");

        private String cn;

        InvoiceType(String cn) {
            this.cn = cn;
        }

        public String CN() {
            return cn;
        }

        public static String getCn(String event) {
            try {
                return valueOf(event).cn;
            } catch (Exception e) {
                return "未知状态";
            }
        }
    }


    //注册方式
    public enum RegistMethod {
        publics("普通注册"),
        other("其他注册");

        private String cn;

        RegistMethod(String cn) {
            this.cn = cn;
        }

        public String CN() {
            return cn;
        }

        public static String getCn(String event) {
            try {
                return valueOf(event).cn;
            } catch (Exception e) {
                return "未知状态";
            }
        }
    }

    public enum PayState {
        wait("待支付"),
        success("支付成功"),
        failed("支付失败");

        private String state;

        PayState(String state) {
            this.state = state;
        }

        public String get() {
            return state;
        }

        public static PayState get(String state) {
            try {
                return valueOf(state);
            } catch (Exception e) {
                return null;
            }
        }

        public static String getState(String state) {
            PayState s = PayState.get(state);
            if (s != null) {
                return s.state;
            }
            return "未知状态";
        }

    }

    /**
     * 认证官二维码生成
     * @param id
     * @return
     */
    public static String authofficerQrcode(Long id) {
        return ProjectUtils.Project.declare.getDomain() + "e/" + id;
    }

    /**
     * 申报官生成
     * @param id
     * @return
     */
    public static String declarerQrcode(Long id) {

        return ProjectUtils.Project.declare.getDomain() + "d/" + id;
    }

}
