package com.algorithm.demo.matrix;

/**
 * 矩阵斜线上元素相同
 * 描述
 * 给定一个 n * n 的矩阵，如果每一条从左上到右下的斜线上的数值相同，返回 true， 否则返回false。
 * 例如：
 * 1，2，3
 * 5，1，2
 * 6，5，1
 * 应该返回 true。
 * <p>
 * 但
 * 1，2，3
 * 2，1，5
 * 6，5，1
 * 应该返回 false。
 * <p>
 * n的长度范围是: [1, 500]
 * <p>
 * 样例
 * 样例1：
 * 输入数据：[[1, 2, 3], [5, 1, 2], [6, 5, 1]]
 * 输出数据：true
 * 样例解释：每一条从左上到右下的斜线均是相同的元素，返回true。
 */
public class 矩阵斜线上元素相同 {

    public static void main(String[] args) {

    }

    /**
     * @param matrix: a matrix
     * @return: return true if same.
     */
    public boolean judgeSame(int[][] matrix) {
        // write your code here.
        int n = matrix.length;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
