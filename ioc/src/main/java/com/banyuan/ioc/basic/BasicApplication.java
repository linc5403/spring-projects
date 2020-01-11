package com.banyuan.ioc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BasicApplication {
    private static Logger logger = LoggerFactory.getLogger(BasicApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BasicApplication.class, args);
        BinarySearchImpl searchImpl1 = context.getBean(BinarySearchImpl.class);
        BinarySearchImpl searchImpl2 = context.getBean(BinarySearchImpl.class);
        searchImpl1.search(new int [] {1, 2, 3}, 3);
        logger.info("{}", searchImpl1);
        logger.info("{}", searchImpl2);
        logger.error("{}", searchImpl1 == searchImpl2);
    }

}
