package com.algorithm.demo.剑指Offer;

/**
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 * 例如：输入数组{1，2，4，7，11，15}和数字15.由于4+11=15,因此输出4和11.
 */
public class Q57_和为s的数字 {

    public boolean findNumbersWithSum(int[] data, int length, int sum, int left, int right) {
        boolean found = false;
        if (length < 1 || left == 0 || right == 0)
            return found;
        int ahead = length - 1;
        int behind = 0;
        while (ahead > behind) {
            int curSum = data[ahead] + data[behind];
            if (curSum == sum) {
                left = data[behind];
                right = data[ahead];
                found = true;
                break;
            } else if (curSum > sum) {
                ahead--;
            } else {
                behind++;
            }
        }
        return found;
    }
}
