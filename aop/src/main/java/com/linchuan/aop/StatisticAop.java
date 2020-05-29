package com.linchuan.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class StatisticAop {

    private Integer counter = 0;

    @Pointcut(value = "@annotation(Statistic)")
    void statis(){}

    @After(value = "statis()")
    void afterRequestStasis() {
        System.out.println(++counter);
    }
}
