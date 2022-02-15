package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.linkedlist.ListNode;

/**
 * 输入一个链表，输出该链表中倒数第k个节点。
 * 解法一：假设整个链表有n个节点，那么倒数第k个节点就是从头节点开始的第n-k+1个节点。如果得到链表中节点的个数n，只要从头节点开始往后走n-k+1就可以了。
 * 如何得到节点书n？只需要从头开始遍历链表，每经过一个节点，计数器加1就行了。需遍历两次链表。
 * <p>
 * 解法二：为了实现只遍历一次就能找到倒数第k个节点，可以定义两个指针。第一个指针从链表的头指针开始遍历向前走k-1步，第二个指针保持不懂；
 * 从第k步开始，第二个指针也开始从链表的头指针开始遍历。由于两个指针的距离保持在k-1，当第一个指针到达链表的尾节点时，第二个指针正好指向
 * 倒数第k个节点。
 */
public class Q22_链表中倒数第k个节点 {

    public ListNode findKthToTail(ListNode head, int k) {
        if (head == null || k < 1) {
            return null;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        for (int i = 0; i < k - 1; i++) {
            if (p2 == null) {
                return null;
            }
            p2 = p2.next;
        }
        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

}
