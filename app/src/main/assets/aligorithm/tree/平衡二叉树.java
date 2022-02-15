package com.algorithm.demo.tree;

/**
 * 93 · 平衡二叉树
 * 描述
 * 给定一个二叉树,确定它是高度平衡的。对于这个问题,一棵高度平衡的二叉树的定义是：
 * 一棵二叉树中每个节点的两个子树的深度相差不会超过1。
 * 样例 1：
 * <p>
 * 输入：
 * <p>
 * tree = {1,2,3}
 * 输出：
 * <p>
 * true
 * 解释：
 * <p>
 * 如下，是一个平衡的二叉树。
 * 1
 * / \
 * 2   3
 */
public class 平衡二叉树 {
    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced(TreeNode root) {
        // write your code here
        return maxDepth(root) != -1;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

}
