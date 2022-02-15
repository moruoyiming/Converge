package com.algorithm.demo.array;


import com.algorithm.demo.PrintArray;

/**
 * 172. 删除元素
 * 给定一个数组和一个值，在原地删除与值相同的数字，返回新数组的长度。
 * <p>
 * 元素的顺序可以改变，并且对新的数组不会有影响。
 * <p>
 * 样例
 * Example 1:
 * Input: [], value = 0
 * Output: 0
 * <p>
 * <p>
 * Example 2:
 * Input:  [0,4,4,0,0,2,4,4], value = 4
 * Output: 4
 * <p>
 * Explanation:
 * the array after remove is [0,0,0,2]
 */
public class Q14_移除元素 {

    public static void main(String[] args) {
        int[] nums = {0, 4, 4, 0, 0, 2, 4, 4};
        int what = removeElement2(nums, 4);
        System.out.println(" length " + what);
    }

    /*
     * @param A: A list of integers
     * @param elem: An integer
     * @return: The new length after remove
     */
    public static int removeElement(int[] A, int elem) {

        if (A == null || A.length == 0) {
            return 0;
        }

        int i = 0;
        //Input:  [0,4,4,0,0,2,4,4], value = 4
        for (int j = 0; j < A.length; j++) {
            if (A[j] != elem) {
                A[i] = A[j];
                i++;
            }
        }
        PrintArray.print(A);
        return i;
    }

    /*
     * @param A: A list of integers
     * @param elem: An integer
     * @return: The new length after remove
     */
    public static int removeElement2(int[] A, int elem) {

        if (A == null || A.length == 0) {
            return 0;
        }
        int i = 0;
        int n = A.length;
        while (i < n) {
            if (A[i] == elem) {
                A[i] = A[n - 1];
                n--;
            } else {
                i++;
            }
            PrintArray.print(A);
        }
        PrintArray.print(A);
        return i;
    }

}
//当要删除的元素很少时会发生什么？
//        解决方案
//
//        方法一：双指针
//
//        思路
//
//        既然问题要求我们就地删除给定值的所有元素，我们就必须用O(1)
//        O(1) 的额外空间来处理它。如何解决？我们可以保留两个指针i 和 j，其中 i 是慢指针，j 是快指针。
//
//        算法
//
//        当 nums[j]与给定的值相等时，递增 j以跳过该元素。只要 nums[j]≠val，我们就复制
//        nums[j] 到 nums[i] 并同时递增两个索引。重复这一过程，直到 j 到达数组的末尾，该数组的新长度为 i。
//
//        该解法与 删除排序数组中的重复项 的解法十分相似。
//
//        Java
//
//public int removeElement(int[] nums, int val) {
//        int i = 0;
//        for (int j = 0; j < nums.length; j++) {
//        if (nums[j] != val) {
//        nums[i] = nums[j];
//        i++;
//        }
//        }
//        return i;
//        }
//        复杂度分析
//
//        时间复杂度：
//        O(n)，假设数组总共有 n 个元素，i 和 j 至少遍历 2n 步。
//        空间复杂度：
//        O(1)。
//        方法二：双指针 —— 当要删除的元素很少时
//
//        思路
//
//        现在考虑数组包含很少的要删除的元素的情况。例如，
//
//        num=[1，2，3，5，4]，Val=4。之前的算法会对前四个元素做不必要的复制操作。另一个例子是
//
//        num=[4，1，2，3，5]，Val=4。似乎没有必要将 [1，2，3，5] 这几个元素左移一步，因为问题描述中提到元素的顺序可以更改。
//
//        算法
//
//        当我们遇到 nums[i]=val 时，我们可以将当前元素与最后一个元素进行交换，并释放最后一个元素。这实际上使数组的大小减少了 1。
//
//        请注意，被交换的最后一个元素可能是您想要移除的值。但是不要担心，在下一次迭代中，我们仍然会检查这个元素。
//
//        Java
//
//public int removeElement(int[] nums, int val) {
//        int i = 0;
//        int n = nums.length;
//        while (i < n) {
//        if (nums[i] == val) {
//        nums[i] = nums[n - 1];
//        // reduce array size by one
//        n--;
//        } else {
//        i++;
//        }
//        }
//        return n;
//        }
//        复杂度分析
//
//        时间复杂度：
//        O(n)，i 和 n 最多遍历 n 步。在这个方法中，赋值操作的次数等于要删除的元素的数量。因此，如果要移除的元素很少，效率会更高。
//        空间复杂度：
//        O(1)。
