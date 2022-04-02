package com.example.converge.note.javabasics.generic;

/**
 * 类型变量的限定-方法上
 */
public class ArrayAlg {

//    public static <T> T min (T a, T b){
//        if(a.compareTo (b) > 0){
//            return a;
//        }else{
//            return b;
//        }
//    }

    public static <T extends Comparable> T min (T a, T b){
        if(a.compareTo(b) > 0){
            return a;
        }else{
            return b;
        }
    }


}
