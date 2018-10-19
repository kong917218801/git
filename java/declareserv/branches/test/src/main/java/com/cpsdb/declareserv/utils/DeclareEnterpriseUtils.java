package com.cpsdb.declareserv.utils;

/**
 * Created by 李银 2017年12月3日 08:43:06
 */
public class DeclareEnterpriseUtils {


    public enum DeclareEnterpriseEvent {

        create("创建"),
        confirm("审核通过"),
        reject("审核不通过"),
        delete("删除"),
        submit("提交"),
        waitAudit("待审核"),
        pass("已通过");

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
    public enum EnterpriseState {
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

        EnterpriseState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public EnterpriseState setState(String state) {
            this.state = state;
            return this;
        }

        public static String getState(String state) {
            EnterpriseState s = EnterpriseState.get(state);
            if (s != null) {
                return s.state;
            }
            return "未知状态";
        }

        public static EnterpriseState get(String state) {
            try {
                return valueOf(state);
            } catch (Exception e) {
                return null;
            }
        }

    }
}
