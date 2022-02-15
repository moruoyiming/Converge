package com.algorithm.demo.thread;

/**
 * @Date: 2021/12/28
 * @Time: 2:48 下午
 * @Author: Jian
 */
public class DeadLockTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                DeadLockTest.method1();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                DeadLockTest.method2();
            }
        });
        t1.start();
        t2.start();
    }

    public static void method1(){
        synchronized (DeadLockTest.class){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程 1 尝试获取integer.class");
            synchronized (Integer.class){
                System.out.println("线程 1 已拿到 integer.class 类锁");
            }
        }
    }

    public static void method2(){
        synchronized (Integer.class){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程 2 尝试获取DeadLockTest.class");
            synchronized (DeadLockTest.class){
                System.out.println("线程 2 已拿到 DeadLockTest.class 类锁");
            }
        }
    }
}
