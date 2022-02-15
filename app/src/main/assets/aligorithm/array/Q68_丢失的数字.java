package com.algorithm.demo.array;

/**
 * 268. 丢失的数字
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * 示例 1：
 * <p>
 * 输入：nums = [3,0,1]
 * 输出：2
 * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1]
 * 输出：2
 * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 */
public class Q68_丢失的数字 {

    /**
     * 将从 00 到 nn 的全部整数之和记为 \textit{total}total，根据高斯求和公式，有：
     * total = n * ( n + 1) / 2;
     * 将数组 \textit{nums}nums 的元素之和记为 \textit{arrSum}arrSum，则 \textit{arrSum}arrSum 比
     * \textit{total}total 少了丢失的一个数字，因此丢失的数字即为 \textit{total}total 与 \textit{arrSum}arrSum 之差。
     * 时间复杂度：O(n)O(n)，其中 nn 是数组 \textit{nums}nums 的长度。需要 O(1)O(1) 的时间计算从 00 到 nn 的全部整数之和以及需要 O(n)O(n) 的时间计算数组 \textit{nums}nums 的元素之和。
     * 空间复杂度：O(1)O(1)。
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int total = n * (n + 1) / 2;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        int ans = total - sum;
        return ans;
    }

}
