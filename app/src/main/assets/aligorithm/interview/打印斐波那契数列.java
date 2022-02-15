package com.algorithm.demo.interview;

public class 打印斐波那契数列 {

    public static void main(String[] args) {
        what(10);
    }

    public static void what(int n) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                nums[0] = 0;
            } else if (i == 1) {
                nums[1] = 1;
            } else {
                nums[i] = nums[i - 2] + nums[i - 1];
            }
            System.out.print(" nums[i] " + nums[i]);
        }
    }
}
