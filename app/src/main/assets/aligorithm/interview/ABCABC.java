package com.algorithm.demo.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ABCABC {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        MyRunnable1 runnableA = new MyRunnable1(lock, conditionA, conditionB, 'A');
        MyRunnable1 runnableB = new MyRunnable1(lock, conditionB, conditionC, 'B');
        MyRunnable1 runnableC = new MyRunnable1(lock, conditionC, conditionA, 'C');
        new Thread(runnableA).start();
        Thread.sleep(100);
        new Thread(runnableB).start();
        Thread.sleep(100);
        new Thread(runnableC).start();
        Thread.sleep(100);
    }


    static class MyRunnable1 implements Runnable {

        private ReentrantLock lock;
        private Condition thisCondition;
        private Condition nextCondition;
        private char cha;

        public MyRunnable1(ReentrantLock lock, Condition condition1, Condition condition2, char chr) {
            this.lock = lock;
            this.thisCondition = condition1;
            this.nextCondition = condition2;
            this.cha = chr;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 3; i++) {
                    System.out.println(cha);
                    nextCondition.signal();
                    if (i < 2) {
                        thisCondition.await();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

}
