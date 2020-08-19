package com.example.basics.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 面试题
 * 实现一个容器，提供两个方法add、size 写两个线程
 * 线程1，添加10个元素到容器中
 * 线程2，实时监控元素个数，当个数到5个时，线程2给出提示并结束
 */

public class ThreadDemo3 {


    volatile List list = new ArrayList<>();

    public void add(Object object) {
        list.add(object);
    }

    public int size() {
        return list.size();
    }


    public static void main(String[] args) {
        ThreadDemo3 threadDemo2 = new ThreadDemo3();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 启动");
                if (threadDemo2.size() == 5) {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
            }
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 启动");
                for (int i = 0; i < 10; i++) {
                    Object object = new Object();
                    threadDemo2.add(object);
                    if (threadDemo2.size() == 5) {
                        try {
                            //通知t2执行
                            countDownLatch.countDown();
                            //阻塞当前线程
                            countDownLatch.await();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t1 结束");
            }
        }, "t1").start();
    }

}
