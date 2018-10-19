package com.cpsdb.declareserv.utils;

/**
 * @Author 老周
 * @Description 功能描述
 * @CreateDate 2018.7.1
 * @Version v1.0
 */
public class ProvenceUtils {

    /**
     * 省级服务中心状态
     */
    public enum ProvenceState {
        financeAudit("财务审核"),
        waitPending("待初审"),
        waitAudit("待审核"),
        unpass("未通过"),
        passed("已通过"),
        deleting("删除中"),
        deleted("已删除");
        private String state;

        ProvenceState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public ProvenceState setState(String state) {
            this.state = state;
            return this;
        }

        public static ProvenceState get(String state) {
            try {
                return valueOf(state);
            } catch (Exception e) {
                return null;
            }
        }

        public static String getState(String state) {
            ProvenceState s = ProvenceState.get(state);
            if (s != null) {
                return s.state;
            }
            return "未知状态";
        }

    }

    public enum ProvEvent {
        create("创建"),
        submit("提交"),
        confirm("审核"),
        reject("审核不通过"),

        delete("删除");

        private String cn;

        ProvEvent(String cn) {
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


}
