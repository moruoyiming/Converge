package com.algorithm.demo.thread;

/**
 * 写两个线程，一个线程打印1-52，另一个线程打印字母A-Z。打印 顺序为12A34B56C……5152Z
 */
public class ThreadTest {
    public static void main(String[] args) {
        Object obj = new Object();
        // 启动两个线程
        Thread1 t1 = new Thread1(obj);

        Thread2 t2 = new Thread2(obj);

        t1.start();
        t2.start();

    }


    // 一个线程打印1-52
    static class Thread1 extends Thread {
        private Object obj;

        public Thread1(Object obj) {
            this.obj = obj;
        }

        public void run() {
            synchronized (obj) {
                // 打印1-52
                for (int i = 1; i < 53; i++) {
                    System.out.print(i + " ");
                    if (i % 2 == 0) {
                        // 不能忘了 唤醒其它线程
                        obj.notifyAll();
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }

    }

    // 另一个线程打印字母A-Z
    static class Thread2 extends Thread {
        private Object obj;

        public Thread2(Object obj) {
            this.obj = obj;
        }

        public void run() {
            synchronized (obj) {
                // 打印A-Z
                for (int i = 0; i < 26; i++) {
                    System.out.print((char) ('A' + i) + " ");
                    // 不能忘了 唤醒其它线程
                    obj.notifyAll();
                    try {
                        // 最后一个就不要等了
                        if (i != 25) {
                            obj.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }

    }
}