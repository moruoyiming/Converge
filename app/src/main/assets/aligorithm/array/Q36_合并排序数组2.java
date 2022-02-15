package com.algorithm.demo.array;

/**
 * 算法复杂度：
 * 时间复杂度：
 * 平均时间复杂度：O ( nlogn )
 * 最好情况复杂度：O ( nlogn )
 * 最坏时间复杂度：O ( nlogn )
 * 平均、最好、最坏时间复杂度都相同，因此稳定性非常好！
 * 空间复杂度： O(n)
 */
public class Q36_合并排序数组2 {
    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7};
        int[] b = {2, 4, 6, 8};
        int[] size = mergeSortedArray(a, b);
        System.out.println("\n排序:");
        log(size);
    }

    /**
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    public static int[] mergeSortedArray(int[] A, int[] B) {
        int size = 0;
        int[] list = new int[A.length + B.length];
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                list[size++] = A[i++];
            } else {
                list[size++] = B[j++];
            }
        }
        while (i < A.length) {
            list[size++] = A[i++];
        }
        while (j < B.length) {
            list[size++] = B[j++];
        }
        return list;
    }

    private static void log(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.print(" " + A[i]);
        }
        System.out.println("");
    }
}
