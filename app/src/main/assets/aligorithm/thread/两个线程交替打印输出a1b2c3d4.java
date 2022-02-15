package com.algorithm.demo.thread;

public class 两个线程交替打印输出a1b2c3d4 {

    private static String[] ms = {"a", "1", "b", "2", "c", "3", "d", "4"};
    private static int x = 0;

    public static void main(String[] args) {
        method();
    }

    /**
     * 两个线程交替打印输出a1b2c3d4
     */
    public static void method() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    try {
                        int threadId = Integer.parseInt(Thread.currentThread().getName());
                        while (x < ms.length) {
                            if (x % 2 == threadId - 1) {
                                System.out.println("threadid = " + threadId + " " + ms[x]);
                                x++;
                                notifyAll();
                            } else {
                                wait();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t1 = new Thread(r, "1");
        Thread t2 = new Thread(r, "2");
        t1.start();
        t2.start();
    }
}
