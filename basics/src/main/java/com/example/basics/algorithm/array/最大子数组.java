package com.example.basics.algorithm.array;

public class 最大子数组 {

    public static void main(String[] args) {
        int[] array = {1, -2, 3, 10, -4, 7, 2, -5};
        Long begintime = System.nanoTime();
        int result = maxSubArray(array);
        Long endtime = System.nanoTime();
        System.out.println("连续子数组的最大和为：" + result + ",运行时间：" + (endtime - begintime) + "ns");
    }

    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int currentsum = nums[0];
        int greatsetsum = nums[0];
        System.out.println("第1步：累加子数组和：" + currentsum + "，最大子数组和：" + greatsetsum);
        for (int i = 1; i < nums.length; i++) {//{1, -2, 3, 10, -4, 7, 2, -5};
            if (currentsum > 0) {
                currentsum += nums[i];
            } else {
                currentsum = nums[i];
            }
            if (currentsum > greatsetsum) {
                greatsetsum = currentsum;
            }
            System.out.println("第" + (i + 1) + "步：累加子数组和：" + currentsum + "，最大子数组和：" + greatsetsum);
        }
        return greatsetsum;

    }
}
