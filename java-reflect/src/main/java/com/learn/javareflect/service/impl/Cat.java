package com.learn.javareflect.service.impl;

import com.learn.javareflect.service.Animal;

/**
 * @Author: xiazewei
 * @Date: 2020-05-18 18:54
 */
public class Cat implements Animal {

    @Override
    public void play() {

    }

    @Override
    public void hello(String str) {
        System.out.println("hello kitty");
    }
}
