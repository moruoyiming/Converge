package com.algorithm.demo.math;

/**
 * 310 · 数字变形
 * 题目题解笔记讨论排名
 * 描述
 * 现在题目给出一个整数A。
 * 整数B是A的变形，由整数A的位数交替形成。
 * 依次为A的右数第一位数，左数第一位数，右数第二位数.....以此类推，得到整数B。
 * <p>
 * 0<=A<=1e200
 * <p>
 * 样例
 * 输入: "12345678"
 * 输出:"81726354"
 */
public class Q9_数字变形 {
    /**
     * @param A: the integer discrible in problem
     * @return: the integer after distortion
     */
    public String Distortion(String A) {
        //
        char[] cs = new char[A.length()];
        for (int i = 0; i < A.length() / 2; i++) {
            cs[i * 2] = A.charAt(A.length() - 1 - i);
            cs[i * 2 + 1] = A.charAt(i);
        }
        if (A.length() % 2 == 1) {
            cs[A.length() - 1] = A.charAt(A.length() / 2);
        }
        return String.valueOf(cs);
    }
}
