package com.algorithm.demo.matrix;

/**
 * 770 · 最大数和最小数
 * 题目题解笔记讨论排名
 * 描述
 * 给定一个矩阵，返回矩阵中的最大数和最小数
 * 您需要返回一个整数数组array，该数组array[0]表示最大值，而数组array[1]表示最小值。
 * 样例
 * 样例 1:
 * Input :
 * [
 * [1,2,3],
 * [4,3,2],
 * [6,4,4]
 * ]
 * Output : [6,1]
 */
public class 最大数和最小数 {


    /**
     * @param matrix: an input matrix
     * @return: nums[0]: the maximum,nums[1]: the minimum
     */
    public int[] maxAndMin(int[][] matrix) {
        // write your code here
        int[] result = new int[2];
        if(matrix.length==0){
            return new int[0];
        }
        int n = matrix.length;
        int max = matrix[0][0];
        int min = matrix[0][0];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                }
            }
        }
        result[0] = max;
        result[1] = min;
        return result;
    }

}
