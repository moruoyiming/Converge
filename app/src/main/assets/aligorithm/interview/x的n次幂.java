package com.algorithm.demo.interview;

/**
 * 428 · x的n次幂
 * 描述
 * 实现 pow(x, n)。(n是一个整数)
 */
public class x的n次幂 {

    public static void main(String[] args) {

    }

    /**
     * @param x the base number
     * @param n the power number
     * @return the result
     */
    public double myPow(double x, int n) {
        // write your code here
        boolean isNegative = false;
        if (n < 0) {
            x = 1 / x;
            isNegative = true;
            n = -(n + 1);       // Avoid overflow when n == MIN_VALUE
        }

        double ans = 1, tmp = x;

        while (n != 0) {
            if (n % 2 == 1) {
                ans *= tmp;
            }
            tmp *= tmp;
            n /= 2;
        }

        if (isNegative) {
            ans *= x;
        }
        return ans;
    }
}
