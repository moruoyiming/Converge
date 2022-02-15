package com.algorithm.demo.interview;

import com.algorithm.demo.linkedlist.ListNode;

public class 链表倒数第n个节点 {

    /*
     * @param head: The first node of linked list.
     * @param n: An integer
     * @return: Nth to last node of a singly linked list.
     */
    public ListNode nthToLast(ListNode head, int n) {// 3  5  6  8  23
        if (head == null || n < 1) {
            return null;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        for (int i = 0; i < n - 1; i++) {
            if (p2 == null) {
                return null;
            }
            p2 = p2.next;
        }
        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

}
