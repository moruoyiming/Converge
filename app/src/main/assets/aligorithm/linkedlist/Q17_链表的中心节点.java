package com.algorithm.demo.linkedlist;

/**
 * 1609 · 链表的中间结点
 * <p>
 * 描述
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 * <p>
 * 如果有两个中间结点，则返回第二个中间结点。
 * 样例
 * 样例 1:
 * <p>
 * 输入：1->2->3->4->5->null
 * 输出：3->4->5->null
 */
public class Q17_链表的中心节点 {

    /**
     * @param head: the head node
     * @return: the middle node
     */
    public ListNode middleNode(ListNode head) {
        // write your code here.
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
