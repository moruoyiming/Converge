package com.algorithm.demo.math;

/**
 * 408 · 二进制求和
 * 题目题解笔记讨论排名
 * 描述
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * 样例
 * 样例 1：
 * 输入：
 * a = "0", b = "0"
 * 输出：
 * "0"
 */
public class Q10_二进制求和 {
    /**
     * @param a: a number
     * @param b: a number
     * @return: the result
     */
    public String addBinary(String a, String b) {
        // write your code here
        String ans = "";
        int carry = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = carry;
            sum += (i >= 0) ? a.charAt(i) - '0' : 0;
            sum += (j >= 0) ? b.charAt(j) - '0' : 0;
            ans = (sum % 2) + ans;
            carry = sum / 2;
        }
        if (carry != 0) {
            ans = carry + ans;
        }
        return ans;
    }
}
