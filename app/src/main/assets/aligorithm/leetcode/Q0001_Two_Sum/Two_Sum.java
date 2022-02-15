package com.algorithm.demo.leetcode.Q0001_Two_Sum;

import com.algorithm.demo.PrintArray;

import java.util.HashMap;

public class Two_Sum {

    public static void main(String[] args) {
        int[] array = {2, 7, 11, 15};
        PrintArray.printAfter(twoSum(array, 9));
    }

    public static int[] twoSum(int[] array, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < array.length - 1; i++) {
            if (hashMap.containsKey(target - array[i])) {
                return new int[]{array[i], target - array[i]};
            }
            hashMap.put(array[i], i);
        }
        return new int[]{};
    }


}
