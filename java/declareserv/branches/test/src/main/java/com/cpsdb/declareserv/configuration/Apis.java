package com.cpsdb.declareserv.configuration;

import com.cpsdb.baseservapi.api.AreaApi;
import com.cpsdb.baseservapi.api.PlatformUserApi;
import com.cpsdb.declareapi.api.DeclareApi;
import com.cpsdb.examinationapi.api.DeclareCheckApi;
import com.cpsdb.gpsapi.api.BaiduGpsApi;
import com.cpsdb.payapi.api.PayApi;
import com.cpsdb.thirdapi.api.BaiduApi;
import com.cpsdb.thirdapi.api.JuHeApi;
import com.cpsdb.zimgapi.api.ZimgClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * api配置配置
 *
 * @author 李银 on 2018年9月20日 20:57:31
 */
@Configuration
public class Apis {

    /**
     * 发送短信api
     */
    @Bean
    public JuHeApi juHeApi() {
        return new JuHeApi();
    }

    /**
     * 支付api
     */
    @Bean
    public PayApi payApi() {
        return new PayApi();
    }

    /**
     * 获取区域api
     */
    @Bean
    public AreaApi areaApi() {
        return new AreaApi();
    }

    /**
     * DeclareCheckApi 考试使用的
     */
    @Bean
    public DeclareCheckApi declareCheckApi() {
        return new DeclareCheckApi();
    }

    /**
     * 上传图片
     */
    @Bean
    public ZimgClient zimgClient() {
        return new ZimgClient();
    }

    /**
     * 总后台权限相关
     */
    @Bean
    public PlatformUserApi platformUserApi() {
        return new PlatformUserApi();
    }

    /**
     * 申报系统项目提供的
     */
    @Bean
    public DeclareApi declareApi() {
        return new DeclareApi();
    }

    /**
     * 百度提供的gps api
     */
    @Bean
    public BaiduGpsApi baiduGpsApi() {
        return new BaiduGpsApi();
    }

    /**
     * 申报系统项目提供的
     */
    @Bean
    public BaiduApi baiduApi() {
        return new BaiduApi();
    }


//    <bean id="jiSuApi" class="com.cpsdb.thirdapi.api.JiSuApi"/>

//    <bean id="enterpriseApi" class="com.cpsdb.enterpriseapi.api.EnterpriseApi"/>


}
