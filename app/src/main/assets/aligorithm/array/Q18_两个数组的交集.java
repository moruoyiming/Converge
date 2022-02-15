package com.algorithm.demo.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Q18_两个数组的交集 {

    public static void main(String[] args) {
//        int[] a = {1, 2, 2, 1};
//        int[] b = {2, 2};

        int[] a = {4, 9, 5};
        int[] b = {9, 4, 9, 8, 4};
        method(a, b);

    }

    public static void method(int[] a, int[] b) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    set.add(a[i]);
                }
            }
        }
        System.out.println("交集为：");
        int[] nums = new int[set.size()];
        int i = 0;
        for (int n : set) {
            nums[i] = n;
            i++;
            System.out.println("    " + n);
        }
    }

    /**
     * 思路：数组排序，然后用两个指针分别遍历数组，如果两个指针指向的元素相等 就是其中一个交集，否则比较两个指针指向的元素的大小，较小的向前移动
     * 复杂度：时间复杂度O(mlogm+nlogn)，两数组快排时间复杂度分别是O(mlogm)、O(nlogn)，双指针遍历需要O(m+n)，复杂度取决于较大的O(mlogm+nlogn)。空间复杂度O(logm+logn)排序使用的额外空间
     * @param nums1
     * @param nums2
     * @return
     * // nums1 = [4,5,9]
     * // nums2 = [4,4,8,9,9]
     * // intersection = [4,9]
     *
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[length1 + length2];
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < length1 && index2 < length2) {
            int num1 = nums1[index], num2 = nums2[index2];
            if(num1 == num2){
                if(index == 0 || num1 != intersection[index - 1]){
                   intersection[index++] = num1;
                }
                index1++;
                index2++;
            }else if(num1 < num2){
                index1++;
            }else{
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }


}
