package com.example.basics.algorithm;

public class 二分查找 {

    public static void main(String[] args) {
        //[1,4,4,5,7,7,8,9,9,10]，1
        int[] array = {1, 4, 4, 5, 7, 7, 8, 9, 9, 10};
        int x = binarySearch(array, 9);
        System.out.println(" binarySearch " + x);
    }

    /**
     * @param array: The integer array.
     * @param value: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public static int binarySearch(int[] array, int value) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (array[middle] > value) {
                right = middle - 1;
            } else if (array[middle] < value) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

}
