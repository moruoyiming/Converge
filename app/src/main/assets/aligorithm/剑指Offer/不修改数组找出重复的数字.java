package com.algorithm.demo.剑指Offer;

/**
 * 在一个长度为n+1的数组里的所有数字都在1-n的范围内，所以数组中至少有一个数字是重复的。
 * 如输入长度为8的数字{2,3,5,4,3,2,6,7},那么对应的输出是重复的数字2或者3。
 */
public class 不修改数组找出重复的数字 {

    public static void main(String[] args) {
        int[] nums = {2,3,5,4,3,2,6,7};
        int result = getDuplication(nums);
        System.out.println("result="+result);
    }

    public static int getDuplication(int[] nums) {
        if (nums.length <= 0) {
            return -1;
        }
        int start = 1;
        int end = nums.length - 1;
        while (end >= start) {
            int middle = ((end - start) >> 1) + start;
            int count = countRange(nums, nums.length, start, middle);
            if (end == start) {
                if (count >= 1) {
                    return start;
                } else {
                    break;
                }
            }
            if (count > (middle - start + 1)) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 时间复杂度O(logn)次
     * 空间复杂度O(1)次
     * @param nums
     * @param length
     * @param start
     * @param end
     * @return
     */
    public static int countRange(int[] nums, int length, int start, int end) {
        if (nums == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (nums[i] >= start && nums[i] <= end) {
                ++count;
            }
        }
        return count;
    }

}
