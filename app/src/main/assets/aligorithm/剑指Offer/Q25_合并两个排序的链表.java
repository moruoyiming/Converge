package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.linkedlist.ListNode;

/**
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * 首先分析合并两个链表的过程。
 * 从合并两个链表的头节点开始。链表1的头节点的值小于链表2的头节点的值，因此链表1的头节点将合并后链表的头节点。
 * 继续合并两个链表中剩余的节点。在两个链表中剩下的节点依然是排序的，因此合并着两个链表的步骤和前面的步骤是一样的。
 * 还是比较两个头节点的值。此时链表2的头节点的值小于链表1的头节点的值，因此链表2的头节点的值将是合并剩余节点的到的链表的头节点。
 * 把这个节点和前面合并链表时得到的链表的尾节点链接起来。
 * 1 -> 3 -> 5 -> 7
 * 2 -> 4 -> 6 -> 8
 */
public class Q25_合并两个排序的链表 {

    /**
     * 递归实现思路
     * 当得到两个链表中值较小的头节点并把它连接到已经合并的链表之后，两个链表剩余的节点依然是排序的，因此合并的步骤和之前的步骤是一样的。
     * 可以定义递归函数完成合并过程。
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode merge(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null) {
            return pHead2;
        } else if (pHead2 == null) {
            return pHead1;
        }
        ListNode pMergeHead = null;
        if (pHead1.val < pHead2.val) {
            pMergeHead = pHead1;
            pMergeHead.next = merge(pHead1.next, pHead2);
        } else {
            pMergeHead = pHead2;
            pMergeHead.next = merge(pHead1, pHead2.next);
        }
        return pMergeHead;
    }


}
