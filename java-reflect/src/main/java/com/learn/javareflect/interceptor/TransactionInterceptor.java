package com.learn.javareflect.interceptor;


/**
 * @Author: xiazewei
 * @Date: 2020-08-10 17:59
 */
public class TransactionInterceptor implements Interceptor {

    @Override
    public void interceptor() {
        System.out.println("LogInterceptor");
    }
}
