package com.example.basics.algorithm.array;

import static com.example.basics.algorithm.LogUtils.log;

/**
 * 合并排序数组
 * 合并两个排序的整数数组A和B变成一个新的数组。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入：[1, 2, 3]  3  [4,5]  2
 * 输出：[1,2,3,4,5]
 * 解释:
 * 经过合并新的数组为[1,2,3,4,5]
 * 样例 2:
 * <p>
 * 输入：[1,2,5] 3 [3,4] 2
 * 输出：[1,2,3,4,5]
 * 解释：
 * 经过合并新的数组为[1,2,3,4,5]
 * 注意事项
 * 你可以假设A具有足够的空间（A数组的大小大于或等于m+n）去添加B中的元素。
 */
public class 合并排序数组 {

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7};
        int[] b = {2, 4, 6, 8};
        int[] size = mergeSortedArray(a, 4, b, 4);
        System.out.println("\n排序:");
        log(size);
    }


    public static int[] mergeSortedArray(int[] nums1, int m, int[] nums2, int n) {
        int temp[] = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (k < m + n) {
            if (i >= m || j < n && nums1[i] > nums2[j]) {
                temp[k] = nums2[j];
                j++;
            } else if (j >= n || i < m && nums1[i] <= nums2[j]) {
                temp[k] = nums1[i];
                i++;
            }
            k++;
        }
        return temp;
    }

}
