package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.tree.TreeNode;

/**
 * 输入二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点(含根、叶节点)形成树的一条路径，
 * 最长路径的长度为树的深度.
 * 如果一棵树只有一个节点，那么它的深度为1。
 * 如果根节点只有左子树而没有右子树，那么树的深度应该其左子树的深度加1。
 * 如果根节点只有右子树而没有左子树，那么树的深度应该其右子树的深度加1。
 * 如果既有左子树又有右子树，那么该树的深度就是其左、 右子树深度的较大值再加1。
 */
public class Q55_二叉树的深度 {

    public int TreeDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        return (left > right) ? (left + 1) : (right + 1);
    }

}
