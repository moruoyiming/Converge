package com.algorithm.demo.geek;

import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class Q12_柱状图中最大的矩形 {
    /**
     * 暴力求解：O（n^3）
     * <p>
     * <p>
     * 栈求解：枚举每个柱状时，将数据存放到栈中，实现顺序排列。O(1)复杂度获取到某一数据。
     * 栈是从小到大排列的。
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] > heights[i]){
                int height = heights[stack.peek()];
                stack.pop();
                int width = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            int height = heights[stack.peek()];
            stack.pop();
            int width = heights.length - stack.peek() - 1;
            maxArea = Math.max(maxArea, height * width);
        }
        return maxArea;
    }
}
