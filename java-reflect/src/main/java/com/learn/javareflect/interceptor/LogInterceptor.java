package com.learn.javareflect.interceptor;


import com.learn.javareflect.handler.Invocation;

/**
 * @Author: xiazewei
 * @Date: 2020-08-10 17:59
 */
public class LogInterceptor implements Interceptor {

    @Override
    public void intercept() {
        System.out.println("LogInterceptor");
    }

    @Override
    public void intercept(Invocation invocation) throws Exception {
        System.out.println("LogInterceptor begin");
        invocation.process();
        System.out.println("LogInterceptor after");
    }
}
