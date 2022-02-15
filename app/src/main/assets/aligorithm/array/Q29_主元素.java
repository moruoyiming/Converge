package com.algorithm.demo.array;

import com.algorithm.demo.PrintArray;

import java.util.List;

/**
 * 描述
 * 给定一个整型数组，找出主元素，它在数组中的出现次数大于数组元素个数的二分之一。
 * <p>
 * 假设数组非空，且数组中总是存在主元素。
 * <p>
 * 样例
 * 样例 1：
 * <p>
 * 输入：
 * <p>
 * 数组 = [1, 1, 1, 1, 2, 2, 2]
 * 输出：
 * <p>
 * 1
 * 解释：
 * <p>
 * 数组中1的个数大于数组元素的二分之一。
 * <p>
 * 样例 2：
 * <p>
 * 输入：
 * <p>
 * 数组 = [1, 1, 1, 2, 2, 2, 2]
 * 输出：
 * <p>
 * 2
 * 解释：
 * <p>
 * 数组中2的个数大于数组元素的二分之一。
 * <p>
 * 挑战
 * 要求时间复杂度为O(n)，空间复杂度为O(1)
 */
public class Q29_主元素 {

    /*
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public static int majorityNumber(List<Integer> nums) {
        int currentMajor = 0;
        int count = 0;

        for (Integer num : nums) {
            if (count == 0) {
                currentMajor = num;
            }

            if (num == currentMajor) {
                count++;
            } else {
                count--;
            }
        }
        return currentMajor;
    }
}
