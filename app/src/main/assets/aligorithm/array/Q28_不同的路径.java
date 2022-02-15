package com.algorithm.demo.array;


import com.algorithm.demo.PrintArray;

/**
 * 114. 不同的路径
 * 有一个机器人的位于一个 m × n 个网格左上角。
 * <p>
 * 机器人每一时刻只能向下或者向右移动一步。机器人试图达到网格的右下角。
 * <p>
 * 问有多少条不同的路径？
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入: n = 1, m = 3
 * 输出: 1
 * 解释: 只有一条通往目标位置的路径。
 * 样例 2:
 * <p>
 * 输入:  n = 3, m = 3
 * 输出: 6
 * 解释:
 * D : Down
 * R : Right
 * 1) DDRR
 * 2) DRDR
 * 3) DRRD
 * 4) RRDD
 * 5) RDRD
 * 6) RDDR
 * 注意事项
 * n和m均不超过100
 * 且答案保证在32位整数可表示范围内。
 */
public class Q28_不同的路径 {

    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int what = uniquePaths(m, n);
        System.out.println("what " + what);
    }

    /**
     * @param m: positive integer (1 <= m <= 100)
     * @param n: positive integer (1 <= n <= 100)
     * @return: An integer
     */
    public static int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j - 1] + dp[j];
                PrintArray.print(dp);
            }
            PrintArray.print(dp);
        }
        return dp[n - 1];
    }

}
