package com.linchuan.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SdkUserProxyFactory {
    private IUserDao target;

    public SdkUserProxyFactory(IUserDao target) {
        this.target = target;
    }

    public Object genProxyInstance() {
        return Proxy.newProxyInstance(
            target.getClass().getClassLoader(), //类加载器
            target.getClass().getInterfaces(),  //类接口
            new InvocationHandler() { //InvocationHandler接口,在这里面重新invoke方法
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("开启事务");
                    Object returnValue = method.invoke(target, args); //重要的是这个
                    System.out.println("结束事务");
                    return returnValue;
                }
            }
        );
    }
}
