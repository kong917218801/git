package com.cpsdb.declareserv.configuration;

import com.cpsdb.base.aop.CheckTokenInterceptor;
import com.cpsdb.baseservapi.aop.PlatformAuthInterceptor;
import com.cpsdb.declareapi.aop.DeclareAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 *
 * @author 李银 on 2018年3月29日 16:29:58
 */
@Configuration
public class Interceptors implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加平台认证拦截器
        registry.addInterceptor(new DeclareAuthInterceptor()).addPathPatterns("/organiz/**", "/declarerapp/**");

        registry.addInterceptor(new PlatformAuthInterceptor()).addPathPatterns("/platform/**");

        registry.addInterceptor(new CheckTokenInterceptor()).addPathPatterns("/privates/**");
        //添加更多的拦截器
        //...
    }
}
