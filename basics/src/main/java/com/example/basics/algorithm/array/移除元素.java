package com.example.basics.algorithm.array;

import static com.example.basics.algorithm.LogUtils.log;

/**
 * 172. 删除元素
 * 给定一个数组和一个值，在原地删除与值相同的数字，返回新数组的长度。
 * <p>
 * 元素的顺序可以改变，并且对新的数组不会有影响。
 * <p>
 * 样例
 * Example 1:
 * Input: [], value = 0
 * Output: 0
 * <p>
 * <p>
 * Example 2:
 * Input:  [0,4,4,0,0,2,4,4], value = 4
 * Output: 4
 * <p>
 * Explanation:
 * the array after remove is [0,0,0,2]
 */
public class 移除元素 {

    public static void main(String[] args) {
        int[] nums = {0, 4, 4, 0, 0, 2, 4, 4};
        int what = removeElement(nums, 4);
        System.out.println(" length " + what);
    }

    /*
     * @param A: A list of integers
     * @param elem: An integer
     * @return: The new length after remove
     */
    public static int removeElement(int[] A, int elem) {

        if (A == null || A.length == 0) {
            return 0;
        }

        int i = 0;
        //Input:  [0,4,4,0,0,2,4,4], value = 4
        for (int j = 0; j < A.length; j++) {
            if (A[j] != elem) {
                A[i] = A[j];
                i++;
            }
        }
        log(A);
        return i ;
    }
}
