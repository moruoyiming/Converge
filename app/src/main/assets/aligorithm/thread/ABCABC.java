package com.algorithm.demo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程 ，顺序打印ABC、ABC
 * 可重入锁实现方案
 */
public class ABCABC {
    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();

        MyRunnable runnableA = new MyRunnable(lock, conditionA, conditionB, 'A');
        MyRunnable runnableB = new MyRunnable(lock, conditionB, conditionC, 'B');
        MyRunnable runnableC = new MyRunnable(lock, conditionC, conditionA, 'C');
        new Thread(runnableA).start();
        Thread.sleep(100);
        new Thread(runnableB).start();
        Thread.sleep(100);
        new Thread(runnableC).start();
        Thread.sleep(100);
    }

    static class MyRunnable implements Runnable {

        private int times = 10;
        private ReentrantLock lock;
        private Condition thisCondition;
        private Condition nextCondition;
        private char ch;

        public MyRunnable(ReentrantLock lock, Condition thisCondition, Condition nextCondition, char ch) {
            this.lock = lock;
            this.thisCondition = thisCondition;
            this.nextCondition = nextCondition;
            this.ch = ch;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < times; i++) {
                    System.out.println(ch);
                    nextCondition.signal();
                    if (i < times - 1) {
                        thisCondition.await();//此处需要使用判断
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


}
