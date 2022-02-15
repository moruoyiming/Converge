package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.linkedlist.ListNode;

/**
 * 输入两个链表，找出它们的第一个公共节点。
 * 解法一：蛮力
 * 解法二：分别把两个链表的节点放在两个栈中，这样两个栈表的尾节点就位于两个栈的栈顶，接下来比较两个栈顶的节点是否相同。如果相同，
 * 则把栈顶弹出接着比较下一个栈顶，直到找到最后一个相同的节点。
 * 需要两个辅助栈。链表长度分别是m和n，空间复杂度O(m+n)。时间复杂度O(m+n).
 * 解法三：首先遍历两个链表得到长度，第二次遍历，较长的链表先走若干步，接着同时两个链表遍历，找到第一个相同的节点就是它们的第一个公共节点。
 * 不需要辅助栈。时间复杂度O(m+n).
 */
public class Q52_两个链表的第一个公共节点 {

    public ListNode findFirstCommonNode(ListNode headA, ListNode headB) {
        int lengthA = getListLength(headA);
        int lengthB = getListLength(headB);
        int lengthDif = lengthA - lengthB;
        ListNode headLong = headA;
        ListNode headShort = headB;
        if (lengthB > lengthA) {
            headLong = headB;
            headShort = headA;
            lengthDif = lengthB - lengthA;
        }
        //先在长链表上走几步，再同时在两个链表上遍历
        for (int i = 0; i < lengthDif; ++i) {
            headLong = headLong.next;
        }
        while (headLong != null && headShort != null && headLong != headShort) {
            headLong = headLong.next;
            headShort = headShort.next;
        }
        //得到第一个公共节点
        ListNode firstCommonNode = headLong;
        return firstCommonNode;
    }

    private int getListLength(ListNode head) {
        int length = 0 ;
        ListNode pNode = head;
        while(pNode != null){
            ++length;
            pNode = pNode.next;
        }
        return length;
    }


}
