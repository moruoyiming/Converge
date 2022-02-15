package com.algorithm.demo.剑指Offer;

/**
 * 把n个骰子仍在地上，所有骰子朝上一面的点数之和为s。输入n，打印s的所有可能的值出现的概率。、
 * 骰子一共有6个面，每个面上都有一个点数，对应的是1~6之间的一个数字。所以n个骰子的点数和最小值为n，最大值为6n。
 * n个骰子的所有点数排列数6^n。解决问题，首先统计出每个点数出现的次数，然后把每个点数出现的次数除以6^n，就能求出每个点数出现的概率。
 * 解法一：基于递归求骰子点数，时间效率不够高。
 * 解法二：基于循环求骰子点数，时间性能好。
 */
public class Q60_n个骰子的点数 {
    static int maxValue = 6;

    public static void main(String[] args) {
        printProbability(6);
    }

    public static void printProbability(int number) {
        if (number < 1)
            return;
        int maxSum = number * maxValue;
        int[] probabilities = new int[maxSum - number + 1];
        for (int i = number; i <= maxSum; ++i) {
            probabilities[i - number] = 0;
        }
        Probaility(number, probabilities);
        int total = (int) Math.pow(maxValue, number);
        for (int i = number; i <= maxSum; ++i) {
            double ratio = (double) probabilities[i - number] / total;
            System.out.println("ratio=" + ratio);
        }
    }

    private static void Probaility(int number, int[] probabilities) {
        for (int i = 1; i <= maxValue; ++i)
            Probaility(number, number, i, probabilities);
    }

    private static void Probaility(int original, int current, int sum, int[] probabilities) {
        if (current == 1) {
            probabilities[sum - original]++;
        } else {
            for (int i = 1; i <= maxValue; ++i) {
                Probaility(original, current - 1, i + sum, probabilities);
            }
        }
    }

}
