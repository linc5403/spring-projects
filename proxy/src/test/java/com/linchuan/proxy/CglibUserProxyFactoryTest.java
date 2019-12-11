package com.linchuan.proxy;

import org.junit.Test;

import static org.junit.Assert.*;

public class CglibUserProxyFactoryTest {

    @Test
    public void getProxyInstance() {
        UserDao target = new UserDao();
        UserDao proxy = (UserDao) new CglibUserProxyFactory(target).getProxyInstance();
        proxy.save();
    }
}