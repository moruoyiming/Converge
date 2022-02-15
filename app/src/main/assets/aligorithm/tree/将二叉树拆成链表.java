package com.algorithm.demo.tree;

/**
 * 453 · 将二叉树拆成链表
 * 题目题解笔记讨论排名
 * 描述
 * 将一棵二叉树按照前序遍历拆解成为一个 假链表。所谓的假链表是说，用二叉树的 right 指针，来表示链表中的 next 指针。
 * 不要忘记将左儿子标记为 null，否则你可能会得到空间溢出或是时间溢出。
 * 样例 1：
 * 输入：{1,2,5,3,4,#,6}
 * 输出：{1,#,2,#,3,#,4,#,5,#,6}
 * 解释：
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * <p>
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 */
public class 将二叉树拆成链表 {
    private TreeNode lastNode = null;

    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        // write your code here
        if (root == null) {
            return;
        }
        if (lastNode != null) {
            lastNode.left = null;
            lastNode.right = root;
        }

        lastNode = root;
        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);
    }
}
