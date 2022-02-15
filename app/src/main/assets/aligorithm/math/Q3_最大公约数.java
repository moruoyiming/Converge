package com.algorithm.demo.math;

/**
 * 845 · 最大公约数
 * 样例
 * 样例1
 *
 * 输入: a = 10, b = 15
 * 输出: 5
 * 解释:
 * 10 % 5 == 0
 * 15 % 5 == 0
 */
public class Q3_最大公约数 {

    public static void main(String[] args) {
        int result = gcd(10, 15);
        System.out.println("result=" + result);
    }

    /**
     * @param a: the given number
     * @param b: another number
     * @return: the greatest common divisor of two numbers
     * 来自于《九章算术》：更相减损术，将两数辗转相减直至减数和余数相等，O(max(a, b))
     */
    public static int gcd(int a, int b) {
        if (a == b) {
            return a;
        } else if (b < a) {
            return gcd(a - b, b);
        } else {
            return gcd(b - a, a);
        }
    }

}
