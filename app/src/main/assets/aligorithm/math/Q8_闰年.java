package com.algorithm.demo.math;

/**
 * 闰年是包含额外一天的日历年. 如果年份可以被 4 整除且不能被 100 整除 或者 可以被 400 整除, 那么这一年为闰年.
 */
public class Q8_闰年 {
    /**
     * @param n: a number represent year
     * @return: whether year n is a leap year.
     */
    public boolean isLeapYear(int n) {
        return (n % 4 == 0 && (n % 100 != 0 || n % 400 == 0));
    }
}
