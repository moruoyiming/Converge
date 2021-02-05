package com.example.basics.algorithm.array;

/**
 * 479. 数组第二大数
 * 在数组中找到第二大的数。
 * <p>
 * 样例
 * 例1：
 * <p>
 * 输入：[1,3,2,4]
 * 输出：3
 * 例2：
 * <p>
 * 输入：[1,1,2,2]
 * 输出：2
 * 注意事项
 * 你可以假定至少有两个数字。
 * 第二大的数是指降序数组中第二个数字。
 */
public class 第二最大的数 {

    public static void main(String[] args) {
        int[] numbs = {1, 3, 2, 4};
        int value = secondMax(numbs);
        System.out.println(" " + value);
    }

    /**
     * @param nums: An integer array
     * @return: The second max number in the array.
     */
    public static int secondMax(int[] nums) {
        // write your code here
       int sec_Max = nums[0];
        for (int i = 0; i < nums.length - 1; i++) {

        }
        return sec_Max;
    }

}
