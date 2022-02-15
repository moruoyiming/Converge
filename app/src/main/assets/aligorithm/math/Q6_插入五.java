package com.algorithm.demo.math;

/**
 * 188 · 插入五
 * 题目题解笔记讨论排名
 * 描述
 * 给定一个数字，在数字的任意位置插入一个5，使得插入后的这个数字最大。
 * |a| \leq 10^6∣a∣≤10
 * 6
 * 样例
 * 样例 1:
 * <p>
 * 输入:  a = 234
 * 输出: 5234
 */
public class Q6_插入五 {

    /**
     * @param a: A number
     * @return: Returns the maximum number after insertion
     * 如果a是正数，我们要想尽办法让数字大，所以我们把5插入第一次遇见的比五小的数前面。
     * 如果a是负数，我们要想尽办法让去掉负号后的数字小，所以我们把5插入到第一次遇见的比五大的数前面。
     */
    public int InsertFive(int a) {
        // write your code here
        String num = String.valueOf(a);
        int i = 0;
        if (a >= 0) {
            while (i < num.length() && num.charAt(i) > '5') {
                i++;
            }
        } else {
            i = 1;
            while (i < num.length() && num.charAt(i) < '5') {
                i++;
            }
        }
        return Integer.parseInt(num.substring(0, i) + '5' + num.substring(i));
    }

}
