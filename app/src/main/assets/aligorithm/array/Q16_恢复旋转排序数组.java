package com.algorithm.demo.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 恢复旋转排序数组
 * 中文English
 * 给定一个旋转排序数组，在原地恢复其排序。（升序）
 * <p>
 * 样例
 * Example1:
 * [4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
 * Example2:
 * [6,8,9,1,2] -> [1,2,6,8,9]
 * <p>
 * 挑战
 * 使用O(1)的额外空间和O(n)时间复杂度
 * <p>
 * 说明
 * 什么是旋转数组？
 * <p>
 * 比如，原始数组为[1,2,3,4], 则其旋转数组可以是[1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]
 */
public class Q16_恢复旋转排序数组 {

    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>();
//        4, 5, 1, 2, 3
        nums.add(4);
        nums.add(5);
        nums.add(1);
        nums.add(2);
        nums.add(3);
        recoverRotatedSortedArray(nums);
    }

    /**
     * @param nums: An integer array
     * @return: nothing
     */
    public static void recoverRotatedSortedArray(List<Integer> nums) {
        int temp = nums.get(0);
        int i;
        for (i = 0; i < nums.size() - 1; i++) {
            // 4, 5, 1, 2, 3
            if (nums.get(i) < temp) {//定位到临界值
                break;
            }
        }
        if (i != nums.size()) {
            for (int j = 0; j < i; j++) {
                nums.add(nums.get(j));// 将4,5追加到数组后 4,5,1,2,3,4,5
            }
            nums.subList(0, i).clear();// 清除4,5  剩余 1,2,3,4,5
            for (int m = 0; m < nums.size(); m++) {
                System.out.print(nums.get(m) + " ");
            }

        }

    }
}
