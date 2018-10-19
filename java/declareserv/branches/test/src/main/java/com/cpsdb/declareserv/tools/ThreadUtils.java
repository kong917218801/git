package com.cpsdb.declareserv.tools;

import com.cpsdb.base.spring.SpringContextHolder;
import com.cpsdb.declareserv.utils.DeclareConfigUtils;
import com.cpsdb.thirdapi.api.JuHeApi;
import com.cpsdb.thirdapi.utils.ModeType;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class ThreadUtils {
    private static final Logger logger = LoggerFactory.getLogger(ThreadUtils.class);

    private static class Holder {
        private static JuHeApi juHeApi = SpringContextHolder.getBean(JuHeApi.class);
    }

    public static void runThread(String cellphone, ModeType type, String mark) {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("file-split-worker-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(() -> {
            try {
                //是否发送短信
                Integer issend = DeclareConfigUtils.getInt("sms.send", 0);
                if (issend == 0) {
                    Holder.juHeApi.sendSms(cellphone, type, "", mark);
                }
            } catch (Exception e) {
                logger.error("发送短信失败: ", e);
            }
        });
        singleThreadPool.shutdown();
    }
    public static void runThreadProv(String cellphone, ModeType type, String name) {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("file-split-worker-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(() -> {
            try {
                //是否发送短信
                Integer issend = DeclareConfigUtils.getInt("sms.send", 0);
                if (issend == 0) {
                    Holder.juHeApi.sendSms(cellphone, type, name);
                }
            } catch (Exception e) {
                logger.error("发送短信失败: ", e);
            }
        });
        singleThreadPool.shutdown();
    }

    public static void runThreadProv(String cellphone, ModeType type, String name,String reason) {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("file-split-worker-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(() -> {
            try {
                //是否发送短信
                Integer issend = DeclareConfigUtils.getInt("sms.send", 0);
                if (issend == 0) {
                    Holder.juHeApi.sendSms(cellphone, type, name,reason);
                }
            } catch (Exception e) {
                logger.error("发送短信失败: ", e);
            }
        });
        singleThreadPool.shutdown();
    }
    public static void runThread(String cellphone, ModeType type, String name, String content, String amount, String reason) {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("file-split-worker-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(() -> {
            try {
                //是否发送短信
                int issend = DeclareConfigUtils.getInt("sms.send", 0);
                if (issend == 0) {
                    Holder.juHeApi.sendSms(cellphone, type, name, content, amount, reason);
                }

            } catch (Exception e) {
                logger.error("发送短信失败: ", e);
            }
        });
        singleThreadPool.shutdown();
    }

}
