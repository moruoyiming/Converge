package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.linkedlist.ListNode;

/**
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点
 */
public class Q24_反转链表 {

    public ListNode reverseList(ListNode pHead) {
        ListNode pReversedHead = null;
        ListNode pNode = pHead;
        ListNode pPrev = null;
        while (pNode != null) {
            ListNode pNext = pNode.next;
            if (pNext != null) {
                pReversedHead = pNode;
            }
            pNode.next = pPrev;
            pPrev = pNode;
            pNode = pNext;
        }
        return pReversedHead;
    }

}
