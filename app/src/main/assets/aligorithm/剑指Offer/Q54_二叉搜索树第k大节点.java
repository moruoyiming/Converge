package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.tree.TreeNode;

/**
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 * 例如，在图中的二叉搜索树中，按节点数值大小排序，第三大节点的值是4。
 * 如果按照中序遍历的顺序遍历一棵二叉搜索树，则遍历序列的数值是递增排序的。
 */
public class Q54_二叉搜索树第k大节点 {

    public TreeNode KthNode(TreeNode root, int k) {
        if (root == null || k == 0)
            return null;
        return KthNodeCore(root, k);
    }


    public TreeNode KthNodeCore(TreeNode root, int k) {
        TreeNode target = null;
        if (root.left != null)
            target = KthNodeCore(root.left, k);
        if (target == null)
            if (k == 1)
                target = root;
        k--;
        if (target == null && root.right != null)
            target = KthNodeCore(root.right, k);
        return target;
    }

}
