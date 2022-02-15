package com.algorithm.demo.剑指Offer;

/**
 * 找出数组中重复的数字
 * 在一个长度为n的数组里的所有数字都在0～n-1的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
 * 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。例如，如果输入长度为7的数组{2,3,1,0,2,5,3},
 * 那么对应的输出是重复的数字2或者3。
 */
public class Q3_数组中重复的数字 {

    public boolean duplicate(int[] array, int length) {
        if (array == null || length <= 0) {
            return false;
        }
        for (int i = 0; i < length; ++i) {
            if (array[i] < 0 || array[i] > length - 1)
                return false;
        }
        for (int i = 0; i < length; ++i) {
            while (array[i] != i) {
                if (array[i] == array[array[i]]) {
                    return true;
                }
                int temp = array[i];
                array[i] = array[temp];
                array[temp] = temp;
            }
        }
        return false;
    }

}
