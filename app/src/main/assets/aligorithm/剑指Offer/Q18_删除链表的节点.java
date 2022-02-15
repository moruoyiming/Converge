package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.linkedlist.ListNode;

/**
 * 在O(1)时间内删除链表节点
 * 给定单向链表的头指针和一个节点指针，定义一个函数在O(1)时间内删除该节点。
 * 在单向链表中删除一个节点，常规的做法无疑是从链表的头节点开始，顺序遍历查找要删除的节点，并在链表中删除该节点。
 * a -> b -> c -> d -> e -> h -> i -> j
 * 两种方法
 * 1:想删除节点i，可以从链表的头节点a开始顺序遍历，发现节点h的next指向要删除的节点i，于是可以把节点h的next执行i的下一个节点，即节点j。
 * 指针调整之后，可以安全的删除节点i并保证链表没有断开。由于要顺序查找，时间复杂度自然就是O(n)了。
 * 2:要删除节点的下一个节点。如果我们把下一个节点的内容复制到需要删除的节点上覆盖原有的内容，再把下一个节点删除，相当于把当前需要删除的节点删除。
 * 要删除节点i，先把i的下一个节点j的内容复制到i，然后把i的指针指向节点j的下一个节点。此时在删除节点j，其效果刚好是把节点i删除了。
 * 如果要删除的节点位于链表的尾部，那么它就没有下一个节点，此时仍然需要从链表的头节点开始，顺序遍历得到该节点的前序节点，并完成删除操作。
 * 如果链表中只有一个节点，而我们又要删除链表的头节点(也是尾节点),那么，此时我们在删除节点之后，还需要把链表的头节点设置为nullptr。
 */
public class Q18_删除链表的节点 {

    public void deleteNode(ListNode head, ListNode deleted) {
        if (head == null || deleted == null) {
            return;
        }
        //要删除的节点不是尾节点
        if (deleted.next != null) {
            ListNode next = deleted.next;
            deleted.val = next.val;
            deleted.next = next.next;
            next = null;
        }
        //链表中有一个节单，删除头节点(也是尾节点)
        else if (head == deleted) {
            deleted = null;
            head = null;
        }
        //链表中有多个节点，删除尾节点
        else {
            ListNode node = head;
            while (node.next != deleted) {
                node = node.next;
            }
            node.next = null;
            deleted = null;
        }

    }

}
