package com.linchuan.proxy;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoProxyTest {
    @Test
    public void testUserDaoProxy() {
        IUserDao target = new UserDao();
        // target.save();
        UserDaoProxy proxy = new UserDaoProxy(target);
        proxy.save();
    }

}