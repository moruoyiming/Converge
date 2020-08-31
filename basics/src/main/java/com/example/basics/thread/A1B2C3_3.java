package com.example.basics.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class A1B2C3_3 {

    public static void main(String[] args) {

        char[] a1 = "ABCDEFGHIGKLMNOPQRSTUVWXYZ".toCharArray();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Condition condition2 = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                for (char c : a1) {
                    System.out.println(c);
                    condition2.signal();
                    condition.await();
                }
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                for (int i = 1; i < 27; i++) {
                    System.out.println(i + "");
                    condition.signal();
                    condition2.await();
                }
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

    }

}
