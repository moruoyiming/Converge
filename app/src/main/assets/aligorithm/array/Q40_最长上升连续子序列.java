package com.algorithm.demo.array;

import java.util.HashSet;

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
public class Q40_最长上升连续子序列 {

    public static void main(String[] args) {
        int[] nums = {5, 4, 2, 1, 3};
        int value = ContinuousSubsequence(nums);
        System.out.println("  " + value);
    }

    /**
     * @param nums: An array of Integer
     * @return: an integer
     */
    public static int ContinuousSubsequence(int[] nums) {
        HashSet<Integer> sets = new HashSet<>();
        //先将每个元素添加到hash集合中
        for (int i = 0; i < nums.length; i++) {
            sets.add(nums[i]);
        }
        int max = 0;
        int count;
        for (int i = 0; i < nums.length; i++) {
            count = 0;
            int val = nums[i];
            int valTmp = val;
            //然后再遍历每个元素，在遍历每个元素时，分别向两边扩展。当找不到相邻元素时退出。
            while (sets.contains(valTmp)) {
                count++;
                sets.remove(val);
                valTmp++;
            }
            while (sets.contains(val - 1)) {
                count++;
                sets.remove(val - 1);
                val--;
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

}
