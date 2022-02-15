package com.algorithm.demo.array;


import com.algorithm.demo.PrintArray;

/**
 * 101. 删除排序数组中的重复数字 II
 * 给你一个排序数组，删除其中的重复元素，使得每个数字最多出现两次，返回新的数组的长度。
 * 如果一个数字出现超过2次，则这个数字最后保留两个。
 * <p>
 * 样例
 * 样例 1:
 * 输入: []
 * 输出: 0
 * <p>
 * <p>
 * 样例 2:
 * 输入:  [1,1,1,2,2,3]
 * 输出: 5
 * <p>
 * 样例解释:
 * 长度为 5，  数组为：[1,1,2,2,3]
 * 注意事项
 * 需要在原数组中操作
 */
public class Q32_删除排序数组中的重复数字2 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int x = removeDuplicates(nums);
        System.out.println("xxxx  " + x);
    }

    /*
     * @param nums: An ineger array
     * @return: An integer
     */
    public static int removeDuplicates(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        int count = 1;
        for (int j = 1; j < nums.length; ++j) {
            if (nums[i] == nums[j] && count < 2) {
                ++count;
                nums[++i] = nums[j];
            } else if (nums[i] != nums[j]) {
                count = 1;
                nums[++i] = nums[j];
            }
            PrintArray.print(nums);
        }
        return i + 1;

    }

}
