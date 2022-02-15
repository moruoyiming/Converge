package com.algorithm.demo.剑指Offer;

/**
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 */
public class 二维数组中的查找 {

    public static void main(String[] args) {
        int[][] nums = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        boolean result = find(nums, nums.length, nums[0].length, 17);
        System.out.println("result=" + result);
    }


    /**
     * 首先选取数组中右上角的数字。如果该数字等于要查找的数字，则查找过程结束。
     * 如果该数字大于要查找的数字，则剔除这个数字所在的列。
     * 如果该数字小于要查找的数字，则剔除这个数字所在的行。
     *
     * @param matrix
     * @param rows
     * @param columns
     * @param number
     * @return
     */
    public static boolean find(int[][] matrix, int rows, int columns, int number) {
        boolean found = false;
        if (matrix != null && rows > 0 && columns > 0) {
            int row = 0;
            int column = columns - 1;
            while (row < rows && column >= 0) {
                if (matrix[row][column] == number) {
                    found = true;
                    break;
                } else if (matrix[row][column] > number) {
                    --column;
                } else {
                    ++row;
                }
            }
        }
        return found;
    }


}
