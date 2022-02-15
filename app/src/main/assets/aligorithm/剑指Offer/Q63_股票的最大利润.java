package com.algorithm.demo.剑指Offer;

/**
 * 假设把某股票的价格按照时间先后排序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 * 例如：一直股票在某些时间系欸但的价格为{9,11,8,5,7,12,16,14}。如果我们在价格为5的时候买入并再加个为16时卖出，则能守护最大的利润11.
 * 最大的利润就是数组中所有数对的最大差值。
 * 解法一：暴力法，找出数组中所有的数对，并注意求出他们的插值。由于长度为n的数组中存在O(n^2)个数对，因此该算法的时间复杂度时O(n^2)
 * 解法二：先定义函数diff(i)为当卖出价为数组中第i个数字时可能获得的最大利润。显然，在卖出价固定时，买入价越低获得的利润越大。
 * 如果在扫描到数组中的第i个数字时，只要我们能够记住之前的i-1个数字中最小值，就能算出在当前价位卖出时可能得到的最大利润。
 */
public class Q63_股票的最大利润 {

    public static void main(String[] args) {
        int[] numbers = {9, 11, 8, 5, 7, 12, 16, 14};
        int result = MaxDiff(numbers, numbers.length - 1);
        System.out.println("result=" + result);
    }

    /**
     * 时间复杂度O(n)
     *
     * @param numbers
     * @param length
     * @return
     */
    public static int MaxDiff(int[] numbers, int length) {
        if (numbers == null && length < 2)
            return 0;
        int min = numbers[0];
        int maxDiff = numbers[1] - min;
        for (int i = 2; i < length; ++i) {
            if (numbers[i - 1] < min)
                min = numbers[i - 1];
            int currentDiff = numbers[i] - min;
            if (currentDiff > maxDiff)
                maxDiff = currentDiff;
        }
        return maxDiff;
    }

}
