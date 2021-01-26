package com.example.converge.note.javabasics.concurrent.thread.threadlocal;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized 隐式锁 内部会完成 锁定解锁底层逻辑。修改不了，需要关键字 synchronized
 * lock 显式锁 可以手动控制锁定解锁
 */
public class LockDemo {

    private int count = 0;
    //可重入锁 ReentrantLock()  synchronized 可重入锁
    private Lock lock = new ReentrantLock();

    public void incr() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }


    }
}
