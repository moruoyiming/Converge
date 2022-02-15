package com.algorithm.demo.tree;

/**
 * 175 · 翻转二叉树
 * 题目题解笔记讨论排名
 * 描述
 * 翻转一棵二叉树。左右子树交换。
 * 样例
 * 样例 1:
 * 输入: {1,3,#}
 * 输出: {1,#,3}
 * 解释:
 * 1    1
 * /  =>  \
 * 3        3
 */
public class 翻转二叉树 {

    public static void main(String[] args) {

    }

    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void invertBinaryTree(TreeNode root) {
        // write your code here
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertBinaryTree(root.left);
        invertBinaryTree(root.right);
    }

    public TreeNode invertTree(TreeNode root){
        if(root == null){
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

}
