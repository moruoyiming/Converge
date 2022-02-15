package com.algorithm.demo.thread;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ConcurrentLinkedQueue {

    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedDeque<>();
        for (int i = 0; i < 10; i++) {
            strs.offer("Queue " + i);//类似  add 数据，区别 offer 加入失败有返回值boolean，add 加入失败抛异常。
        }
        System.out.println(strs);
        System.out.println(strs.size());
        // 获取数据并移除
        System.out.println(strs.poll());
        System.out.println(strs.size());
        // 获取数据
        System.out.println(strs.peek());
        System.out.println(strs.size());
    }
}
