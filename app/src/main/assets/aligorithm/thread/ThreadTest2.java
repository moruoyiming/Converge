package com.algorithm.demo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 面试题
 * 实现一个容器，提供两个方法add、size 写两个线程
 * 线程1，添加10个元素到容器中
 * 线程2，实时监控元素个数，当个数到5个时，线程2给出提示并结束
 *
 */

public class ThreadTest2 {


    volatile List list = new ArrayList<>();

    public void add(Object object) {
        list.add(object);
    }

    public int size() {
        return list.size();
    }


    public static void main(String[] args) {
        ThreadTest2 threadDemo2=new ThreadTest2();
        Object lock = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (lock){
                    System.out.println("t2 启动");
                    if (threadDemo2.size()==5){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("t2 结束");
                }
                //通知t1继续执行
                lock.notify();
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

                synchronized (lock){
                    System.out.println("t1 启动");
                    for(int i=0;i<10 ;i++){
                        Object object=new Object();
                        threadDemo2.add(object);
                        if (threadDemo2.size()==5){
                            try {
                                //通知t2执行
                                lock.notify();
                                //阻塞当前线程
                                lock.wait();
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
            }
        }, "t1").start();
    }

}
