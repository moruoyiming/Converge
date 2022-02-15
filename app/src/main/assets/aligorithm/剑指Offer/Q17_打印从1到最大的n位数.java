package com.algorithm.demo.剑指Offer;

/**
 * 输入数字n，按顺序打印从1到最大的n位十进制数。比如输入3，则打印出1、2、3一直到最大的3位数999。
 */
public class Q17_打印从1到最大的n位数 {

    public static void main(String[] args) {

    }

    /**
     * 需要确定n的范围
     * @param n
     */
    public static void method(int n) {
        int number = 1;
        int i = 0;
        while (i++ < n) {
            number *= 10;
        }
        for (i = 1; i < number; ++i) {
            System.out.println("result " + i);
        }
    }

}
