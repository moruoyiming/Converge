package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.linkedlist.ListNode;

import java.util.Stack;

/**
 * 遍历的顺序是从偷盗，可输出的顺序确实从尾到头。先进后出规则
 */
public class 从尾到头打印链表 {

    /**
     * 通过栈实现
     *
     * @param head
     */
    public void printListNode(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node);
            node = node.next;
        }
        while (!stack.empty()) {
            ListNode temp = stack.peek();
            System.out.println("node" + temp.val);
        }
    }

    /**
     * 通过递归
     * 递归的本质也是栈结构
     * 要实现反过来输出链表，每次访问到一个节点的时候，先递归输出他后面的节点，在输出该节点自身。
     *
     * @param head
     */
    public void printListNode2(ListNode head) {
        ListNode node = head;
        if (node != null) {
            if (node.next != null) {
                printListNode2(node.next);
            }
            System.out.println("node" + node.val);
        }
    }

}
