package com.algorithm.demo.array;

/**
 * 描述
 * 设计一个算法，计算出n阶乘中尾部零的个数。
 * 样例
 * 样例 1：
 * 输入：
 * n = 11
 * 输出：
 * 2
 * 解释：
 * 11! = 39916800, 结尾的0有2个。
 */
public class Q6_尾部的零 {

    public static void main(String[] args) {
        long result = trailingZeros(11);
        System.out.println("result = " + result);
    }


    /*
     * @param n: An integer
     * @return: An integer, denote the number of trailing zeros in n!
     * 可以将每个数拆分成其素因子的乘积，可以发现，0是由2*5产生的，而5的数量一定小于2的数量，因此5的个数决定了结尾0的个数。
     * 只要计算n的阶乘中，5这个素因子出现多少次即可。
     */
    public static long trailingZeros(long n) {
        // write your code here, try to do it without arithmetic operators.
        long sum = 0;
        while (n != 0) {
            sum += n / 5;
            System.out.println("sum=" + sum);
            n /= 5;
            System.out.println("sum=" + sum + ", n=" + n);
        }
        return sum;
    }

    public static long trailingZeros2(long n) {
        long sum = 0;
        while (n != 0) {
            sum += n / 5;
            n /= 5;
        }
        return sum;
    }

}
