package com.algorithm.demo.sort;

import com.algorithm.demo.PrintArray;

import java.util.Arrays;

/**
 * 归并排序（Merge sort）是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * <p>
 * 作为一种典型的分而治之思想的算法应用，归并排序的实现由两种方法：
 * <p>
 * 自上而下的递归（所有递归的方法都可以用迭代重写，所以就有了第 2 种方法）；
 * 自下而上的迭代；
 * 在《数据结构与算法 JavaScript 描述》中，作者给出了自下而上的迭代方法。但是对于递归法，作者却认为：
 * <p>
 * However, it is not possible to do so in JavaScript, as the recursion goes too deep for the language to handle.
 * <p>
 * 然而，在 JavaScript 中这种方式不太可行，因为这个算法的递归深度对它来讲太深了。
 * <p>
 * 说实话，我不太理解这句话。意思是 JavaScript 编译器内存太小，递归太深容易造成内存溢出吗？还望有大神能够指教。
 * <p>
 * 和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多，因为始终都是 O(nlogn) 的时间复杂度。代价是需要额外的内存空间。
 * <p>
 * 2. 算法步骤
 * 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列；
 * <p>
 * 设定两个指针，最初位置分别为两个已经排序序列的起始位置；
 * <p>
 * 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置；
 * <p>
 * 重复步骤 3 直到某一指针达到序列尾；
 * <p>
 * 将另一序列剩下的所有元素直接复制到合并序列尾。
 */
public class Q8_归并排序 {

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

        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2);

        int[] left = Arrays.copyOfRange(arr, 0, middle);//排除middle元素
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(sort(left), sort(right));
    }

    protected static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }


}
