package com.algorithm.demo.designpattern.my.singleton;

/**
 * 懒汉式:用的时候创建对象
 * 优点：第一次调用才初始化，避免内存浪费
 * 缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率
 */
public class Singleton_lazy {

    private static Singleton_lazy singleton;

    private Singleton_lazy() {
    }

    public static synchronized Singleton_lazy getInstance() {
        if (null == singleton) {
            singleton = new Singleton_lazy();
        }
        return singleton;
    }
}
