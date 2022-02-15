package com.algorithm.demo.tree;

/**
 * 1126 · 合并两棵二叉树
 * 题目题解笔记讨论排名
 * 描述
 * 给出两棵二叉树，当你用其中一棵覆盖另一棵时，两棵树的一些节点会发生重叠，而其他节点则不会重叠。
 * 您需要将它们合并到一棵新的二叉树中。 合并的规则是如果两个节点重叠，则将节点值加起来作为合并节点的新值。 否则，非空的节点将用作新树的节点。
 * 合并过程必须从两个树的根节点开始。
 * 样例
 * 输入:
 * {1,3,2,5}
 * {2,1,3,#,4,#,7}
 * 输出：
 * {3,4,5,5,4,#,7}
 * 解释：
 * 	树.md 1                     树.md 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 *
 * 合并的树:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 */
public class 合并两棵二叉树 {

    /**
     * @param t1: the root of the first tree
     * @param t2: the root of the second tree
     * @return: the new binary tree after merge
     * 比较基础的二叉树遍历。 类似于树的先序遍历，合并的时候先合并根节点，之后递归的合并左右子树。
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        // Write your code here
        if(t1 == null) return t2;
        if(t2 == null) return t1;
        TreeNode t3 = new TreeNode(t1.val + t2.val);
        t3.left = mergeTrees(t1.left , t2.left);
        t3.right = mergeTrees(t1.right , t2.right);
        return t3;
    }

}
