package com.algorithm.demo.array;

import com.algorithm.demo.PrintArray;

import java.util.HashMap;
import java.util.List;

public class Q51_主元素 {

    /*
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public static int majorityNumber(List<Integer> nums) {
        // write your code here.
        HashMap<Integer, Integer> count = new HashMap();
        for (int i : nums) {
            if (count.containsKey(i)) {
                int v = count.get(i);
                count.replace(i, ++v);
            } else {
                count.put(i, 1);
            }
            if (count.get(i) > nums.size() / 2) {
                return i;
            }
        }
        return -1;
    }
}
