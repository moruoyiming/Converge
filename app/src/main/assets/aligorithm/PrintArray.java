package com.algorithm.demo;

import java.util.List;

public class PrintArray {

    public final static int[] SRC
            = {86,11,77,23,32,45,58,63,93,4,37,22};

    public static void print(int[] array){
        for(int i :array){
            System.out.print(i+"  ");
        }
        System.out.println("");
    }

    public static void printBefore(int[] array){
        System.out.println("排序前顺序是");
        print(array);
    }

    public static void printAfter(int[] array){
        System.out.println("排序后顺序是");
        print(array);
    }

    public static void printIndex(int[] array,int begin ,int end){
        for(int i=begin;i<=end;i++){
            System.out.print(array[i]+"  ");
        }
        System.out.println("");
    }

    public static void printObject(List<Integer> array){
        for(int i :array){
            System.out.print(i+"  ");
        }
        System.out.println("");
    }

    public static void printObjectIndex(List<Integer> array,int begin ,int end){
        for(int i=begin;i<=end;i++){
            System.out.print(array.get(i)+"  ");
        }
        System.out.println("");
    }

    public static void printStringArrayAfter(String[] array){
        System.out.println("排序后顺序是");
        for(String i :array){
            System.out.print(i+"  ");
        }
        System.out.println("");
    }
    public static void printStringArrayBefore(String[] array){
        System.out.println("排序前顺序是");
        for(String i :array){
            System.out.print(i+"  ");
        }
        System.out.println("");
    }
}
