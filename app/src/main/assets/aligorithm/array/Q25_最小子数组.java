package com.algorithm.demo.array;


import java.util.List;

public class Q25_最小子数组 {

    public static void main(String[] args) {
        int[] array = {1, -2, 3, 10, -4, 7, 2, -5};
        Long begintime = System.nanoTime();
        int result = minSubArray(array);
        Long endtime = System.nanoTime();
        System.out.println("连续子数组的最小和为：" + result + ",运行时间：" + (endtime - begintime) + "ns");
    }

    public static int minSubArray(int[] nums) {
        int currentsum = nums[0];
        int greateminsum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (currentsum > 0) {
                currentsum = nums[i];
            } else {
                currentsum += nums[i];
            }
            if (currentsum < greateminsum) {
                greateminsum = currentsum;
            }
        }
        return greateminsum;
    }

    /*
     * @param nums: a list of integers
     * @return: A integer indicate the sum of minimum subarray
     */
    public int minSubArray2(List<Integer> nums) {
        int currentsum = nums.get(0);
        int greateminsum = nums.get(0);
        for(int i = 1; i < nums.size(); i++){
            if(currentsum > 0){
                currentsum = nums.get(i);
            }else{
                currentsum += nums.get(i);
            }
            if(currentsum < greateminsum){
                greateminsum = currentsum;
            }
        }
        return greateminsum;
    }

}
