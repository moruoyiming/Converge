package com.algorithm.demo.designpattern.my.singleton;

/**
 * 优点：它不仅能避免多线程同步问题，而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化。
 * 缺点：当想实例化一个单例类的时候，必须要记住使用相应的获取对象的方法，而不是使用new，可能会给其他开发人员造成困扰，特别是看不到源码的时候。（在实际工作中，很少使用）
 */
public enum Singleton_enum {
    INSTANCE;  
    public void whateverMethod() {  
    }  
}