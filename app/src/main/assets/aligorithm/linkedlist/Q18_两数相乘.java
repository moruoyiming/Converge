package com.algorithm.demo.linkedlist;

/**
 * 756 · 两数相乘
 * <p>
 * 题目题解笔记讨论排名
 * 描述
 * 给出两个链表形式表示的数字,写一个函数得到这两个链表相乘乘积。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入：9->4->6->null,8->4->null
 * 输出：79464
 * 解释：946*84=79464
 * 样例 2:
 * <p>
 * 输入：3->2->1->null,1->2->null
 * 输出：3852
 * 解释：321*12=3852
 */
public class Q18_两数相乘 {

    public static void main(String[] args) {
//        long result = multiplyLists();
    }

    /**
     * @param l1: the first list
     * @param l2: the second list
     * @return: the product list of l1 and l2
     */
    public static long multiplyLists(ListNode l1, ListNode l2) {
        // write your code here
        ListNode p = new ListNode(0);
        p = l1;
        long num1 = 0;
        while (p != null) {
            num1 *= 10;
            num1 += p.val;
            p = p.next;
        }
        p = l2;
        long num2 = 0;
        while (p != null) {
            num2 *= 10;
            num2 += p.val;
            p = p.next;
        }
        return num1 * num2;
    }

}
