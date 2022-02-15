package com.algorithm.demo.array;


import com.algorithm.demo.PrintArray;

/**
 * 407. 加一
 * 给定一个非负数，表示一个数字数组，在该数的基础上+1，返回一个新的数组。
 * <p>
 * 该数字按照数位高低进行排列，最高位的数在列表的最前面。
 * <p>
 * 样例
 * 样例 1：
 * <p>
 * 输入：[1,2,3]
 * 输出：[1,2,4]
 * 样例 2：
 * <p>
 * 输入：[9,9,9]
 * 输出：[1,0,0,0]
 * 如果数组末位（个位）小于 9 ，直接个位加 1 返回即可
 * 如果数组末位（个位）等于 9 ，将该位（个位）设置为 0 ，并且产生了进位，接下来观察前一位（十位）
 * 如果前一位（十位）小于 9 ，直接十位加 1 返回即可
 * 如果前一位（十位）等于 9 ，将该位（十位）设置为 0 ，并且产生了进位，接下来观察前一位（百位）
 */
public class Q13_加一 {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3};
        int[] nums = {9, 9, 9};
        int[] values = plusOne(nums);
        PrintArray.print(values);
    }

    /**
     * @param digits: a number represented as an array of digits
     * @return: the result
     */
    public static int[] plusOne(int[] digits) {
        // write your code here
        if (digits.length == 0) {
            return digits;
        }
        int n = digits.length;
        for (int i = digits.length - 1; i >= 0; --i) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] temp = new int[n + 1];
        temp[0] = 1;
        return temp;
    }

    public static int[] plusOne2(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; --i) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] temp = new int[n + 1];
        temp[0] = 1;
        return temp;
    }

}
