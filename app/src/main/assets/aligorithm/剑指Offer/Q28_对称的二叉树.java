package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.tree.TreeNode;

/**
 * 实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 * <p>
 * 8
 * 6        6
 * 5     7  7      5
 * 先序遍历： 8   6   5   7   6  7  5
 * <p>
 * 根据前序遍历定义一种对称的遍历算法。
 * 前序遍历：      先遍历左子节点，在遍历右子节点。
 * 对称遍历：      先遍历右子节点，在遍历左子节点。
 */
public class Q28_对称的二叉树 {

    public boolean isSymmetrical(TreeNode root) {
        return isSymmetrical(root, root);
    }

    public boolean isSymmetrical(TreeNode rootA, TreeNode rootB) {
        if (rootA == null && rootB == null)
            return true;
        if (rootA == null || rootB == null)
            return false;
        if (rootA.val != rootB.val) {
            return false;
        }
        return isSymmetrical(rootA.left, rootB.right)
                && isSymmetrical(rootA.right, rootB.left);
    }
}
