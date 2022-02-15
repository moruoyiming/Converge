package com.algorithm.demo.array;

public class Q24_最大子数组 {

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

    public static int FindGreatestSumOfSubArray(int[] array) {
        int len = array.length;
        if (len == 0){
            return 0;
        }
        int[] currentsum = new int[len];
        currentsum[0] = array[0];
        int greatsetsum = array[0];
        System.out.println("第1步：累加子数组和："+currentsum[0]+"，最大子数组和："+greatsetsum);
        for(int i=1;i<array.length;i++){
            //下面是动态规划的状态转移方程
            if(currentsum[i-1]>0){
                currentsum[i] = currentsum[i-1] + array[i];
            }else{
                currentsum[i] = array[i];
            }
            //根据currentsum的值更新greatsetsum的值
            if(currentsum[i] > greatsetsum){
                greatsetsum  = currentsum[i];
            }
            System.out.println("第"+(i+1)+"步：累加子数组和："+currentsum[i]+"，最大子数组和："+greatsetsum);
        }
        return greatsetsum;
    }

    public static int maxSubArray2(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(currentSum > 0){
                currentSum += nums[i];
            }else{
                currentSum = nums[i];
            }
            if(maxSum < currentSum){
                currentSum = maxSum;
            }
        }
        return maxSum;
    }

}
