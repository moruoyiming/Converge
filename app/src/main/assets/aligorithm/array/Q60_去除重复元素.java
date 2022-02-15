package com.algorithm.demo.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 521 · 去除重复元素
 * 题目题解笔记讨论排名
 * 描述
 * 给一个整数数组，去除重复的元素。
 * 你应该做这些事
 * 1.在原数组上操作
 * 2.将去除重复之后的元素放在数组的开头
 * 3.返回去除重复元素之后的元素个数
 * 不需要保持原数组的顺序
 * 例1:
 * 输入:
 * nums = [1,3,1,4,4,2]
 * 输出:
 * [1,3,4,2,?,?]
 * 4
 * 解释:
 * 1. 将重复的整数移动到 nums 的尾部 => nums = [1,3,4,2,?,?].
 * 2. 返回 nums 中唯一整数的数量  => 4.
 * 事实上我们并不关心你把什么放在了 ? 处, 只关心没有重复整数的部分.
 */
public class Q60_去除重复元素 {
    /**
     * @param nums: an array of integers
     * @return: the number of unique integers
     */
    public int deduplication(int[] nums) {
        // write your code here
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            set.add(nums[i]);
        }
        int result = 0;
        for (Integer i : set) {
            nums[result++] = i;
        }
        return result;
    }
}
