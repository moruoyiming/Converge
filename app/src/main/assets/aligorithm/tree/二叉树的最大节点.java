package com.algorithm.demo.tree;

/**
 * 632 · 二叉树的最大节点
 * 在二叉树中寻找值最大的节点并返回。
 * 样例1:
 * 输入:
 * {1,-5,3,1,2,-4,-5}
 * 输出: 3
 * 说明:
 * 这棵树如下所示：
 * 1
 * /   \
 * -5     3
 * / \   /  \
 * 1   2 -4  -5
 * 样例 2
 * 输入:
 * {10,-5,2,0,3,-4,-5}
 * 输出: 10
 * 说明:
 * 这棵树如下所示：
 * 10
 * /   \
 * -5     2
 * / \   /  \
 * 0   3 -4  -5
 */
public class 二叉树的最大节点 {


    /*
     * @param root: the root of tree
     * @return: the max node
     */
    public TreeNode maxNode(TreeNode root) {
        // write your code here
        if (root == null) {
            return null;
        }
        TreeNode left = maxNode(root.left);
        TreeNode right = maxNode(root.right);
        return max(root, max(left, right));
    }

    public TreeNode max(TreeNode a, TreeNode b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (a.val > b.val) {
            return a;
        }
        return b;
    }

}
