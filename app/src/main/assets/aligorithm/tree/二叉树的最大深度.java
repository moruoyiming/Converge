package com.algorithm.demo.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 97 · 二叉树的最大深度
 * 描述
 * 给定一个二叉树，找出其最大深度。
 * 最大深度是从根节点到叶节点的最长路径的节点数。
 * 最终答案不会超过 5000
 * 样例
 * 样例 1：
 * 输入：
 * tree = {}
 * 输出：
 * 0
 * 解释：
 * 空树的深度是0。
 * <p>
 * 解题思路
 * 思路
 * 这道题用DFS（深度优先搜索）来解答。我们知道，每个节点的深度与它左右子树的深度有关，且等于其左右子树最大深度值加上 1。
 * 递归设计
 * 递归条件（recursive case）: 对于当前节点root，我们求出左右子树的深度的最大值，再加1表示当前节点的深度，返回该数值，即为以root为根节点的树的最大深度。
 * 终止条件（base case）：当前节点为空时，认为树深为0。
 * 复杂度分析
 * 时间复杂度：O(n)，其中 n是节点的数量。我们每个节点只访问一次，因此时间复杂度为 O(n)。
 * 空间复杂度：考虑到递归使用调用栈（call stack）的情况。
 * 最坏情况：树完全不平衡。例如每个节点都只有左节点，此时将递归n 次，需要保持调用栈的存储为O(n)
 * 最好情况：树完全平衡。即树的高度为 log(n)，此时空间复杂度为 O(log(n))
 */
public class 二叉树的最大深度 {

    public static void main(String[] args) {

    }

    /**
     * @param root: The root of binary tree.
     * @return: An integer
     * 时间复杂度：O(n)O(n)，其中 nn 为二叉树节点的个数。每个节点在递归中只被遍历一次。
     * 空间复杂度：O(\textit{height})O(height)，其中 \textit{height}height 表示二叉树的高度。递归函数需要栈空间，
     * 而栈空间取决于递归的深度，因此空间复杂度等价于二叉树的高度。
     */
    public static int maxDepth(TreeNode root) {
        // write your code here
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 时间复杂度：O(n)O(n)，其中 nn 为二叉树的节点个数。与方法一同样的分析，每个节点只会被访问一次。
     * 空间复杂度：此方法空间的消耗取决于队列存储的元素数量，其在最坏情况下会达到 O(n)O(n)。
     *
     * @param root
     * @return
     */
    public static int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode temp = queue.poll();
                if (temp.left == null) {
                    queue.offer(temp.left);
                }
                if (temp.right == null) {
                    queue.offer(temp.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

}
