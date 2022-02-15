package com.algorithm.demo.sort;

import com.algorithm.demo.PrintArray;

/**
 * 希尔排序，也称递减增量排序算法，是插入排序的一种更高效的改进版本。但希尔排序是非稳定排序算法。
 * <p>
 * 希尔排序是基于插入排序的以下两点性质而提出改进方法的：
 * <p>
 * 插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率；
 * 但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位；
 * 希尔排序的基本思想是：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列中的记录"基本有序"时，再对全体记录进行依次直接插入排序。
 * <p>
 * 1. 算法步骤
 * 选择一个增量序列 t1，t2，……，tk，其中 ti > tj, tk = 1；
 * <p>
 * 按增量序列个数 k，对序列进行 k 趟排序；
 * <p>
 * 每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，分别对各子表进行直接插入排序。仅增量因子为 1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 */
public class Q4_希尔排序 {

    public static void main(String[] args) {
        int[] arr = {3, 5, 2, 4, 3, 7, 32, 12};
        PrintArray.printBefore(arr);
        arr = shellSort2(arr);
        PrintArray.printAfter(arr);
    }

    public static int[] shellSort(int[] arr) {
        int length = arr.length;
        //组内待排序的数据
        int currentValue;
        for (int step = length / 2; step >= 1; step /= 2) {
            for (int i = step; i < length; i++) {
                currentValue = arr[i];
                int currentIndex = i - step;
                while (currentIndex >= 0 && arr[currentIndex] > currentValue) {
                    arr[currentIndex + step] = arr[currentIndex];
                    currentIndex -= step;
                }
                arr[currentIndex + step] = currentValue;
            }
        }
        return arr;
    }

    public static int[] shellSort2(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int len = array.length;
        //增量序列
        int gap = len / 2;
        //组内待排序的数据
        int currentValue;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                currentValue = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > currentValue) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex-=gap;
                }
                array[preIndex + gap] = currentValue;
            }
            gap = gap / 2;
        }
        return array;
    }

}
