package com.example.basics.algorithm.array;

import static com.example.basics.algorithm.LogUtils.log;

/**
 * 100.删除排序数组中的重复数字
 * 给定一个排序数组，在原数组中“删除”重复出现的数字，使得每个元素只出现一次，并且返回“新”数组的长度。
 * <p>
 * 不要使用额外的数组空间，必须在不使用额外空间的条件下原地完成。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入:  []
 * 输出: 0
 * 样例 2:
 * <p>
 * 输入:  [1,1,2]
 * 输出: 2
 * 解释:  数字只出现一次的数组为: [1,2]
 */
public class 删除排序数组中的重复数字 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        int x = removeDuplicates(nums);
        System.out.println("xxxx  "+x);
    }

    /*
     * @param nums: An ineger array
     * @return: An integer
     */
    public static int removeDuplicates(int[] nums) {
        // write your code here
        int i = 0, j;
        int temp = nums[0];
        for (j = 1; j < nums.length; j++) {
            if (temp != nums[j]) {
                nums[i] = nums[j];
                i++;
            }
            j++;
        }
        log(nums);
        return nums.length;

    }

}
