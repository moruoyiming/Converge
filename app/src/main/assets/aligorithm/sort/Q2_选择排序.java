package com.algorithm.demo.sort;

import com.algorithm.demo.PrintArray;

/**
 * 选择排序是一种简单直观的排序算法，无论什么数据进去都是 O(n²) 的时间复杂度。所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间了吧。
 * <p>
 * 1. 算法步骤
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
 * <p>
 * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * <p>
 * 重复第二步，直到所有元素均排序完毕。
 */
public class Q2_选择排序 {

    public static int[] selectSort(int[] arr) {
        if (arr == null || arr.length == 0)
            return arr;
        int minIndex = 0;
        for (int i = 0; i < arr.length - 1; i++) { //只需要比较n-1次
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) { //从i+1开始比较，因为minIndex默认为i了，i就没必要比了。
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) { //如果minIndex不为i，说明找到了更小的值，交换之。
                swap(arr, i, minIndex);
            }
        }
        return arr;

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 2, 4, 3, 7, 32, 12};

        PrintArray.printBefore(arr);
        try {
            arr = selectSort(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintArray.printAfter(arr);
    }

}