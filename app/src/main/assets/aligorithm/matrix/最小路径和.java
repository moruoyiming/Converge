package com.algorithm.demo.matrix;

/**
 * 110 · 最小路径和
 * 题目题解笔记讨论排名
 * 描述
 * 给定一个只含非负整数的m*nm∗n网格，找到一条从左上角到右下角的可以使数字和最小的路径。
 * 你在同一时间只能向下或者向右移动一步
 * 样例 1：
 * 输入：
 * grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：
 * 7
 * 解释：
 * 路线为： 1 -> 3 -> 1 -> 1 -> 1
 */
public class 最小路径和 {

    public static void main(String[] args) {

    }

    /**
     * @param grid: a list of lists of integers
     * @return: An integer, minimizes the sum of all numbers along its path
     * DP动态规划
     * 这道题目要求在一个只含非负整数的m*n网格中，找到一条从左上角到右下角的可以使数字和最小的路径，且在同一时间只能向下或者向右移动一步。
     * 很容易得想到这个问题存在递推关系，因此可以用DP动态规划来实现。
     * 1.若输入的网络为空直接返回。
     * 2.DP,状态的定义：f[i][j]表示从(0,0)到(i,j)的最小数字和
     * 3.状态初始化
     * f[0][0] = grid[0][0];
     * f[i][0] = grid[i-1][0]+grid[i][0](0<i<m);
     * f[0][j] = grid[0][j-1]+grid[0][j](0<j<n);
     * 4.状态转移：f[i][j] = min{f[i-1][j],f[i][j-1]}+grid[i][j](0<i<m,0<j<n)
     * 5.最终的结果为：f[m-1][n-1];
     * <p>
     * 复杂度分析
     * n为网格的行数 m为网格的列数
     * 时间复杂度: O(n * m)
     * 二维dp遍历网络，需要两重循环，因此为O(n * m)
     * 空间复杂度: O(n * m)
     * 保存网络的grid数组和动态规划的f数组均为二维数组n*m;
     */
    public int minPathSum(int[][] grid) {
        // write your code here
        if (grid == null) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] f = new int[m][n];
        //初始化
        f[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            f[i][0] = f[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            f[0][j] = f[0][j - 1] + grid[0][j];
        }
        //状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + grid[i][j];
            }
        }
        return f[m - 1][n - 1];
    }

}
