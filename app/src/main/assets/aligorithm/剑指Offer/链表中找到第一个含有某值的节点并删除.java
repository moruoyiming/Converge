package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.linkedlist.ListNode;

public class 链表中找到第一个含有某值的节点并删除 {

    public void removeNode(ListNode head, int val) {
        if (head == null) {
            return;
        }
        if (head.val == val) {
            head = head.next;
        } else {
            ListNode node = head;
            while (node.next != null && node.next.val != val) {
                node = node.next;
                if(node.next != null && node.next.val ==val){
                    node.next = node.next.next;
                }
            }
        }
    }

}
