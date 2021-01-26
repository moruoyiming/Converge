package com.example.hotfix.note.javabasics.concurrent.thread.thread.theory.aqs;


import com.example.hotfix.note.javabasics.concurrent.thread.thread.tools.SleepTools;

import java.util.concurrent.locks.Lock;

/**
 *类说明：
 */
public class TestReenterSelfLock {

    static final Lock lock = new ReenterSelfLock();

    public void reenter(int x){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+":递归层级:"+x);
            int y = x - 1;
            if (y==0) return;
            else{
                reenter(y);
            }
        } finally {
            lock.unlock();
        }

    }

    public void test() {
        class Worker extends Thread {
			public void run() {
                System.out.println(Thread.currentThread().getName());
                SleepTools.second(1);
                reenter(3);
            }
        }
        // 启动3个子线程
        for (int i = 0; i < 3; i++) {
            Worker w = new Worker();
            w.start();
        }
        // 主线程每隔1秒换行
        for (int i = 0; i < 100; i++) {
        	SleepTools.second(1);
        }
    }

    public static void main(String[] args) {
        TestReenterSelfLock testMyLock = new TestReenterSelfLock();
        testMyLock.test();
    }
}
