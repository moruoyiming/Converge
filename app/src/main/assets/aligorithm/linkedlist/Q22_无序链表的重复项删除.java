package com.algorithm.demo.linkedlist;

import java.util.HashSet;

/**
 * 217 · 无序链表的重复项删除
 * 题目题解笔记讨论排名
 * 描述
 * 设计一种方法，从无序链表中删除重复项。
 * <p>
 * 样例
 * 样例 1：
 * <p>
 * 输入：1->2->1->3->3->5->6->3->null
 * 输出：1->2->3->5->6->null
 */
public class Q22_无序链表的重复项删除 {

    public static void main(String[] args) {

    }

    /**
     * @param head: The first node of linked list.
     * @return: Head node.
     */
    public ListNode removeDuplicates(ListNode head) {
        // write your code here
        HashSet<Integer> hash = new HashSet<>();

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while (head.next != null) {
            if (hash.contains(head.next.val)) {
                head.next = head.next.next;
            } else {
                hash.add(head.next.val);
                head = head.next;
            }
        }
        return dummy.next;
    }

}
