package com.example.converge.note.javabasics.concurrent.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock锁方法
 * 1、基本思路
 * 通过ReentrantLock我们可以很方便的进行显式的锁操作，即获取锁和释放锁，对于同一个对象锁而言，统一时刻只可能有一个线程拿
 * 到了这个锁，此时其他线程通过lock.lock()来获取对象锁时都会被阻塞，直到这个线程通过lock.unlock()操作释放这个锁后，
 * 其他线程才能拿到这个锁。
 *
 */
public class ABC_Lock {

    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }

    private static Lock lock = new ReentrantLock();//通过JDK5中的Lock锁来保证线程的访问互斥
    private static int state = 0;//通过state的值来确定是否打印


    static class ThreadA extends Thread{
        @Override
        public void run() {
            for(int i = 0 ; i < 10;){
                try{
                    lock.lock();
                    while (state % 3 == 0){// 多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                        System.out.println("A");
                        state++;
                        i++;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();// unlock操作必须放到finally模块中
                }
            }
        }
    }

    static class ThreadB extends Thread{
        @Override
        public void run() {
            for(int i = 0; i < 10;){
                try{
                    lock.lock();
                    while(state % 3 == 1){
                        System.out.println("B");
                        state++;
                        i++;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally{
                    lock.unlock();
                }
            }
        }
    }

    static class ThreadC extends Thread{
        @Override
        public void run() {
            for(int i = 0; i < 10;){
                try{
                    lock.lock();
                    while(state % 3 == 2){
                        System.out.println("C");
                        state++;
                        i++;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally{
                    lock.unlock();
                }
            }
        }
    }


}
