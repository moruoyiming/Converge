package com.algorithm.demo.linkedlist;

/**
 * 219. 在排序链表中插入一个节点
 * 在链表中插入一个节点。
 * <p>
 * 样例
 * Example 1:
 * <p>
 * Input: head = 1->4->6->8->null, val = 5
 * Output: 1->4->5->6->8->null
 * Example 2:
 * <p>
 * Input: head = 1->null, val = 2
 * Output: 1->2->null
 */
public class Q12_在排序链表中插入一个节点 {

    public static void main(String[] args) {

    }

    /**
     * @param head: The head of linked list.
     * @param val:  An integer.
     * @return: The head of new linked list.
     */
    public ListNode insertNode(ListNode head, int val) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode pre = sentinel;
        while (pre != null) {
            ListNode curr = pre.next;
            if (curr != null && curr.val < val) {
                pre = pre.next;
            } else {
                ListNode newNode = new ListNode(val);
                newNode.next = curr;
                pre.next = newNode;
                break;
            }
        }
        return sentinel.next;
    }


}
