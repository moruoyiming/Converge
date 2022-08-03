package com.example.converge.note.javabasics.concurrent.thread;

import java.util.concurrent.Semaphore;

/**
 * 1、基本思路
 * Semaphore又称信号量，是操作系统中的一个概念，在Java并发编程中，信号量控制的是线程并发的数量。
 * public Semaphore(int permits)
 * 其中参数permits就是允许同时运行的线程数目;
 * Semaphore是用来保护一个或者多个共享资源的访问，Semaphore内部维护了一个计数器，其值为可以访问的共享资源的个数。
 * 一个线程要访问共享资源，先获得信号量，如果信号量的计数器值大于1，意味着有共享资源可以访问，则使其计数器值减去1，再访问共享资源。
 * 如果计数器值为0,线程进入休眠。当某个线程使用完共享资源后，释放信号量，并将信号量内部的计数器加1，之前进入休眠的线程将被唤醒并再次试图获得信号量。
 *
 * Semaphore使用时需要先构建一个参数来指定共享资源的数量，Semaphore构造完成后即是获取Semaphore、共享资源使用完毕后释放Semaphore。
 *
 */
public class ABC_Semaphore {

    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }

    private static Semaphore A = new Semaphore(1);
    private static Semaphore B = new Semaphore(0);
    private static Semaphore C = new Semaphore(0);

    static class ThreadA extends Thread{

        @Override
        public void run() {
            try{
                for(int i = 0; i < 10; i++){
                    A.acquire();
                    System.out.println("A");
                    B.release();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    static class ThreadB extends Thread{

        @Override
        public void run() {
            try{
                for(int i = 0; i < 10; i++){
                    B.acquire();
                    System.out.println("B");
                    C.release();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    static class ThreadC extends Thread{
        @Override
        public void run() {
            try{
                for(int i = 0; i < 10; i++){
                    C.acquire();
                    System.out.println("C");
                    A.release();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
