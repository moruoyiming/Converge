package com.algorithm.demo.array;

public class Q56_只出现一次的数字 {

    public static void main(String[] args) {

    }


    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }


}
