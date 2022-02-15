package com.algorithm.demo.剑指Offer;

/**
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0~n-1之内。在范围0~n-1内的n个数字中有且只有一个数字不在该数组中，
 * 请找出这个数字。
 * 解法1：先用公式n(n-1)/2求出数字0~n-1的所有数字之和，记为s1.接着求出数组中所有数字的和，记为s2。那个不在数组中的数字就是s1-s2的差。
 * 这种解法需要O(n)的时间求数组中所有数字的和。显然，该解法没有有效利用数组是递增排序的一个特点。
 * 因为0～n-1这些数字在数组中是排序的，因此数组中开始的一些数字与它们的下标相同。也就是说，0在下标位0的位置，1在下标为1的位置，依次类推。
 * 如果不再数组中的那个数字记为m，那么所有比m小的数字的下标都与它们的值相同。
 * 由于m不再数组中，那么m+1处在下标为m的位置，m+2处在下标为m+1的位置，以此类推。我们发现m正好是数组中第一个数值和下标不想等的下标，
 * 因此这个问题转换成在排序数组中找出第一个值和下标不想等的元素。
 */
public class 零至n减1中缺失的数字 {
    /**
     * 二分查找
     *
     * @param array
     * @param length
     * @return
     */
    public int getMissingNumber(int[] array, int length) {
        if (array == null || length <= 0)
            return -1;
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int middle = (left + right) >> 1;
            if (array[middle] != middle) {
                if (middle == 0 || array[middle - 1] == middle - 1) {
                    return middle;
                }
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        if (left == length) {
            return length;
        }
        return -1;
    }

}
