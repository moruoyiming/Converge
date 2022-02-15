package com.algorithm.demo.剑指Offer;

/**
 * 在一个mxn的棋盘的每一格都放油一个礼物，每个礼物都有一定的价值(价值大于0)。你可以从棋盘的左上角开始那个自理的礼物，
 * 并每次向左或者向下移动一格，知道到达棋盘的右下角。给定一个棋盘及其上面的礼物，请计算你最多能拿到多少价值的礼物？
 */
public class Q47_礼物的最大价值 {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 10, 3, 8},
                {12, 2, 9, 6},
                {5, 7, 4, 11},
                {3, 7, 16, 5}
        };
        System.out.println(getMaxValue(arr));
        System.out.println(getMaxValue2(arr));
        //1,12,5,7,7,16,5
    }

    /**
     * 利用循环的动态规划实现
     *
     * @param arr
     * @return
     */
    public static int getMaxValue(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int rows = arr.length;
        int cols = arr[0].length;
        int[][] maxValues = new int[rows][cols];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                int left = 0;
                int up = 0;
                if (i > 0)
                    up = maxValues[i - 1][j];
                if (j > 0)
                    left = maxValues[i][j - 1];
                maxValues[i][j] = Math.max(up, left) + arr[i][j];
            }
        }
        return maxValues[rows - 1][cols - 1];
    }

    /**
     * 使用循环实现
     *
     * @param arr
     * @return
     */
    public static int getMaxValue2(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int rows = arr.length;
        int cols = arr[0].length;
        int[] maxValue = new int[cols];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                int left = 0;
                int up = 0;
                if (i > 0)
                    up = maxValue[j];
                if (j > 0)
                    left = maxValue[j - 1];
                maxValue[j] = Math.max(up, left) + arr[i][j];
            }
        }
        return maxValue[cols - 1];
    }
}
