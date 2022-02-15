package com.algorithm.demo.tree;

/**
 * 155 · 二叉树的最小深度
 * 题目题解笔记讨论排名
 * 描述
 * 给定一个二叉树，找出其最小深度。
 * 二叉树的最小深度为根节点到最近叶子节点的最短路径上的节点数量。
 * 样例
 * 样例 1:
 * 输入: {}
 * 输出: 0
 * 样例 2:
 * 输入:  {1,#,2,3}
 * 输出: 3
 * 解释:
 * 1
 * \
 * 2
 * /
 * 3
 * 它将被序列化为 {1,#,2,3}
 */
public class 二叉树的最小深度 {

    public static void main(String[] args) {

    }

    /**
     * @param root: The root of binary tree
     * @return: An integer
     */
    public int minDepth(TreeNode root) {
        // write your code here
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0 || right == 0) {
            return left + right + 1;
        }
        return Math.min(left, right) + 1;
    }

    /**
     * 方法一：深度优先搜索
     * 首先可以想到使用深度优先搜索的方法，遍历整棵树，记录最小深度。
     * 对于每一个非叶子节点，我们只需要分别计算其左右子树的最小叶子节点深度。这样就将一个大问题转化为了小问题，可以递归地解决该问题。
     * 时间复杂度：O(N)O(N)，其中 NN 是树的节点数。对每个节点访问一次。
     * 空间复杂度：O(H)O(H)，其中 HH 是树的高度。空间复杂度主要取决于递归时栈空间的开销，最坏情况下，树呈现链状，空间复杂度为 O(N)O(N)。平均情况下树的高度与节点数的对数正相关，空间复杂度为 O(\log N)O(logN)。
     *
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null || root.right == null) {
            return 1;
        }
        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth2(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth2(root.right), min_depth);
        }
        return min_depth + 1;
    }

}
