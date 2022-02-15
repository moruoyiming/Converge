package com.algorithm.demo.geek;

import com.algorithm.demo.PrintArray;

public class Q2_斐波那契数列 {

    public static void main(String[] args) {
        faib();
    }
    // f(n) = f(n-1) + f(n-2)
    // 画出递归调用图
    // f6 = f5 + f4
    // f5 = f4+ f3
    // f4 = f3 + f2
    public static void faib(){
//        递归调用会出现性能问题
        int[] number = new int[10];
        number[0] = 0;
        number[1] = 1;
        for(int i = 2 ; i < number.length; i++){
            number[i] = number[i - 1] + number[i - 2];
        }
        PrintArray.print(number);
    }

}
