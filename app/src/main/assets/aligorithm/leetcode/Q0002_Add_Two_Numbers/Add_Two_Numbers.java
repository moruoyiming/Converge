package com.algorithm.demo.leetcode.Q0002_Add_Two_Numbers;

import com.algorithm.demo.linkedlist.ListNode;

public class Add_Two_Numbers {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        int n1 = 0;
        int n2 = 0;
        int carry = 0;
        ListNode current = head;
        while (l1 != null || l2 != null || carry != 0) {
            if (l1 == null) {
                n1 = 0;
            } else {
                n1 = l1.val;
                l1 = l1.next;
            }

            if (l2 == null) {
                n2 = 0;
            } else {
                n2 = l2.val;
                l2 = l2.next;
            }
            current.next = new ListNode((n1 + n2 + carry) % 10);
            current = current.next;
            carry = (n1 + n2 + carry) / 10;
        }
        return head.next;
    }

}
