package com.algorithm.demo.array;

import java.util.List;

/**
 * 297. 寻找最大值
 * 寻找 n 个数中的最大值。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入：[1, 2, 3, 4, 5]
 * 输出：5
 * 注意事项
 * 保证列表里的所有数字都在 int 范围内。
 */
public class Q21_寻找最大值 {

    public static void main(String[] args) {

    }

    /**
     * @param nums: the list of numbers
     * @return: return the maximum number.
     */
    public int maxNum(List<Integer> nums) {
        // write your code here
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            max = Math.max(max, nums.get(i));
        }
        return max;
    }
}
