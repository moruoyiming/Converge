package com.algorithm.demo.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 1320. 包含重复值
 * 给定一个整数数组，查找数组是否包含任何重复项。 如果数组中某个值至少出现两次，则函数应返回true，如果每个元素都是不同的，则返回false。
 * <p>
 * 样例
 * 样例 1：
 * <p>
 * 输入：nums = [1, 1]
 * 输出：True
 * 样例 2：
 * <p>
 * 输入：nums = [1, 2, 3]
 * 输出：False
 */
public class Q33_包含重复值 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3,3};
        boolean value = containsDuplicate(nums);
        System.out.println(" 包含重复值 " + value);
    }

    /**
     * @param nums: the given array
     * @return: if any value appears at least twice in the array
     */
    public static boolean containsDuplicate(int[] nums) {
        // Write your code here
        Set<Integer> data = new HashSet<>();
        int left = 0;
        while (left < nums.length){
            System.out.println("  "+nums[left]);
            if(data.contains(nums[left])){
                return true;
            }
            data.add(nums[left]);
            left++;
        }
        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for(int i = 0 ; i < n - 1; i++){
            if(nums[i] == nums[i + 1]){
                return true;
            }
        }
        return false;
    }
}
