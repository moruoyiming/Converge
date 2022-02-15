package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.tree.TreeNode;

/**
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左、右子树的深度相差不超过1，那么它就是
 * 一颗平衡二叉树。
 */
public class 平衡二叉树 {

    /**
     * 在遍历每个节点的时候，调用函数TreeDepth得到它的左、右子树的深度。如果每个节点的左、右子树的深度相差都不超过1，
     * 那么他就是平衡二叉树。
     * 需要重复遍历节点多次
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        int diff = left - right;
        if (diff > 1 || diff < -1)
            return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public int TreeDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        return (left > right) ? (left + 1) : (right + 1);
    }

    /**
     * 如果我们用后续遍历的方式遍历二叉树的每个节点，那么在遍历到一个节点之前我们就已经遍历它的左、右子数。只要在遍历每个节点的时候记录它的深度(
     * 某一节点的深度等于它到叶子节点的路径的长度)，我们就可以一边遍历一边判断每个节点是不是平衡的。
     * 每个节点只遍历一次
     *
     * @param root
     * @return
     */
    public boolean isBalanced2(TreeNode root, int depth) {
        if (root == null) {
            depth = 0;
            return true;
        }
        int left = 0, right = 0;
        if (isBalanced2(root.left, left) && isBalanced2(root.right, right)) {
            int diff = left - right;
            if (diff <= 1 && diff >= -1) {
                depth = 1 + (left > right ? left : right);
                return true;
            }
        }
        return false;
    }

    public boolean IsBalanced(TreeNode root) {
        int depth = 0;
        return isBalanced2(root, depth);
    }


}
