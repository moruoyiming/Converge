package com.algorithm.demo.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 69 · 二叉树的层次遍历
 * 描述
 * 给出一棵二叉树，返回其节点值的层次遍历（逐层从左往右访问）
 * 样例 1：
 * <p>
 * 输入：
 * <p>
 * tree = {1,2,3}
 * 输出：
 * <p>
 * [[1],[2,3]]
 * 解释：
 * <p>
 * 1
 * / \
 * 2   3
 * 它将被序列化为{1,2,3}
 */
public class 二叉树的层次遍历 {

    /**
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // write your code here
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                level.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            result.add(level);
        }
        return result;
    }

}
