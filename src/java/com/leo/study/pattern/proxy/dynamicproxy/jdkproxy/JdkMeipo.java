package com.leo.study.pattern.proxy.dynamicproxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkMeipo implements InvocationHandler {

    private Object target;
    public Object getInstance(Object obj) throws Exception {
        this.target = obj;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = method.invoke(this.target, args);
        return obj;
    }

    private void before(){
        System.out.println("befor : 增强");
    }

    private void after(){
        System.out.println("after : 增强");
    }
}
