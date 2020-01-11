package com.banyuan.ioc.scope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ScopeApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ScopeApplication.class, args);
        PersonDao personDao1 = context.getBean(PersonDao.class);
        PersonDao personDao2 = context.getBean(PersonDao.class);
        System.out.println(personDao1);
        System.out.println(personDao1.getJdbcConnection());
        System.out.println(personDao2);
        System.out.println(personDao2.getJdbcConnection());
    }
}
