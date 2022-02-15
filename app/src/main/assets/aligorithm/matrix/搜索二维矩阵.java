package com.algorithm.demo.matrix;

/**
 * 28 · 搜索二维矩阵
 * 描述
 * 写出一个高效的算法来搜索 m × n矩阵中的值 target 。
 * <p>
 * 这个矩阵具有以下特性：
 * <p>
 * 每行中的整数从左到右是排序的。
 * 每行的第一个数大于上一行的最后一个整数。
 * 样例 2：
 * <p>
 * 输入：
 * <p>
 * 矩阵 = [
 * [1, 3, 5, 7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出：
 * <p>
 * true
 */
public class 搜索二维矩阵 {

    /**
     * @param matrix: matrix, a list of lists of integers
     * @param target: An integer
     * @return: a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int n = matrix.length, m = matrix[0].length;
        int start = 0, end = n * m - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (get(matrix, mid) < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (get(matrix, start) == target) {
            return true;
        }
        if (get(matrix, end) == target) {
            return true;
        }
        return false;
    }

    private int get(int[][] matrix, int index) {
        int x = index / matrix[0].length;
        int y = index % matrix[0].length;
        return matrix[x][y];
    }

}
