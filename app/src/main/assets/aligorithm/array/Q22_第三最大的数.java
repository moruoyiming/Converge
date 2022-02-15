package com.algorithm.demo.array;

import java.util.TreeSet;

/**
 * 1250. 第三大的数
 * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入: num = [3, 2, 1]
 * 输出: 1
 * 解释: 第三大的数是 1.
 * 样例 2:
 * <p>
 * 输入: num = [1, 2]
 * 输出: 2
 * 解释: 第三大的数不存在, 所以返回最大的数 2 .
 * 样例 3:
 * <p>
 * 输入: num = [2, 2, 3, 1]
 * 输出: 1
 * 解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
 * 存在两个值为2的数，它们都排第二。
 */
public class Q22_第三最大的数 {

    public static void main(String[] args) {
//        int[] nums = {3, 2, 1};
//        int[] nums = {1, 2};
        int[] nums = {2, 2, 3, 1};
        int value = thirdMax(nums);
        System.out.println(" " + value);
    }

    /**
     * @param nums: the array
     * @return: the third maximum number in this array
     * 思路一：
     * <p>
     * 维护一个只有3个元素的TreeSet，如果大于三个元素就就把Set中的最小最小值remove掉。
     * 最后如果Set中元素小于3就返回Set最大值，否则返回最小值。
     */
    public static int thirdMax(int[] nums) {
        // Write your code here.
        if (nums == null || nums.length == 0) {
            return 0;
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (Integer elem : nums) {
            set.add(elem);
            if (set.size() > 3) {
                set.remove(set.first());
            }
        }
        return set.size() < 3 ? set.last() : set.first();
    }


}
