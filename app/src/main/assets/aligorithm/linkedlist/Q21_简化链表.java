package com.algorithm.demo.linkedlist;

/**
 * 294 · 简化链表
 * 题目题解笔记讨论排名
 * 描述
 * 给出一个字符链表，对其进行简化。
 * 简化的过程为，保留链表的头尾节点，用数字代替掉中间的部分。
 * 数字也要用字符链表表示。
 * <p>
 * 输入的字符链表用一个整型链表表示，链表的每个节点的值都是其对应的ASCII码。
 * 输入的字符链表的节点数为 nn，3 \le n \le 10^43≤n≤10
 * 4
 * 。
 * 原链表是 'h'->'e'->'l'->'l'->'o'->null。
 * 简化后变为 'h'->'3'->'o'->null。
 * 第二组样例中：
 * 原链表是 'a'->'b'->...->'z'->null。
 * 简化后变为 'a'->'2'->'4'->'z'->null。
 * <p>
 * 样例
 * 输入1：
 * 104->101->108->108->111->null
 * 输出1：
 * 104->51->111->null
 */
public class Q21_简化链表 {

    public static void main(String[] args) {

    }

    /**
     * @param head: the linked list to be simplify.
     * @return: return the linked list after simplifiction.
     */
    public ListNode simplify(ListNode head) {
        // write your code here
        ListNode last = head;
        int count = -1;
        while (last.next != null) {
            count++;
            last = last.next;
        }
        if (count > 0) {
            ListNode middleHead = head;
            String s = String.valueOf(count);
            char[] cs = s.toCharArray();
            for (char c : cs) {
                middleHead.next = new ListNode(c);
                middleHead = middleHead.next;
            }
            middleHead.next = last;
        }
        return head;
    }
}
