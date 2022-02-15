package com.algorithm.demo.geek;

import com.algorithm.demo.PrintArray;

public class Q9_旋转数组 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] temp = rotate(nums, 3);
        PrintArray.print(temp);

    }

    /**
     * @param nums: an array
     * @param k:    an integer
     * @return: rotate the array to the right by k steps
     */
    public static int[] rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int length = nums.length;
        if (k % length == 0) {
            return nums;
        }
        int[] temp = new int[nums.length];
        k %= length;
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                temp[i] = nums[length - k + i];
            } else {
                temp[i] = nums[i - k];
            }
        }
        return temp;
    }
}
