package com.linchuan.aop;

import org.springframework.stereotype.Component;

@Component
public class Person {
    @HelloAnnotation
    void sayHello() {
        System.out.println("Hello");
    }
}
