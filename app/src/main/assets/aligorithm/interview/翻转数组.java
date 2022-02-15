package com.algorithm.demo.interview;


import com.algorithm.demo.PrintArray;

/**
 * 767. 翻转数组
 * 原地翻转给出的数组 nums
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入 : nums = [1,2,5]
 * 输出 : [5,2,1]
 * 注意事项
 * 原地意味着你不能使用额外空间
 */
public class 翻转数组 {

    public static void main(String[] args) {
        PrintArray.printBefore(PrintArray.SRC);
        reverseArray(PrintArray.SRC);
    }

    /**
     * @param nums: a integer array
     * @return: nothing
     */
    public static void reverseArray(int[] nums) {

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }

    }


}
