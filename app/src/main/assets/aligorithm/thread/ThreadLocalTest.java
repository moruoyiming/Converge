package com.algorithm.demo.thread;

public class ThreadLocalTest {

    public static void main(String[] args) {

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        Thread thread = new Thread(){
            @Override
            public void run() {
                threadLocal.set("xcvxc");
                String wha=  threadLocal.get();
                System.out.println("  "+wha);
            }
        };
        thread.start();
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                threadLocal.set("sdfasdfa");
                String wha=  threadLocal.get();
                System.out.println("  "+wha);
            }
        };
        thread2.start();
    }



}
