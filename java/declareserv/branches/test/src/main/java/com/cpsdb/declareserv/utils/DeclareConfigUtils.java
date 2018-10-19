package com.cpsdb.declareserv.utils;

import com.cpsdb.base.common.CPSStringUtils;
import com.cpsdb.base.common.ProjectUtils;
import com.taobao.diamond.client.impl.DiamondEnv;
import com.taobao.diamond.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Properties;

public class DeclareConfigUtils {
    private static final Logger logger = LoggerFactory.getLogger(ProjectUtils.class);

    private static Properties properties = new Properties();

    static {
        synchronized (properties) {
            try {
                properties.load(new StringReader(
                        DiamondEnv.instance().getConfig("DECLARE_SERV", "CONFIG",
                                Constants.GETCONFIG_LOCAL_SERVER_SNAPSHOT, 20000)));
            } catch (IOException e) {
                logger.error("初始化 DECLARE CONFIG：", e);
            }
        }
    }

    public static String getValue(String key) {
        return properties.getProperty(key);
    }

    public static String getValue(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static BigDecimal getBigDecimal(String key) {
        return CPSStringUtils.parseDecimal(getValue(key));
    }

    public static Integer getInteger(String key) {
        return CPSStringUtils.parseInt(getValue(key));
    }

    public static Long getLong(String key) {
        return CPSStringUtils.parseLong(getValue(key));
    }

    public static int getInt(String key, int defaultValue) {
        return CPSStringUtils.parseInt(getValue(key), defaultValue);
    }
}
