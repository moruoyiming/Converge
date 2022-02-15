package com.algorithm.demo.array;

import java.util.HashMap;

/**
 * 838. 子数组和为K
 * 给定一个整数数组和一个整数k，你需要找到连续子数列的和为k的总个数。
 * <p>
 * 样例
 * 样例1
 * <p>
 * 输入: nums = [1,1,1] 和 k = 2
 * 输出: 2
 * 解释:
 * 子数组 [0,1] 和 [1,2]
 * 样例2
 * <p>
 * 输入: nums = [2,1,-1,1,2] 和 k = 3
 * 输出: 4
 * 解释:
 * 子数组 [0,1], [1,4], [0,3] and [3,4]
 */
public class                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             Q26_子数组和为K {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 4, 3, 2, 1};
        int value = subarraySumEqualsK2(nums, 9);
        System.out.println("   " + value);
    }

    /**
     * @param nums: a list of integer
     * @param k:    an integer
     * @return: return an integer, denote the number of continuous subarrays whose sum equals to k
     */
    public static int subarraySumEqualsK(int[] nums, int k) {
        // write your code here
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * @param nums: a list of integer
     * @param k:    an integer
     * @return: return an integer, denote the number of continuous subarrays whose sum equals to k
     * 方法二：前缀和 + 哈希表优化
     * 思路和算法
     * <p>
     * 我们可以基于方法一利用数据结构进行进一步的优化，我们知道方法一的瓶颈在于对每个 ii，我们需要枚举所有的 jj 来判断是否符合条件，这一步是否可以优化呢？答案是可以的。
     * <p>
     * 我们定义 \textit{pre}[i]pre[i] 为 [0..i][0..i] 里所有数的和，则 \textit{pre}[i]pre[i] 可以由 \textit{pre}[i-1]pre[i−1] 递推而来，即：
     * <p>
     * \textit{pre}[i]=\textit{pre}[i-1]+\textit{nums}[i]
     * pre[i]=pre[i−1]+nums[i]
     * <p>
     * 那么「[j..i][j..i] 这个子数组和为 kk 」这个条件我们可以转化为
     * <p>
     * \textit{pre}[i]-\textit{pre}[j-1]==k
     * pre[i]−pre[j−1]==k
     * <p>
     * 简单移项可得符合条件的下标 jj 需要满足
     * <p>
     * \textit{pre}[j-1] == \textit{pre}[i] - k
     * pre[j−1]==pre[i]−k
     * <p>
     * 所以我们考虑以 ii 结尾的和为 kk 的连续子数组个数时只要统计有多少个前缀和为 \textit{pre}[i]-kpre[i]−k 的 \textit{pre}[j]pre[j] 即可。我们建立哈希表 \textit{mp}mp，以和为键，出现次数为对应的值，记录 \textit{pre}[i]pre[i] 出现的次数，从左往右边更新 \textit{mp}mp 边计算答案，那么以 ii 结尾的答案 \textit{mp}[\textit{pre}[i]-k]mp[pre[i]−k] 即可在 O(1)O(1) 时间内得到。最后的答案即为所有下标结尾的和为 kk 的子数组个数之和。
     * <p>
     * 需要注意的是，从左往右边更新边计算的时候已经保证了\textit{mp}[\textit{pre}[i]-k]mp[pre[i]−k] 里记录的 \textit{pre}[j]pre[j] 的下标范围是 0\leq j\leq i0≤j≤i 。同时，由于\textit{pre}[i]pre[i] 的计算只与前一项的答案有关，因此我们可以不用建立 \textit{pre}pre 数组，直接用 \textit{pre}pre 变量来记录 pre[i-1]pre[i−1] 的答案即可。
     * 复杂度分析
     * <p>
     * 时间复杂度：O(n)O(n)，其中 nn 为数组的长度。我们遍历数组的时间复杂度为 O(n)O(n)，中间利用哈希表查询删除的复杂度均为 O(1)O(1)，因此总时间复杂度为 O(n)O(n)。
     * <p>
     * 空间复杂度：O(n)O(n)，其中 nn 为数组的长度。哈希表在最坏情况下可能有 nn 个不同的键值，因此需要 O(n)O(n) 的空间复杂度。
     * <p>
     */
    public static int subarraySumEqualsK2(int[] nums, int k) {
        // write your code here
        int count = 0, pre = 0;
        HashMap<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
