package com.algorithm.demo.thread;


import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LinkedBlockingQueueTest {

    static BlockingQueue<String> strs = new LinkedBlockingQueue<>();

    static Random random = new Random();


    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    strs.put("LinkedBlockingQueueTest: " + i);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread-1").start();


        for (int i = 0; i < 5; i++) {
            new Thread(() -> {

                for (; ; ) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " take " + strs.take());
                        //如果为空  等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "thread-2 = " + i).start();
        }

    }


}
