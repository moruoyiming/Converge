package com.algorithm.demo.tree;

/**
 * 469 · 等价二叉树
 * 题目题解笔记讨论排名
 * 描述
 * 检查两棵二叉树是否等价。等价的意思是说，首先两棵二叉树必须拥有相同的结构，并且每个对应位置上的节点上的数都相等。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入：{1,2,2,4}，{1,2,2,4}
 * 输出：true
 * 解释：
 * 1                   1
 * / \                 / \
 * 2   2   和          2   2
 * /                   /
 * 4                   4
 * <p>
 * 是相同的。
 * 样例 2:
 * <p>
 * 输入：{1,2,3,4}，{1,2,3,#,4}
 * 输出：false
 * 解释：
 * <p>
 * 1                  1
 * / \                / \
 * 2   3   和          2  3
 * /                        \
 * 4                          4
 * 不一样。
 */
public class 等价二叉树 {

    /**
     * @param a: the root of binary tree a.
     * @param b: the root of binary tree b.
     * @return: true if they are identical, or false.
     */
    public boolean isIdentical(TreeNode a, TreeNode b) {
        // write your code here
        if (a == null && b == null) {
            return true;
        }
        if (a != null && b != null) {
            return a.val == b.val && isIdentical(a.left, b.left)
                    && isIdentical(a.right, b.right);
        }
        return false;
    }


}
