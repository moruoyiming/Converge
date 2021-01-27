package com.example.basics.algorithm;

public class 二分查找 {

    public static void main(String[] args) {
        //[1,4,4,5,7,7,8,9,9,10]，1
        int[] array = {3, 4, 5, 8, 8, 8, 8, 10, 13, 14};
        int x = binarySearch(array, 8);
        System.out.println(" binarySearch " + x);
    }

    /**
     * @param nums:  The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            System.out.println("middle" + middle + " left " + left + " (right - left) >> 1)  " + (((right - left) >> 1)));
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {//当有重复数据时
                while (middle >= 0) {
                    if (nums[middle] != target) {
                        break;
                    }
                    middle--;
                }
                if (middle <= -1) {
                    return 0;
                }
                return middle + 1;
            }
        }
        return -1;
    }

}
