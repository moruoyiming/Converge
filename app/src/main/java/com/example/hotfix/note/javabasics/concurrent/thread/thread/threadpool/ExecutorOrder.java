package com.example.hotfix.note.javabasics.concurrent.thread.thread.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池执行顺序
 * pool-1-thread-2:1
 * pool-1-thread-3:7
 * pool-1-thread-1:0
 * pool-1-thread-4:8
 * pool-1-thread-5:9
 * pool-1-thread-6:10
 * Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task java.util.concurrent.FutureTask@2e5d6d97 rejected from java.util.concurrent.ThreadPoolExecutor@238e0d81[Running, pool size = 6, active threads = 6, queued tasks = 5, completed tasks = 0]
 * pool-1-thread-1:3
 * pool-1-thread-6:2
 * pool-1-thread-5:5
 * pool-1-thread-4:6
 * pool-1-thread-3:4
 *
 */
public class ExecutorOrder {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 6,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(5), new ThreadPoolExecutor.AbortPolicy());

        for(int i =0; i<15; i++) {
            final int id = i;
            pool.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + ":"+  id);
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
