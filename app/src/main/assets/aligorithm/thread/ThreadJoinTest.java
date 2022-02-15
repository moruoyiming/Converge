package com.algorithm.demo.thread;// 写出这段程序的最后输出结果

public class ThreadJoinTest {
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        // 定义两个锁对象。
        Object lock1 = new Object();
        Object lock2 = new Object();
        
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                // 开始线程主体之前先获取锁对象 'lock1' 。
                synchronized(lock1) {
                    // 打印线程开始执行信息。
                    System.out.println("thread1 start");
                    try {
                        // 休眠一分钟，模拟耗时任务。
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // 获取锁对象 'lock2'，注意此时线程仍然持有锁 'lock1'，
                    // 也就是说线程是在持有锁'lock1'的前提下尝试获取锁对象'lock2'。
                    synchronized(lock2) {
                        System.out.println("thread1 end");
                    }
                    // 线程释放锁对象'lock2'。
                }
                // 线程释放锁对象'lock1'。
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                // 开始线程主体之前先获取锁对象'lock2'。
                synchronized(lock2) {
                    // 打印线程开始执行信息。
                    System.out.println("thread2 start");
                    try {
                        // 休眠一分钟，模拟耗时任务。
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // 在线程持有锁对象'lock2'的情况下尝试获取锁对象'lock1'。
                    synchronized(lock1) {
                        System.out.println("thread2 end");
                    }
                    // 释放锁对象'lock1'。
                }
                // 释放锁对象'lock2'。
            }
        };
        
        // 启动线程
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        // 主线程休眠两分钟，模拟耗时任务。
        Thread.sleep(2000);
        // 打印主线程结束信息。
        System.out.println("main thread end");
    }
}
