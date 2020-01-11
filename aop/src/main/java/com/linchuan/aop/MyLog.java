package com.linchuan.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class MyLog {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.linchuan.aop.Loggable)")
    public void loggableMethods() {};

//  @Before("execution(public * com.linchuan.aop.BasicController.*(..))")
    @Before(value = "loggableMethods()")
    public void logSomeThing(JoinPoint joinPoint) {
        Object[] tos = joinPoint.getArgs();
        logger.info(joinPoint.getSignature().toString());
        if (tos.length > 0)
            logger.info(tos[0].toString());
    }

    @AfterReturning(value = "loggableMethods()", returning = "obj")
    public void logReturnValue(Object obj) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info(request.getRequestURI());
        logger.info(request.getRequestURL().toString());
        logger.info(request.getContextPath());
        logger.info("Return " + obj.toString());
    }
}
