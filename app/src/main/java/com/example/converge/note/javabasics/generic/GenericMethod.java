package com.example.converge.note.javabasics.generic;

/**
 * 泛型方法
 */
public class GenericMethod {

    // <T> 为定义泛型方法的声明   T 为返回类型
    public <T> T genericMethod(T... a) {
        return a[a.length / 2];
    }

    public void test(int x, int y) {
        System.out.println(x + y);
    }


    public static void main(String[] args) {
        GenericMethod method = new GenericMethod();
        method.test(23,232);
        System.out.println(method.<String>genericMethod("mark","derry","lance"));
        System.out.println(method.genericMethod(123,"312","321"));
    }


}
