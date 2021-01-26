package com.example.hotfix.note.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁，lock尝试拿锁 解决死锁
 */
public class TryLock {

    public static Lock no13 = new ReentrantLock();
    public static Lock no14 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("李四");
        TestThread thread = new TestThread("张三");
        thread.start();
        firstToSecond();
    }

    //先尝试拿No13锁 ， 在尝试拿No14锁，No14锁没拿到，连同No13锁一起释放掉
    public static void firstToSecond() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        Random random = new Random();
        while (true) {
            if (no13.tryLock()) {
                System.out.println(threadName + "get no13");
                try {
                    if (no14.tryLock()) {
                        try {
                            System.out.println(threadName + "get no14");
                            System.out.println("firstToSecond do work !");
                            break;
                        } finally {
                            no14.unlock();
                        }
                    }
                } finally {
                    no13.unlock();
                }
            }
            Thread.sleep(random.nextInt(3));
        }
    }


    //先尝试拿No14锁 ， 在尝试拿No13锁，No13锁没拿到，连同No14锁一起释放掉
    public static void secondToFirst() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        Random random = new Random();
        while (true) {
            if (no14.tryLock()) {
                System.out.println(threadName + "get no14");
                try {
                    if (no13.tryLock()) {
                        try {
                            System.out.println(threadName + "get no13");
                            System.out.println("secondToFirst do work !");
                            break;
                        } finally {
                            no13.unlock();
                        }
                    }
                } finally {
                    no14.unlock();
                }
            }
            Thread.sleep(random.nextInt(3));
        }
    }

    public static class TestThread extends Thread {
        private String name;

        public TestThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(name);

            try {
                secondToFirst();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
