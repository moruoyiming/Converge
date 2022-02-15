package com.algorithm.demo.thread;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch: 一个线程(或者多个)， 等待另外N个线程完成某个事情之后才能执行。一个线程中可以多次执行countDown
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for(int i=0;i<5;i++){
            new Thread(new readNum(i,countDownLatch)).start();
        }
        countDownLatch.await();
        System.out.println("线程执行结束。。。。");
    }

    static class readNum  implements Runnable{
        private int id;
        private CountDownLatch latch;
        public readNum(int id,CountDownLatch latch){
            this.id = id;
            this.latch = latch;
        }
        @Override
        public void run() {
            synchronized (this){
                System.out.println("id:"+id);
                latch.countDown();
                System.out.println("线程组任务"+id+"结束，其他任务继续");
            }
        }
    }

}
