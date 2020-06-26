package com.example.basics.thread;


public class ForCharacter {

    private volatile static char c = 'A';

    private volatile static int count = 0;

    private static volatile int orderNum = 1;// 必要的时候声明为volatile类型的

    public static void main(String[] args) {
        method3();
    }

    /**
     * 一个多线程的问题，用三个线程，顺序打印字母A-Z，输出结果是1A 2B 3C 1D 2E...
     */
    public static void method() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    try {
                        int threadId = Integer.parseInt(Thread.currentThread().getName());
                        while (count < 26) {
                            if (count % 3 == threadId - 1) {
                                System.out.println("线程id : " + threadId + "  " + (char) (c++) + " count " + count);
                                count++;
                                notifyAll();
                            } else {
                                wait();
                            }
                        }

                    } catch (Exception e) {

                    }
                }

            }
        };
        new Thread(r, "1").start();
        new Thread(r, "2").start();
        new Thread(r, "3").start();
    }

    /**
     * 一个多线程的问题，用五个线程，顺序打印数字1~无穷大，其中每5个数字为1组
     */
    public static void method2() {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                synchronized (this) {
                    try {
                        int threadId = Integer.parseInt(Thread.currentThread().getName());
                        while (true) {
                            if (orderNum % 5 == threadId || orderNum % 5 == 0) {
                                if (orderNum % 5 == 0)
                                    System.out.println("threadid = " + 5 + " " + orderNum++);
                                else
                                    System.out.println("threadid = " + threadId + " " + orderNum++);
                                Thread.sleep(1000);// 为了执行效果看的更清楚
                                notifyAll();
                            } else {
                                wait();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t1 = new Thread(r, "1");
        Thread t2 = new Thread(r, "2");
        Thread t3 = new Thread(r, "3");
        Thread t4 = new Thread(r, "4");
        Thread t5 = new Thread(r, "5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    private static String[] ms = {"a", "1", "b", "2", "c", "3", "d", "4"};
    private static int x = 0;

    /**
     * 两个线程交替打印输出a1b2c3d4
     */
    public static void method3() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    try {
                        int threadId = Integer.parseInt(Thread.currentThread().getName());
                        while (x < ms.length) {
                            if (x % 2 == threadId - 1) {
                                System.out.println("threadid = " + threadId + " " +ms[ x]);
                                x++;
                                notifyAll();
                            } else {
                                wait();
                            }
                        }
                    } catch (Exception e) {

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