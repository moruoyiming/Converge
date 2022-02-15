package com.algorithm.demo.thread;

/**
 * 编号 1-10 10个线程同时执行计算  1-100 ，1-90 可以并行，90-100 需要每个线程之前的线程全部执行到90，才可以执行当前线程
 * CountDownLatch:具有计数器的功能，等待其他线程执行完毕，主线程在继续执行，用于监听某些初始化操作，并且线程进行阻塞，等初始化执行完毕后，通知主线程继续工作执行。
 * CyclicBrrier：翻译过来就是循环栅栏的意思，其作用就是多线程的进行阻塞，等待某一个临界值条件满足后，同时执行！
 *   CountDownLatch: 一个线程(或者多个)， 等待另外N个线程完成某个事情之后才能执行。
 *   CyclicBrrier: N个线程相互等待，任何一个线程完成之前，所有的线程都必须等待。
 */
public class 编号1到1010个线程执行计算 {

    private static int total;

    private static class SubThread extends Thread {
        private int start;
        private int end;

        public SubThread(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            int sum = 0;
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            System.out.println(sum);
            total += sum;
        }
    }

    public static void main(String[] args) {
        int start = 1;
        for (int i = 1; i <= 10; i++) {
            int end = start + 9;
            SubThread t = new SubThread(start, end);
            System.out.println(start + "," + end); //测试传入的参数是否正确
            t.start();
            start = end + 1;
            try {
                t.join(); //main线程让所有的子线程全部执行完了，才输出结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("最后的总和:" + total);
    }


}
