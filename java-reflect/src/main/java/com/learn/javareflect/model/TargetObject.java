package com.learn.javareflect.model;

public class TargetObject {

    private String name;
    private int age;

    public TargetObject() {
        System.out.println("------ 无参构造方法 --------");
    }

    private TargetObject(int value) {
        this.age = value;
        System.out.println("------ 私有、一个参数的构造方法 --------");
    }

    public TargetObject(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("------ 两个参数的构造方法 --------");
    }

    public void publicMethod() {
        System.out.println("");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TargetObject{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}