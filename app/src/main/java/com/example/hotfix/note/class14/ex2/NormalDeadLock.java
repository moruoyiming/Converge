package com.example.hotfix.note.class14.ex2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *类说明：演示普通账户的死锁和解决
 */
public class NormalDeadLock {
    private static Object LockA = new Object();//第一个锁
    private static Object LockB = new Object();//第二个锁

    Lock lock = new  ReentrantLock();

    //先拿第一个锁，再拿第二个锁
    private static void fisrtToSecond() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (LockA){
            System.out.println(threadName+" get A");
            Thread.sleep(100);
            synchronized (LockB){
                System.out.println(threadName+" get B");
            }
        }
    }
    //先拿第二个锁，再拿第一个锁
    private static void SecondToFisrt() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (LockB){
            System.out.println(threadName+" get B");
            Thread.sleep(100);
            synchronized (LockA){
                System.out.println(threadName+" get A");
            }
        }
    }

    private static class TestThread extends Thread{

        private String name;

        public TestThread(String name) {
            this.name = name;
        }

        public void run(){
            Thread.currentThread().setName(name);
            try {
                SecondToFisrt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread.currentThread().setName("Thread1");
        TestThread testThread = new TestThread("Thread2");
        testThread.start();
        try {
            fisrtToSecond();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
