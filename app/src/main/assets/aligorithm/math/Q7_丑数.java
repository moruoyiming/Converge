package com.algorithm.demo.math;

/**
 * 517 · 丑数
 * 题目题解笔记讨论排名
 * 描述
 * 写一个程序来检测一个整数是不是丑数。
 * <p>
 * 丑数的定义是，只包含质因子 2, 3, 5 的正整数。比如 6, 8 就是丑数，但是 14 不是丑数因为他包含了质因子 7。
 * <p>
 * 可以认为 1 是一个特殊的丑数。
 * <p>
 * 样例
 * 例1:
 * <p>
 * 输入: num = 8
 * 输出: true
 * 解释:
 * 8=2*2*2
 */
public class Q7_丑数 {

    /**
     * @param num: An integer
     * @return: true if num is an ugly number or false
     */
    public boolean isUgly(int num) {
        // write your code here
        if (num <= 0) {
            return false;
        }
        if (num == 1) {
            return true;
        }
        while (num >= 2 && num % 2 == 0) num /= 2;
        while (num >= 3 && num % 3 == 0) num /= 3;
        while (num >= 5 && num % 5 == 0) num /= 5;
        return num == 1;
    }
}
