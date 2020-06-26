package com.example.basics.designpattern.singleton;

/**
 * 单例模式
 * Ensure a class has only one instance, and provide a global point of access to it.
 * （确保某一个类只有一个实例，而且自行实例化并向整个系统提供这个实例。）
 * 懒汉   饿汉   线程安全
 */
public class Singleton {

    //volatile 可见性 指令重排序  不能确保原子性
    private static volatile Singleton singleton;

    private Singleton() {

    }

    /**
     * DCL double check lock
     * @return
     */
    public static Singleton getSingleton() {
        if (singleton == null) {//1.线程A执行到这里时挂起 2.线程B也开始执行该判断并挂起 AB线程都可进入
            synchronized (Singleton.class) {//3.线程A开始执行。 6.线程B接着执行
                if (singleton == null) {//4.线程A判空校验，为空。 7.线程B判空校验，singleton不为空，直接返回。
                    singleton = new Singleton();//5.创建实例对象。线程A执行完毕释放。
                }
            }
        }
        return singleton;
    }

    public void what(){

    }
}
