package com.cpsdb.declareserv.utils;

import com.cpsdb.thirdapi.utils.ModeType;

public class SubsidyWithdrawUtils {
    public enum DetailState {
        unwithdrawed("待提取"),
        withdrawing("提取中"),
        withdrawed("已提取");

        private String cn;

        DetailState(String cn) {
            this.cn = cn;
        }
    }

    public enum DetailType {
        购买二维码,
        申报官注册,
        企业入库,
        推荐补贴;
    }

    public enum Event {
        create("创建"),
        confirm("通过"),
        delay("延后"),
        reject("不通过");

        private String cn;

        Event(String cn) {
            this.cn = cn;
        }

        public String cn() {
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

    public enum State {
        pending("待审核", null),
        rejected("未通过", ModeType.SUBSiDY_UNPASS),
        delayed("延后处理", ModeType.SUBSiDY_DELAYED),
        passed("通过审核", ModeType.SUBSiDY_PASS);

        private String cn;
        private ModeType modeType;

        State(String cn, ModeType modeType) {
            this.cn = cn;
            this.modeType = modeType;
        }

        public String get() {
            return cn;
        }

        public static SubsidyWithdrawUtils.State get(String state) {
            try {
                return valueOf(state);
            } catch (Exception e) {
                return null;
            }
        }

        public static String getCn(String state) {

            SubsidyWithdrawUtils.State s = SubsidyWithdrawUtils.State.get(state);
            if (s != null) {
                return s.cn;
            }
            return "未知状态";
        }

        public ModeType getModeType() {
            return modeType;
        }
    }

    //消费类型
    public enum SubsidyType {
        ruku("企业入库"),
        code("购买二维码"),
        recommend("推荐补贴");

        private String cn;

        SubsidyType(String cn) {
            this.cn = cn;
        }

        public String getCn() {
            return cn;
        }
    }
}
