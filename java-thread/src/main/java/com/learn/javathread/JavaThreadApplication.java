package com.learn.javathread;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

@SpringBootApplication
public class JavaThreadApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JavaThreadApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 获取 Java 线程管理 MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的 monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程 ID 和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
        // 从上面的输出内容可以看出：一个 Java 程序的运行是 main 线程和多个其他线程同时运行。
    }
}
