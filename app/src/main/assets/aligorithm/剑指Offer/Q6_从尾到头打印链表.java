package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.linkedlist.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 题目描述
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 * <p>
 * 题目解析
 * 链表都是从头读到尾依次访问每个节点，题目要求我们 从尾到头 打印链表，这种逆序的操作很显然可以考虑使用
 * <p>
 * 具有 先入后出 特点的数据结构，那就是 栈。
 * <p>
 * 具体操作如下：
 * <p>
 * 入栈： 遍历链表，将各节点值 push 入栈。
 * <p>
 * 出栈： 将各个节点值 pop 出栈，存储于数组并返回。
 */
public class Q6_从尾到头打印链表 {

    /**
     * 时间复杂度为 O(N)，入栈和出栈共使用 O(N) 时间
     * 空间复杂度为 O(N)，辅助栈 stack 和数组 res 共使用 O(N) 的额外空间。
     *
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        Deque<Integer> stack = new ArrayDeque<>();
        ListNode curNode = head;
        while (curNode != null) {
            stack.add(curNode.val);
            curNode = curNode.next;
        }
        int size = stack.size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = stack.removeLast();
        }
        return res;
    }

    public int[] reversePrint2(ListNode head){
        Deque<Integer> stack = new ArrayDeque<>();
        ListNode curNode = head;
        while (curNode != null){
            stack.add(curNode.val);
            curNode = curNode.next;
        }
        int size = stack.size();
        int[] res = new int[size];
        for(int i = 0 ; i < size ; i++){
            res[i] = stack.removeLast();
        }
        return res;
    }

}
