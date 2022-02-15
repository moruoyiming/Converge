package com.algorithm.demo.array;

/**
 * 309. 交叉数组
 * 给定两个相同长度的数组，通过取第一个数组的第一个元素，第二个数组的第一个元素，第一个数组的第二个元素...依此类推来交叉它们。返回新的交错数组。
 * 注意 ： 长度 ≤ 10000
 *
 * 样例
 * 输入:
 * [1,2]
 * [3,4]
 * 输出:
 * [1,3,2,4]
 */
public class Q9_交叉数组 {

    /**
     * 假设数组大小为n
     * 时间复杂度
     * O(n)
     * 空间复杂度
     * O(n)
     * @param A: the array A
     * @param B: the array B
     * @return: returns an alternate array of arrays A and B.
     */
    public int[] interleavedArray(int[] A, int[] B) {
        // Interleaved Array
        int n = A.length;
        int[] temp = new int[n*2];
        for (int i = 0; i < n; i++) {
            temp[i * 2] = A[i];
            temp[i * 2 + 1] = B[i];
        }
        return temp;
    }

    public int[] interleavedArray2(int[] A, int[] B){
        int[] temp = new int[A.length + B.length];
        for(int i = 0 ; i < A.length; i++){
            temp[i * 2] = A[i];
            temp[i * 2 + 1] = B[i];
        }
        return temp;
    }

}
