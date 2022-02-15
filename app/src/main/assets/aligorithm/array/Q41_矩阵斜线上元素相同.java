package com.algorithm.demo.array;

/**
 * 260. 矩阵斜线上元素相同
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
 * 样例
 * 样例1：
 * 输入数据：[[1, 2, 3], [5, 1, 2], [6, 5, 1]]
 * 输出数据：true
 * 样例解释：每一条从左上到右下的斜线均是相同的元素，返回true。
 * <p>
 * 样例2：
 * 输入数据：[[1, 2, 3], [2, 1, 5], [6, 5, 1]]
 * 输出数据：false
 * 样例解释：第二条和第四条斜线是[2, 5]。这两条斜线不满足条件，返回false。
 * <p>
 * 注意事项
 * n的长度范围是: [1, 500]
 */
public class Q41_矩阵斜线上元素相同 {

    public static void main(String[] args) {
        int[][] matrix = new int[3][3];
        boolean what = judgeSame(matrix);
        System.out.println("  " + what);
    }

    /**
     * @param matrix: a matrix
     * @return: return true if same.
     */
    public static boolean judgeSame(int[][] matrix) {
        // write your code here.
        if (matrix == null
                || matrix.length == 0) {
            return true;
        }
        int max = matrix.length;
        int row = 0;
        int column = matrix.length - 2;
        while (row < max - 1 && column >= 0) {
            int num = matrix[row][column];
            for (int i = 1; i < max - row && i < max - column; i++) {
                int n = matrix[row + i][column + i];
                if (n != num) {
                    return false;
                }
            }
            if (column > 0) {
                column--;
            } else {
                row++;
            }
        }
        return true;
    }

}
