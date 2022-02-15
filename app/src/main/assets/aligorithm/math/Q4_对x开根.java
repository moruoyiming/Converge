package com.algorithm.demo.math;

/**
 * 141 · 对x开根
 * 题目题解笔记讨论排名
 * 描述
 * 实现 int sqrt(int x) 函数，计算并返回 x 的平方根。
 * 样例
 * 样例 1:
 * 输入:  0
 * 输出: 0
 * 样例 2:
 * 输入: 3
 * 输出: 1
 * 样例解释：
 * 返回对x开根号后向下取整的结果。
 * 样例 3:
 * 输入: 4
 * 输出: 2
 */
public class Q4_对x开根 {

    public static void main(String[] args) {

    }

    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // write your code here
        long X = (long) x;
        long l = 1, r = X;

        while (l + 1 < r) {
            long mid = l + (r - l) / 2;
            if (mid * mid == X) {
                return (int) mid;
            } else if (mid * mid < X) {
                l = mid;
            } else {
                r = mid;
            }
        }
        if (r * r == X) return (int) r;
        return (int) l;
    }

}
