package com.algorithm.demo.剑指Offer;

/**
 * 调整数组顺序使奇数位于偶数前面，这道题目既可以使用 首尾双指针，也可以使用 快慢双指针，本文采用首尾双指针法。
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 * <p>
 * 示例：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 * <p>
 * 二、题目解析
 * 定义头指针 left ，尾指针 right
 * <p>
 * left 一直往右移，直到它指向的值为偶数
 * <p>
 * right 一直往左移， 直到它指向的值为奇数
 * <p>
 * 交换 nums[left] 和 nums[right]
 * <p>
 * 重复上述操作，直到 left == right 为止
 */
public class Q21_调整数组顺序使奇数位于偶数前面 {

    /**
     * 时间复杂度为 O(N)  ，其中 N 为数组 nums 的长度。
     * 空间复杂度为 O(1)
     *
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        //定义头指针 left , 尾指针 right ， 临时变量tmp
        int left = 0, right = nums.length - 1, tmp;
        //重复操作，直到 left == right 为止
        while (left < right) {
            //nums[left] & 1 与 nums[right] & 1 都是用来判断数字奇偶性。
            //left 一直往右移，直到它指向的值为偶数
            while (left < right && (nums[left] & 1) == 1) left++;
            //right 一直往左移，直到它只想的数为奇数
            while (left < right && (nums[right] & 1) == 0) right--;
            //交换nums[left] 和 nums[right]
            tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
        }
        return nums;
    }

}
