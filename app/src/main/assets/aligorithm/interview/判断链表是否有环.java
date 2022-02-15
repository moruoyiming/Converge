package com.algorithm.demo.interview;

import com.algorithm.demo.linkedlist.ListNode;

public class 判断链表是否有环 {

    public static void main(String[] args) {

    }

    public static Boolean hasCycle(ListNode head) {
        if(head==null || head.next==null){
            return false;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while(fast!=slow){
            if(fast ==null || fast.next ==null){
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }
}
