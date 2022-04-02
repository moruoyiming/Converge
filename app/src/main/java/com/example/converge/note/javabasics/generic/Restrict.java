package com.example.converge.note.javabasics.generic;

/**
 * @Date: 2022/4/1
 * @Time: 16:10
 * @Author: Jian
 */
public class Restrict<T> {

    private T data;

    //1.不能实例化类型变量
//    public Restrict(){
//        this.data = new T();
//    }

    //2.静态域或者方法里不能引用类型变量
//    private static T instance;

    //静态方法

    // 本身是泛型方法就行
//    private static <T> T getInstance(){
//        return data;
//    }

    public static void main(String[] args) {
//        Restrict<Double> restrict;
//        if(restrict instanceof Restrict<Double>){
//
//        }
    }

}
