package com.linchuan.proxy;

import org.junit.Test;

/**
 * 动态代理
 */
public class SdkUserProxyFactoryTest {
    @Test
    public void genProxyInstance() {
        IUserDao target = new UserDao();
        System.out.println(target.getClass());
        IUserDao proxy = (IUserDao) new SdkUserProxyFactory(target).genProxyInstance();
        System.out.println(proxy.getClass());
        proxy.save();
    }
}