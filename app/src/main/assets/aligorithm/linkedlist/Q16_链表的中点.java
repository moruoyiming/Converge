package com.algorithm.demo.linkedlist;

/**
 * 228 · 链表的中点
 * 描述
 * 找链表的中点，并返回这个节点。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入: 1->2->3
 * 输出: 2
 * 样例解释: 返回中间节点
 */
public class Q16_链表的中点 {

    public static void main(String[] args) {

    }

    /**
     * @param head: the head of linked list.
     * @return: a middle node of the linked list
     */
    public ListNode middleNode(ListNode head) {
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        while (fastPointer != null) {
            if (fastPointer.next == null || fastPointer.next.next == null) {
                return slowPointer;
            }
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        return head;
    }
}
