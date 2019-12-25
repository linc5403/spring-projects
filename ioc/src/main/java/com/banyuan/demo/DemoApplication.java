package com.banyuan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        BinarySearchImpl searchImpl = context.getBean(BinarySearchImpl.class);
        int index = searchImpl.search(new int [] {1, 2, 3}, 3);
        System.out.println(index);
    }

}
