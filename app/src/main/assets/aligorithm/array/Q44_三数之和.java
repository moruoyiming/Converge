package com.algorithm.demo.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 */
public class Q44_三数之和 {

    public static void main(String[] args) {

    }

    public List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums.length < 3) return result;
        Arrays.sort(nums);
        ArrayList<Integer> temp = null;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;  //选定nums[i]为第一个数，并去重
            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    temp = new ArrayList<Integer>();
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    result.add(temp);
                    while (left < right && nums[left] == nums[left + 1]) left++;  //去重
                    while (left + 1 < right && nums[right] == nums[right - 1]) right--;
                }
                if (sum <= 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

}
