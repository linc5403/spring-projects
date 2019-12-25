package com.banyuan.ioc.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BasicApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BasicApplication.class, args);
        BinarySearchImpl searchImpl1 = context.getBean(BinarySearchImpl.class);
        BinarySearchImpl searchImpl2 = context.getBean(BinarySearchImpl.class);
        searchImpl1.search(new int [] {1, 2, 3}, 3);
        System.out.println(searchImpl1);
        System.out.println(searchImpl2);
    }

}
