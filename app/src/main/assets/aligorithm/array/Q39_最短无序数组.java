package com.algorithm.demo.array;

/**
 * 757. 最短无序数组
 * 给定一个整数数组，求出无序整数的最短子序列的长度。如果一组整数既不递减也不递增，则称为无序。[提示：开始检查它是否递增/递减并返回0，否则检查是否存在无序的三元组。如果是，则返回3，否则返回0]
 * <p>
 * 样例
 * 样例 1:
 * 输入：[1,2,3,4,5,6]
 * 输出：0
 * 解释：一个递增数组。
 * 样例 2:
 * <p>
 * 输入：[1,2,1,2]
 * 输出：3
 * 解释：[1,2,1]
 */
public class Q39_最短无序数组 {

    public static void main(String[] args) {

//        int[] nums = {1, 2, 3, 4, 5, 6};
        int[] nums = {1, 2, 1, 2};
        int temp = shortestUnorderedArray(nums);
        System.out.println(" temp " + temp);

    }

    /**
     * @param arr: an array of integers
     * @return: the length of the shortest possible subsequence of integers that are unordered
     */
    public static int shortestUnorderedArray(int[] arr) {
        if (arr.length == 1) {
            return 0;
        }
        int ans = 1;
        int pos = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] != arr[i]) {//前一个元素不与当前元素相等
                if (arr[i - 1] > arr[i]) {//前一个元素 大于 当前元素
                    ans = -1;
                    pos = i;
                    break;
                }
            }
        }
        for (int i = pos + 1; i < arr.length; i++) {
            if (ans == -1) {
                if (arr[i] > arr[i - 1]) {
                    return 3;
                }
            } else {
                if (arr[i] < arr[i - 1]) {
                    return 3;
                }
            }
        }
        return 0;
    }
}
