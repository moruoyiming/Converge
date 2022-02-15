package com.algorithm.demo.array;

import java.util.Stack;

/**
 * 264 · 通用子数组个数
 * 题目题解笔记讨论排名
 * 描述
 * 给定一个由 2 或 4 组成的数组。如果一个数组的子数组 (子数组是数组中相邻的一组元素且不能为空) 符合以下条件，则称为“通用”:
 * <p>
 * 2 和 4 被连续分组(如[4, 2],[2, 4],[4, 4, 2, 2],[2, 2, 4, 4],[4, 4, 4, 2, 2, 2]等等)。
 * 子数组中 4 的个数等于子数组中 2 的个数。
 * 相同元素但位置不同的子数组视为不同，如数组[4, 2, 4, 2]中有两个[4, 2]子数组。
 * 你需要返回一个整数值，即给定数组中“通用”子数组的数量。
 * <p>
 * 1 ≤ |array| ≤ 10^51≤∣array∣≤10
 * 5
 * <p>
 * array[i] ∈ (2, 4)
 * <p>
 * 样例
 * 样例 1：
 * 输入样例：array = [4, 4, 2, 2, 4, 2]
 * 输出样例：4
 * 样例解释：匹配这两个条件的 4 个子数组包括：[4, 4, 2, 2]，[4,2]，[2,4]，[4,2]。注意有两个子数组[4,2]，分别在索引 1-2 和 4-5 中。
 */
public class Q59_通用子数组个数 {

    public static void main(String[] args) {

    }

    /**
     * @param array: An given array.
     * @return: Return the number of "universal" subarrays.
     */
    public static int subarrays(int[] array) {
        // write your code here.
        return countUniversal(2, array) + countUniversal(4, array);
    }
    public static int countUniversal(int left, int[] A){
        Stack<Integer> stack = new Stack<>();
        int cnt = 0;
        int n = A.length;

        for (int i = 0; i<n; i++ ){
            if (left == A[i]){
                stack.add(i);
            } else{
                if (!stack.isEmpty()){
                    cnt++;
                    stack.pop();
                }
                if (i<n-1 && A[i + 1] == left){
                    stack.clear();
                }
            }
        }

        return cnt;
    }
}
