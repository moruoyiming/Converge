package com.algorithm.demo.linkedlist;

/**
 * 225 · 在链表中找节点
 * 描述
 * 在链表中找值为 value 的节点，如果没有的话，返回空(null)。
 */
public class Q19_在链表中找节点 {

    public static void main(String[] args) {

    }

    /*
     * @param head: the head of linked list.
     * @param val: An integer.
     * @return: a linked node or null.
     */
    public ListNode findNode(ListNode head, int val) {
        for (ListNode node = head; node != null; node = node.next) {
            if (node.val == val) {
                return node;
            }
        }
        return null;
    }

}
