package com.example.basics.thread;

public class A1B2C3_2 {


    public static void main(String[] args) {
        char[] a1 = "ABCDEFGHIGKLMNOPQRSTUVWXYZ".toCharArray();
        Object object= new Object();
        Thread t1 = new Thread(() -> {
            synchronized (object) {
                for (char s : a1) {
                    try {
                        System.out.println(s + "");
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();
            }
        }, "T1");
        Thread t2 = new Thread(() -> {
            synchronized (object) {
                for (int i = 1; i < 27; i++) {
                    try {
                        System.out.println(i + "");
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();
            }
        }, "T2");


        t2.start();
        t1.start();
    }

}
