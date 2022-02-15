package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.linkedlist.ListNode;

public class 链表尾节点增加元素 {


    public void AddToTail(ListNode head, int val){
        ListNode newNode = new ListNode(val);
        if(head == null){
            head = newNode;
        }else{
            ListNode node = head;
            while(node.next != null){
                node = node.next;
            }
            node.next = newNode;
        }
    }

}
