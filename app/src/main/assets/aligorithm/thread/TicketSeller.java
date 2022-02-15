package com.algorithm.demo.thread;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 10个窗口卖票
 */
public class TicketSeller {

    static Queue<String> tickets = new ConcurrentLinkedDeque<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号：" + i);
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String s = tickets.poll();
                    if (s == null) {
                        break;
                    } else {
                        System.out.println(Thread.currentThread().getName() + " 销售了 " + s);
                    }
                }
            }, "thread " + i).start();
        }
    }
}
