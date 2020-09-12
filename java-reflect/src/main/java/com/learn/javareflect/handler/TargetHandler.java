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
public class TargetHandler implements InvocationHandler {

    private Object target;

    private List<Interceptor> interceptors;

    public TargetHandler(Object name, List<Interceptor> interceptors) {
        this.target = name;
        this.interceptors = interceptors;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //　　在代理真实对象前我们可以添加一些自己的操作
        interceptors.forEach( x -> x.intercept());

        //    当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(target, args);

        //　　在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("after ===");

        return null;

    }

    public static Object wrap(Object target, List<Interceptor> interceptorList) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader()
                , target.getClass().getInterfaces(), new TargetHandler(target, interceptorList));
    }
}
