package com.algorithm.demo.thread;

import java.util.concurrent.locks.LockSupport;

/**
 *  顺序打印  A 1 B 2 C 3 ... Z 26
 */
public class A1B2C3 {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        method();
    }

    public static void method(){
        char[] a1 = "ABCDEFGHIGKLMNOPQRSTUVWXYZ".toCharArray();
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(char s:a1){
                    System.out.println(s);
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        }, "T1");
        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =1 ; i < 27 ; i++){
                    LockSupport.park();
                    System.out.println(i);
                    LockSupport.unpark(t1);
                }
            }
        }, "T2");
        t1.start();

        t2.start();
    }

}
