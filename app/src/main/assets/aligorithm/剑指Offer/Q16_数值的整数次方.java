package com.algorithm.demo.剑指Offer;

/**
 * 实现函数 double power(double base,int exponent),求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
 */
public class Q16_数值的整数次方 {

    public static void main(String[] args) {

    }

    /**
     * 没有考虑指数exponent 小于1(零和负数)情况
     *
     * @param base
     * @param exponent
     * @return
     */
    public static double power(double base, int exponent) {
        double result = 1.0;
        for (int i = 1; i <= exponent; ++i) {
            result *= base;
        }
        return result;
    }

    /**
     * a(n)  =
     * a(n/2)*a(n/2)   n为偶数
     * a((n-1)/2)*a(n-1)/2*a   n为奇数
     *
     * @param base
     * @param exponent
     * @return
     */
    public static double power2(double base, int exponent) {
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;
        double result = power2(base, exponent >> 1);//右移运算符替代除以2运算
        result *= result;
        if ((exponent & 0x1) == 1) {//位与运算符替代了求余运算符(%)来判断一个数是奇数还是偶数
            result *= base;
        }
        return result;
    }

}
