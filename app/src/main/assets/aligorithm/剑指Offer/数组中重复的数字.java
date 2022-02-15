package com.algorithm.demo.剑指Offer;

public class 数组中重复的数字 {
    public static int[] nums = {2, 3, 1, 0, 2, 5, 3};

    public static void main(String[] args) {
        boolean result = duplicate(nums);
        System.out.println("result=" + result);
    }

    public static boolean duplicate(int[] nums) {
        if (nums.length <= 0) {
            return false;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < 0 || nums[i] > nums.length - 1) {
                return false;
            }
        }
        for (int i = 0; i < nums.length; ++i) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return true;
                }
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return false;
    }

}
