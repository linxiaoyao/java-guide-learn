package com.learn.javathread;

import java.text.SimpleDateFormat;
import java.util.Random;

public class ThreadLocalExample2 implements Runnable{

     // SimpleDateFormat 不是线程安全的，所以每个线程都要有自己独立的副本
//    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmm");

    public static SimpleDateFormat getSdf() {
        return sdf;
    }

    public static void setSdf(SimpleDateFormat sdf) {
        ThreadLocalExample2.sdf = sdf;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalExample2 obj = new ThreadLocalExample2();
        for(int i=0 ; i<10; i++){
            Thread t = new Thread(obj, ""+i);
            Thread.sleep(new Random().nextInt(1000));
            t.start();
        }
    }

    @Override
    public void run() {
        System.out.println("Thread Name= "+Thread.currentThread().getName()+" default Formatter = "+sdf.toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //formatter pattern is changed here by thread, but it won't reflect to other threads
        setSdf(new SimpleDateFormat("yyyy-MM-dd"));

        System.out.println("Thread Name= "+Thread.currentThread().getName()+" formatter = "+sdf.toPattern());
    }

}