package com.algorithm.demo.designpattern.my.singleton;

/**
 * 饿汉式：一上来就初始化对象，线程安全， 容易产生垃圾对象，类加载是就初始化，浪费内存
 * 优点：没有加锁，执行效率会提高。
 * 缺点：类加载时就初始化，浪费内存。
 */
public class Singleton_hungry {

    private static Singleton_hungry singleton = new Singleton_hungry();

    private Singleton_hungry() {
    }

    public static synchronized Singleton_hungry getInstance() {
        return singleton;
    }
}
