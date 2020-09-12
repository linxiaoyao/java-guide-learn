package com.learn.javareflect.handler;


import com.learn.javareflect.interceptor.Interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @Author: xiazewei
 * @Date: 2020-05-18 18:56
 */
public class TargetHandler2 implements InvocationHandler {

    private Object target;

    private Interceptor interceptor;

    public TargetHandler2(Object name, Interceptor interceptor) {
        this.target = name;
        this.interceptor = interceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Invocation invocation = new Invocation(target, method, args);
        interceptor.intercept(invocation);

        return null;

    }

    public static Object wrap(Object target, Interceptor interceptor) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader()
                , target.getClass().getInterfaces(), new TargetHandler2(target, interceptor));
    }

}
