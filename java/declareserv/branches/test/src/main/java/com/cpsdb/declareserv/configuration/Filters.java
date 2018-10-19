package com.cpsdb.declareserv.configuration;

import com.cpsdb.base.aop.CrossAccessFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HttpPutFormContentFilter;

/**
 * 过滤器配置
 *
 * @author 李银 on 2018年3月29日 16:29:34
 */
@Configuration
public class Filters {

    /**
     * 设置允许可以跨域访问
     */
    @Bean
    public CrossAccessFilter crossAccessFilter() {
        return new CrossAccessFilter();
    }

//    @Bean
//    public CharacterEncodingFilter characterEncodingFilter() {
//        return new CharacterEncodingFilter("UTF-8", true);
//    }

    @Bean
    public HttpPutFormContentFilter httpPutFormContentFilter() {
        return new HttpPutFormContentFilter();
    }

}
