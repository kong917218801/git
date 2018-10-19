package com.cpsdb.declareserv.utils;

public class DeclareOrganizUtils {

    public enum OrganizEvent {
        create("创建"),
        submit("提交"),
        confirm("审核"),
        reject("审核不通过"),

        delete("删除");

        private String cn;

        OrganizEvent(String cn) {
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

    public enum UserState {
        /**
         * 可用
         */
        user(1),
        /**
         * 不可用
         */
        unUser(2);

        private int value;

        UserState(int value) {
            this.value = value;
        }

        public static UserState getByValue(int value) {
            for (UserState t : values()) {
                if (t.value == value) {
                    return t;
                }
            }
            return null;
        }

        public int getValue() {
            return value;
        }

        public UserState setValue(int value) {
            this.value = value;
            return this;
        }
    }

    public enum OrganizState {
        baseWaitSubmit("基本信息待填写"),
        baseWaitPending("基本信息待初审"),
        baseWaitAudit("基本信息待审核"),
        baseUnPass("基本信息未通过"),

        registWaitSubmit("登记信息待填写"),
        registWaitPending("登记信息待初审"),
        registWaitAudit("登记信息待审核"),
        registUnPass("登记信息未通过"),

        delete("已删除"),
        passed("通过审核");

        private String state;

        OrganizState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public OrganizState setState(String state) {
            this.state = state;
            return this;
        }

        public static OrganizState get(String state) {
            try {
                return valueOf(state);
            } catch (Exception e) {
                return null;
            }
        }

        public static String getCn(String state) {
            OrganizState s = OrganizState.get(state);
            if (s != null) {
                return s.state;
            }
            return "未知状态";
        }
    }


    public enum AdressType {
        /**
         * 常驻地址
         */
        liveAdress(1),
        /**
         * 申请区域
         */
        declareAdress(2);

        private int value;

        AdressType(int value) {
            this.value = value;
        }

        public static AdressType getByValue(int value) {
            for (AdressType t : values()) {
                if (t.value == value) {
                    return t;
                }
            }
            return null;
        }

        public int getValue() {
            return value;
        }

        public AdressType setValue(int value) {
            this.value = value;
            return this;
        }
    }

}
