package com.algorithm.demo.linkedlist;

import java.util.List;

/**
 * 489 · 链表化数组
 * 题目题解笔记讨论排名
 * 描述
 * 将一个数组变成链表
 * <p>
 * 样例
 * 例1:
 * <p>
 * 输入: [1,2,3,4],
 * 输出: 1->2->3->4->null.
 * 例2:
 * <p>
 * 输入: [1,2],
 * 输出: 1->2->null.
 */
public class Q23_链表化数组 {

    /*
     * @param nums: an integer array
     * @return: the first node of linked list
     */
    public ListNode toLinkedList(List<Integer> nums) {
        // write your code here
        if (nums.size() == 0) {
            return null;
        }
        ListNode head = null;
        ListNode p = null;
        for (Integer num : nums) {
            if (head == null) {
                head = new ListNode(num);
                p = head;
            } else {
                p.next = new ListNode(num);
                p = p.next;
            }
        }
        return head;
    }

}
