package com.algorithm.demo.array;

import com.algorithm.demo.PrintArray;

/**
 * 479 · 数组第二大数
 * 描述
 * 在数组中找到第二大的数。
 * <p>
 * 你可以假定至少有两个数字。
 * 第二大的数是指降序数组中第二个数字。
 * <p>
 * 样例
 * 例1：
 * <p>
 * 输入：[1,3,2,4]
 * 输出：3
 */
public class Q45_数组第二大数 {

    public static void main(String[] args) {
        int result = secondMax(PrintArray.SRC);
        System.out.println("result=" + result);
    }

    /**
     * @param nums: An integer array
     * @return: The second max number in the array.
     */
    public static int secondMax(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int max = Math.max(nums[0],nums[1]);
        int secMax = Math.min(nums[0],nums[1]);
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] > max) {
                secMax = max;
                max = nums[i];
            } else if (nums[i] > secMax) {
                secMax = nums[i];
            }
            System.out.println("max=" + max + "secMax" + secMax);
        }
        return secMax;
    }

}
