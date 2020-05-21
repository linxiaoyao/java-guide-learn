package com.learn.javareflect.handler;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: xiazewei
 * @Date: 2020-05-18 18:56
 */
public class AnimalHandler implements InvocationHandler {

    private Object animal;

    public AnimalHandler(Object name) {
        this.animal = name;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //　　在代理真实对象前我们可以添加一些自己的操作
        System.out.println("before ===");

        System.out.println("Method:" + method);

        //    当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(animal, args);

        //　　在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("after ===");

        return null;

    }
}
