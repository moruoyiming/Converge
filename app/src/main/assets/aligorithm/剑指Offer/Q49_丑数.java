package com.algorithm.demo.剑指Offer;

/**
 * 我们把只包含因子2、3和5的数称作为丑数。
 * 求按从小到大的顺序的第1500个丑数。例如：6、8都是丑数，但14不是，因为它包含因子7.
 * 1当作第一个丑数。
 */
public class Q49_丑数 {

    public int getUglyNumber(int index) {
        if (index <= 0)
            return 0;
        int number = 0;
        int uglyFound = 0;
        while (uglyFound < index) {
            ++number;
            if (isUgly(number)) {
                ++uglyFound;
            }
        }
        return number;
    }

    public boolean isUgly(int number) {
        while (number % 2 == 0)
            number /= 2;
        while (number % 3 == 0)
            number /= 3;
        while (number % 5 == 0)
            number /= 5;
        return (number == 1) ? true : false;
    }

}
