package com.example.basics.algorithm.array;

import java.util.Arrays;

/**
 * 1119. 三个数的最大乘积
 * 给定一个整数数组，找到三个元素，使乘积最大，返回该积。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: 6
 * 样例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: 24
 * 注意事项
 * 数组的长度范围为[3, 10^4]，所有的元素范围为[-1000, 1000]。
 * 任意三个元素的积不会超过32位有符号整数的范围。
 */
public class 三个数的最大乘积 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
//        int[] nums = {-1, 0, 1, 2};
        int value = maximumProduct(nums);
        System.out.println("  " + value);
    }

    /**
     * @param nums: an integer array
     * @return: the maximum product
     */
    private static int maximumProduct(int[] nums) {
        // Write your code here
        if (nums == null || nums.length <= 2) {
            return 0;
        }
        Arrays.sort(nums);
        int n = nums.length;
        if (nums[0] * nums[1] < nums[n - 2] * nums[n - 3]) {
            return nums[n - 1] * nums[n - 2] * nums[n - 3];
        } else {
            return nums[0] * nums[1] * nums[n - 1];
        }
    }

}
