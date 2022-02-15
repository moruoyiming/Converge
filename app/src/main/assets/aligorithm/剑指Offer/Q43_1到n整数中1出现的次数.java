package com.algorithm.demo.剑指Offer;

/**
 * 输入一个整数n，求1~n这个整数的十进制表示中1出现的次数。例如，输入12，1~12这些整数中包含1的数字有1、10、11和12，1一共出现了5次。
 */
public class Q43_1到n整数中1出现的次数 {

    public int NumberOf1Beteen1AndN(int n) {
        int number = 0;
        for (int i = 1; i <= n; ++i) {
            number += NumberOf1(i);
        }
        return number;
    }

    public int NumberOf1(int n) {
        int number = 0;
        while (n != 0) {
            if (n % 10 == 1)
                number++;

            n = n / 10;
        }
        return number;
    }

    public int NumberOf1Beteen1AndN2(int n) {
        if(n <= 0)
            return 0;
        String str = String.valueOf(n);
        return NumberOf12(str);
    }

    public int NumberOf12(String n) {
        int number = 0;
        return number;
    }
}
