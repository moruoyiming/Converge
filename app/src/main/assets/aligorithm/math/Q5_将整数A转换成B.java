package com.algorithm.demo.math;

/**
 * 181 · 将整数A转换为B
 * <p>
 * 描述
 * 如果要将整数n转换为m，需要改变多少个bit位？
 * n和m均为32位整数。
 * 样例
 * 样例 1:
 * 输入: n = 31, m = 14
 * 输出:  2
 * 解释:
 * (11111) -> (01110) 需要改变两个位.
 */
public class Q5_将整数A转换成B {

    public static void main(String[] args) {

    }

    /**
     * @param a: An integer
     * @param b: An integer
     * @return: An integer
     */
    public int bitSwapRequired(int a, int b) {
        // write your code here
        int count = 0;
        for (int c = a ^ b; c != 0; c = c >>> 1) {
            count += c & 1;
        }
        return count;
    }

}
