package com.algorithm.demo.geek;

/**
 * 给定 n 个非负整数 a1, a2, ..., an, 每个数代表了坐标中的一个点 (i, ai)。画 n 条垂直线，使得 i 垂直线的两个端点分别为(i, ai)和(i, 0)。
 * 找到两条线，使得其与 x 轴共同构成一个容器，以容纳最多水。
 */
public class Q4_盛水最多的容器 {

    public static void main(String[] args) {

    }


    /**
     * @param heights: a vector of integers
     * @return: an integer
     * 思路：
     * 一维数组的坐标变换！i , j
     * 1.枚举：left bar x ， right bar y (x - y) * height_diff
     * 时间复杂度 O(n^2)
     * 2.左右边界i，j，向中间收敛.左右夹逼。
     * O(n)
     */
    public static int maxArea(int[] heights) {
        // write your code here
        int max = 0;
        for (int i = 0; i < heights.length - 1; ++i) {
            for (int j = i + 1; j < heights.length; ++j) {
                int area = (j - i) * Math.min(heights[i], heights[j]);
                max = Math.max(max, area);
            }
        }
        return max;
    }


    public static int maxArea2(int[] heights) {
        // write your code here
        int max = 0;
        for (int i = 0, j = heights.length - 1; i < j; ) {
            int minHeight = heights[i] < heights[j] ? heights[i++] : heights[j--];
            int area = (j - i + 1) * minHeight;
            max = Math.max(max, area);
        }
        return max;
    }


}
