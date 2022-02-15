package com.algorithm.demo.math;

/**
 * 569 · 各位相加
 * 描述
 * 给出一个非负整数 num，反复的将所有位上的数字相加，直到得到一个一位的整数。
 * <p>
 * 样例
 * 例1:
 * <p>
 * 输入:
 * num=38
 * 输出:
 * 2
 * 解释:
 * 过程如下： 3 + 8 = 11, 1 + 1 = 2. 因为 2 只有一个数字，返回 2.
 */
public class Q2_各位相加 {

    public static void main(String[] args) {
        int result = addDigits(38);
        System.out.println("result=" + result);
    }


    /**
     * @param num: a non-negative integer
     * @return: one digit
     */
    public static int addDigits(int num) {
        // write your code here
        while (num >= 10) {
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }

}
