package com.algorithm.demo.tree;

/**
 * 85 · 在二叉查找树中插入节点
 * 描述
 * 给定一棵二叉查找树和一个新的树节点，将节点插入到树中。
 * <p>
 * 你需要保证该树仍然是一棵二叉查找树。
 * 保证不会出现重复的值
 * 样例
 * 样例 1：
 * 输入：
 * tree = {}
 * node= 1
 * 输出：
 * {1}
 * 解释：
 * 在空树中插入一个点，应该插入为根节点。
 */
public class 二叉查找树中插入节点 {

    public static void main(String[] args) {

    }

    /*
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // write your code here
        if (root == null) {
            return node;
        }
        if (root.val > node.val) {
            root.left = insertNode(root.left, node);
        } else {
            root.right = insertNode(root.right, node);
        }
        return root;
    }
}
