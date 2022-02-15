package com.algorithm.demo.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AbsClass extends AbsTractClass {
    public static void main(String[] args) {
        try {
            Class clz = Class.forName("com.algorithm.demo.java.AbsTractClass");
            Constructor construtor = clz.getConstructor();
            Object obj = construtor.newInstance();
            Method m3 = clz.getDeclaredMethod("method"); //获得get方法
            m3.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class clz = Class.forName("com.algorithm.demo.java.AbsClass");
            Constructor construtor = clz.getConstructor();
            Object obj = construtor.newInstance();
            Method m3 = clz.getDeclaredMethod("method"); //获得get方法
            m3.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    void method() {
        System.out.println("dsfsfsadfas");
    }
}
