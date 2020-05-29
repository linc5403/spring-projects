package com.linchuan.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloAop {

    @Pointcut("@annotation(HelloAnnotation)")
    void helloPoint() {}

    @Pointcut("@annotation(HelloAnnotation)")
    void hello2Point() {}

    @Before(value = "helloPoint()")
    void beforeSayHello() {
        System.out.println("Before sayHello");
    }

    @After(value = "hello2Point()")
    void afterSayHello() {
        System.out.println("After..........");
    }
}
