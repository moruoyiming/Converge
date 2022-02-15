package com.algorithm.demo.array;

/**
 * 383 · 装最多水的容器
 * 描述
 * 给定 n 个非负整数 a1, a2, ..., an, 每个数代表了坐标中的一个点 (i, ai)。画 n 条垂直线，使得 i 垂直线的两个端点分别为(i, ai)和(i, 0)。找到两条线，使得其与 x 轴共同构成一个容器，以容纳最多水。
 * <p>
 * 容器不可倾斜。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入: [1, 3, 2]
 * 输出: 2
 * 解释:
 * 选择 a1, a2, 容量为 1 * 1 = 1
 * 选择 a1, a3, 容量为 1 * 2 = 2
 * 选择 a2, a3, 容量为 2 * 1 = 2
 * 样例 2:
 * <p>
 * 输入: [1, 3, 2, 2]
 * 输出: 4
 * 解释:
 * 选择 a1, a2, 容量为 1 * 1 = 1
 * 选择 a1, a3, 容量为 1 * 2 = 2
 * 选择 a1, a4, 容量为 1 * 3 = 3
 * 选择 a2, a3, 容量为 2 * 1 = 2
 * 选择 a2, a4, 容量为 2 * 2 = 4
 * 选择 a3, a4, 容量为 2 * 1 = 2
 */
public class Q62_盛水最多的容器 {

    public static int maxArea(int[] heights) {
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
        int max = 0;
        for (int i = 0, j = heights.length - 1; i < j; ) {
            int minHeight = heights[i] < heights[j] ? heights[i++] : heights[j--];
            int area = (j - i + 1) * minHeight;
            max = Math.max(max, area);
        }
        return max;
    }


}
