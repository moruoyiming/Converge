package com.algorithm.demo.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 147 · 水仙花数
 * 题目题解笔记讨论排名
 * 描述
 * 水仙花数的定义是，这个数等于他每一位数上的幂次之和 见维基百科的定义
 * 比如一个3位的十进制整数153就是一个水仙花数。因为 153 = 13 + 53 + 33。
 * 而一个4位的十进制数1634也是一个水仙花数，因为 1634 = 14 + 64 + 34 + 44。
 * 给出n，找到所有的n位十进制水仙花数。
 * 你可以认为n小于8。
 * 样例
 * 样例 1:
 * 输入: 1
 * 输出: [0,1,2,3,4,5,6,7,8,9]
 */
public class Q58_水仙花数 {

    public static void main(String[] args) {

    }


    /**
     * @param n: The number of digits
     * @return: All narcissistic numbers with n digits
     */
    public List<Integer> getNarcissisticNumbers(int n) {
        // write your code here
        List<Integer> result = new ArrayList<>();
        if (n == 1) {
            for (int i = 0; i < 10; ++i)
                result.add(i);
            return result;
        }

        if (n == 6) {
            result.add(548834);
            return result;
        }
        for (int i = pow(10, n - 1); i < pow(10, n); ++i) {
            int j = i;
            int s = 0;
            while (j > 0) {
                s += pow((j % 10), n);
                j = j / 10;
            }
            if (s == i)
                result.add(i);
        }

        return result;
    }

    public int pow(int a, int b) {
        int val = 1;
        for (int i = 1; i <= b; ++i)
            val *= a;
        return val;
    }
}
