package com.cpsdb.declareserv;

import com.cpsdb.base.spring.CustomMappingJacksonConverter;
import com.cpsdb.base.spring.RequestMappingHandlerAdapterModify;
import com.cpsdb.base.spring.SpringContextHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;

import javax.servlet.MultipartConfigElement;
import java.nio.charset.Charset;

/**
 * 程序主入口
 */

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.cpsdb.declareserv")
@ImportResource(locations = {"classpath:config/applicationContext.xml"})
public class Application {

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    @Bean
    public RequestMappingHandlerAdapterModify requestMappingHandlerAdapterModify() {
        return new RequestMappingHandlerAdapterModify();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 文件上传配置
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("3072000KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("3072000KB");
        return factory.createMultipartConfig();
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper() {
        CustomMappingJacksonConverter jacksonConverter = new CustomMappingJacksonConverter();
        jacksonConverter.setSupportedMediaTypes(Lists.newArrayList(MediaType.APPLICATION_JSON_UTF8));
        return jacksonConverter.getObjectMapper();
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.defaultCharset());
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//            String[] beanNames = ctx.getBeanDefinitionNames();
//
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName/* + ":" + ctx.getBean(beanName).getClass()*/);
//            }
//        };
//    }

}