package com.linchuan.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloAop {

    @Pointcut("@annotation(HelloAnnotation)")
    void helloPoint() {}

    @Before(value = "helloPoint()")
    void beforeSayHello() {
        System.out.println("Before sayHello");
    }
}
