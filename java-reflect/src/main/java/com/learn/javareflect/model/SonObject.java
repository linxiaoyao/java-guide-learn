package com.learn.javareflect.model;

/**
 * @Author: xiazewei
 * @Date: 2020-05-19 15:44
 */
public class SonObject extends TargetObject {

    private String name;
    private int age;


    public SonObject(String name, int age) {
        super();
        this.name = name;
        this.age = age;
        System.out.println("子类===》"+ this.toString()+",父类===》"+super.toString());
    }

    public SonObject(String name, int age, String name1, int age1) {
        super(name, age);
        this.name = name1;
        this.age = age1;
    }


    @Override
    public String toString() {
        return "SonObject{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
