package com.example.basics.algorithm.array;

/**
 * 397. 最长上升连续子序列
 * 给定一个整数数组（下标从 0 到 n-1， n 表示整个数组的规模），请找出该数组中的最长上升连续子序列。（最长上升连续子序列可以定义为从右到左或从左到右的序列。）
 * <p>
 * 样例
 * 样例 1：
 * <p>
 * 输入：[5, 4, 2, 1, 3]
 * 输出：4
 * 解释：
 * 给定 [5, 4, 2, 1, 3]，其最长上升连续子序列（LICS）为 [5, 4, 2, 1]，返回 4。
 * 样例 2：
 * <p>
 * 输入：[5, 1, 2, 3, 4]
 * 输出：4
 * 解释：
 * 给定 [5, 1, 2, 3, 4]，其最长上升连续子序列（LICS）为 [1, 2, 3, 4]，返回 4。
 * 挑战
 * 使用 O(n) 时间和 O(1) 额外空间来解决
 */
public class 最长上升连续子序列 {

    public static void main(String[] args) {
        int[] nums = {5, 4, 2, 1, 3};
        int value = ContinuousSubsequence(nums);
        System.out.println("  " + value);
    }

    /**
     * @param A: An array of Integer
     * @return: an integer
     */
    public static int ContinuousSubsequence(int[] A) {
        // write your code here
        return 0;
    }

}
