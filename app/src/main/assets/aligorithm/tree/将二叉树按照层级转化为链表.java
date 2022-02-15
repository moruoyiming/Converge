package com.algorithm.demo.tree;

import com.algorithm.demo.linkedlist.ListNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 242 · 将二叉树按照层级转化为链表
 * 描述
 * 给一棵二叉树，设计一个算法为每一层的节点建立一个链表。也就是说，如果一棵二叉树有 D 层，那么你需要创建 D 条链表。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入: {1,2,3,4}
 * 输出: [1->null,2->3->null,4->null]
 * 解释:
 * 1
 * / \
 * 2   3
 * /
 * 4
 */
public class 将二叉树按照层级转化为链表 {

    public static void main(String[] args) {

    }

    /**
     * @param root the root of binary tree
     * @return a lists of linked list
     */
    public List<ListNode> binaryTreeToLists(TreeNode root) {
        // Write your code here
        List<ListNode> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        ListNode dummy = new ListNode(0);
        ListNode lastNode = null;
        while (!queue.isEmpty()) {
            dummy.next = null;
            lastNode = dummy;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                lastNode.next = new ListNode(head.val);
                lastNode = lastNode.next;
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            result.add(dummy.next);
        }
        return result;
    }


}
