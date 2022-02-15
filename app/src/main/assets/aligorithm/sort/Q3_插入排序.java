package com.algorithm.demo.sort;

import com.algorithm.demo.PrintArray;

import java.util.Arrays;

/**
 * 插入排序的代码实现虽然没有冒泡排序和选择排序那么简单粗暴，但它的原理应该是最容易理解的了，因为只要打过扑克牌的人都应该能够秒懂。插入排序是一种最简单直观的排序算法，它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 * <p>
 * 插入排序和冒泡排序一样，也有一种优化算法，叫做拆半插入。
 * <p>
 * 1. 算法步骤
 * 将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
 * <p>
 * 从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。（如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
 */
public class Q3_插入排序 {

    public static void main(String[] args) throws Exception {
        int[] arr = {3, 5, 2, 4, 3, 7, 32, 12};
        PrintArray.printBefore(arr);
        try {
            arr = sort(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintArray.printAfter(arr);
    }

    public static int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {
            // 记录要插入的数据
            int currentValue = arr[i];
            // 从已经排序的序列最右边的开始比较，找到比其小的数Stack
            int currentIndex = i;
            while (currentIndex > 0 && currentValue < arr[currentIndex - 1]) {
                arr[currentIndex] = arr[currentIndex - 1];
                currentIndex--;
            }
            // 存在比其小的数，插入
            if (currentIndex != i) {
                arr[currentIndex] = currentValue;
            }

        }
        return arr;
    }
}