package com.algorithm.demo.剑指Offer;

/**
 * 数组中有一个数字出现的次数超过数组长度的一般，请找出这个数字。
 * 例如：输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超出数组长度的一半，因此输出2。
 */
public class Q39_数组中出现次数超过一半的数字 {

    /**
     * 解法一：这个数字就是统计学上的中位数，即长度为n的数组中第n/2大的数字。时间复杂度为O(n)的算法得到数组中任意第k大的数字。
     *
     * @param nums
     * @param length
     */
    public int moreThanHalfNum(int[] nums, int length) {
        if(CheckInvalidArray(nums,length)){
            return 0;
        }
        int middle = length >> 1;
        int start = 0 ;
        int end = length - 1;
//        int index = Partition();
        return end;
    }

    private boolean CheckInvalidArray(int[] nums, int length) {
        return true;
    }

}
