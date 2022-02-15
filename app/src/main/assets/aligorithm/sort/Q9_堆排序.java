package com.algorithm.demo.sort;

import com.algorithm.demo.PrintArray;

import java.util.Arrays;

/**
 * 堆排序（Heapsort）是指利用堆这种数据结构所设计的一种排序算法。堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：
 * 即子结点的键值或索引总是小于（或者大于）它的父节点。堆排序可以说是一种利用堆的概念来排序的选择排序。分为两种方法：
 * <p>
 * 大顶堆：每个节点的值都大于或等于其子节点的值，在堆排序算法中用于升序排列；
 * 小顶堆：每个节点的值都小于或等于其子节点的值，在堆排序算法中用于降序排列；
 * 堆排序的平均时间复杂度为 Ο(nlogn)。
 * <p>
 * 1. 算法步骤
 * 创建一个堆 H[0……n-1]；
 * <p>
 * 把堆首（最大值）和堆尾互换；
 * <p>
 * 把堆的尺寸缩小 1，并调用 shift_down(0)，目的是把新的数组顶端数据调整到相应位置；
 * <p>
 * 重复步骤 2，直到堆的尺寸为 1。
 */
public class Q9_堆排序 {

    public static void main(String[] args) {
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

        int len = arr.length;
        //构建一个最大堆
        buildMaxHeap(arr, len);

        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            len--;
            heapify(arr, 0, len);
        }
        return arr;
    }

    private static void buildMaxHeap(int[] arr, int len) {
        //最后一个非叶子节点在length/2-1 的位置，将它和自己的叶节点进行比较并交换(int) Math.floor(len / 2)
        for (int i = (len / 2 - 1); i >= 0; i--) {
            heapify(arr, i, len);
        }
    }

    /**
     * 堆的调整
     *
     * @param arr
     * @param i   要调整的根节点 根节点最大，和左子节点、右子节点比较
     * @param len
     */
    private static void heapify(int[] arr, int i, int len) {
        int left = 2 * i + 1;
//        int right = 2 * i + 2;
        int right = 2 * (i + 1);
        int largest = i;//保存最大的元素的索引
        //比较左子节点与父节点大小
        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }
        //比较右子节点与父节点大小
        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);//交换位置
            heapify(arr, largest, len);//重新进行堆调整
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
