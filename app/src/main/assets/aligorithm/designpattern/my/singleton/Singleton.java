package com.algorithm.demo.designpattern.my.singleton;

/**
 * 单例模式
 * Ensure a class has only one instance, and provide a global point of access to it.
 * （确保某一个类只有一个实例，而且自行实例化并向整个系统提供这个实例。）
 * 懒汉   饿汉   线程安全
 * //    DCL: double-checked locking(双重锁/双重校验锁)
 * //    优点：线程安全，延迟加载，效率较高
 * //    singleton 采用 volatile 关键字修饰也是很有必要的， singleton = new Singleton_DCL(); 这段代码其实是分
 * //    为三步执行：
 * //    1.为 singleton 分配内存空间
 * //    2.初始化 singleton
 * //    3.将 singleton 指向分配的内存地址
 * //    但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2。指令重排在单线程环境下不会出先问题，但是在多线程
 * //    环境下会导致一个线程获得还没有初始化的实例。例如，线程 T1 执行了 1 和 3，此时 T2 调用getUniqueInstance()
 * //    后发现 uniqueInstance 不为空，因此返回 uniqueInstance，但此时 uniqueInstance 还未被初始化。使用
 * //    volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
 */
public class Singleton {

    //volatile 可见性 防止指令重排序 不能确保原子性
    private static volatile Singleton singleton;

    private Singleton() {

    }

    /**
     * DCL double-checked lockin
     *
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


}
