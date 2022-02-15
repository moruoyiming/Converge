package com.algorithm.demo.array;


import com.algorithm.demo.PrintArray;

/**
 * 100.删除排序数组中的重复数字
 * 给定一个排序数组，在原数组中“删除”重复出现的数字，使得每个元素只出现一次，并且返回“新”数组的长度。
 * <p>
 * 不要使用额外的数组空间，必须在不使用额外空间的条件下原地完成。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入:  []
 * 输出: 0
 * 样例 2:
 * <p>
 * 输入:  [1,1,2]
 * 输出: 2
 * 解释:  数字只出现一次的数组为: [1,2]
 */
public class Q30_删除排序数组中的重复数字 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        int x = method(nums);
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
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        PrintArray.print(nums);
        return i + 1;

    }

    public static int method(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

}
