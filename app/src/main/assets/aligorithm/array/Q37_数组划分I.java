package com.algorithm.demo.array;

import java.util.Arrays;

/**
 * 993. 数组划分 I
 * 给一个有 2n 个整数的数组，你的任务是把这些整数分成 n 组，如(a1, b1)，(a2, b2)，...，(an, bn)。并且使得 i 从 1 到 n 的 min(ai, bi)之和尽可能的大。
 * <p>
 * 样例
 * 样例1:
 * <p>
 * 输入: [1,4,3,2]
 * 输出: 4
 * 解释: n 是 2, 最大的数对和为 4 = min(1, 2) + min(3, 4).
 * 样例 2:
 * <p>
 * 输入: [5,6]
 * 输出: 5
 * 解释: n 是 1, 最大的数对和为 5 = min(5, 6) .
 * 注意事项
 * n 是一个正整数，且范围为 [1, 10000].
 * 数组中的元素范围为[-10000, 10000]。
 */
public class Q37_数组划分I {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 4, 3, 2};
        int result = arrayPairSum(nums);
        System.out.println(" result is " + result);
    }

    /**
     *
     * 时间复杂度：O(n\log n)O(nlogn)，即为对数组 \textit{nums}nums 进行排序的时间复杂度。
     * 空间复杂度：O(\log n)O(logn)，即为排序需要使用的栈空间。
     * @param nums: an array
     * @return: the sum of min(ai, bi) for all i from 1 to n
     */
    public static int arrayPairSum(int[] nums) {
        // 将数组排序后，取出所有奇数位（下标为偶数）求和即可。
        // Write your code here
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length; i += 2) {
            ans += nums[i];
        }
        return ans;
    }
}
