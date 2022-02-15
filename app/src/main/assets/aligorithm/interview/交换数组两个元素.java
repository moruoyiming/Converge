package com.algorithm.demo.interview;

import com.algorithm.demo.PrintArray;


/**
 * 484. 交换数组两个元素
 * 给你一个数组和两个索引，交换下标为这两个索引的数字
 * <p>
 * 样例
 * Example 1:
 * <p>
 * Input: `[1,2,3,4]` and index1 = `2`, index2 = `3`
 * Output:The array will change to `[1,2,4,3]` after swapping. You don't need return anything, just swap the integers in-place.
 * Explanation: You don't need return anything, just swap the integers in-place.
 * Example 2:
 * <p>
 * Input: `[1,2,2,2]` and index1 = `0`, index2 = `3`
 * Output:The array will change to `[2,2,2,1]` after swapping. You don't need return anything, just swap the integers in-place.
 * Explanation: You don't need return anything, just swap the integers in-place.
 */
public class 交换数组两个元素 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        swapIntegers(nums, 2, 3);
    }

    /**
     * @param A:      An integer array
     * @param index1: the first index
     * @param index2: the second index
     * @return: nothing
     */
    public static void swapIntegers(int[] A, int index1, int index2) {
        // write your code here
        if (A == null || A.length == 0 || index1 > A.length || index2 > A.length) {
            return;
        }
        int temp = A[index1];
        A[index1] = A[index2];
        A[index2] = temp;

    }
}
