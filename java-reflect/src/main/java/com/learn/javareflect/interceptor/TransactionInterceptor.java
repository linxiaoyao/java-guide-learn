package com.learn.javareflect.interceptor;


import com.learn.javareflect.handler.Invocation;

/**
 * @Author: xiazewei
 * @Date: 2020-08-10 17:59
 */
public class TransactionInterceptor implements Interceptor {

    @Override
    public void intercept() {
        System.out.println("TransactionInterceptor");
    }

    @Override
    public void intercept(Invocation invocation) throws Exception {
        System.out.println("TransactionInterceptor begin");
        invocation.process();
        System.out.println("TransactionInterceptor after");
    }
}
