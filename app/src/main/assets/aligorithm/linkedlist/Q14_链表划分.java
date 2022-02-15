package com.algorithm.demo.linkedlist;

/**
 * 96. 链表划分
 * 给定一个单链表和数值x，划分链表使得所有小于x的节点排在大于等于x的节点之前。
 * <p>
 * 你应该保留两部分内链表节点原有的相对顺序。
 * <p>
 * <p>
 * <p>
 * 样例
 * Example 1:
 * <p>
 * Input:  list = null, x = 0
 * Output: null
 * Explanation: The empty list Satisfy the conditions by itself.
 * Example 2:
 * <p>
 * Input:  list = 1->4->3->2->5->2->null, x = 3
 * Output: 1->2->2->4->3->5->null
 * Explanation:  keep the original relative order of the nodes in each of the two partitions.
 */
public class Q14_链表划分 {

    public static void main(String[] args) {

    }


    /**
     * @param head: The first node of linked list
     * @param x:    An integer
     * @return: A ListNode
     */
    public ListNode partition(ListNode head, int x) {
        // write your code here
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }
}
