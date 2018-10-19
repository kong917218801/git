package com.cpsdb.declareserv.configuration;

import com.cpsdb.validator.ValidatorAdvisor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Simple to Introduction
 *
 * @ProjectName: website
 * @Package: com.cpsdb.website.configuration
 * @ClassName:
 * @Description: 验证添加数据拦截器
 * @Author: 荣少
 */
@Aspect
@Component
public class Aop extends ValidatorAdvisor {
    @Pointcut(value = "@annotation(com.cpsdb.validator.Validate)")
    public void webValidator() {
    }

    @Around(value = "webValidator()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        validateHandler(joinPoint);
    }
}
