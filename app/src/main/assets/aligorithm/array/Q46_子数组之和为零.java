package com.algorithm.demo.array;

import com.algorithm.demo.PrintArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Q46_子数组之和为零 {

    public static void main(String[] args) {
        int[] what = {-3, 1, 2, -3, 4};
        List<Integer> result = subarraySum(what);
        PrintArray.printObject(result);
    }

    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public static List<Integer> subarraySum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        List<Integer> list = new ArrayList<Integer>();
        HashMap<Integer, Integer> maps = new HashMap<Integer, Integer>();
        maps.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            //累加前缀和
            sum += nums[i];
            System.out.println("nums[i]=" + nums[i] + " sum = " + sum + " i = " + i);
            // 前缀和曾经出现，即这个区间的和为0
            if (maps.containsKey(sum)) {
                list.add(maps.get(sum) + 1);
                list.add(i);
                break;
            }
            //前缀和第一次出现，存入hash
            maps.put(sum, i);
        }
        return list;
    }

}
