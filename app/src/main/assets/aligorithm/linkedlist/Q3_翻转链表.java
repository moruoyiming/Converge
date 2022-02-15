package com.algorithm.demo.linkedlist;

/**
 * 35. 翻转链表
 * 翻转一个链表
 * <p>
 * 样例
 * Example 1:
 * <p>
 * Input: 1->2->3->null
 * Output: 3->2->1->null
 * Example 2:
 * <p>
 * Input: 1->2->3->4->null
 * Output: 4->3->2->1->null
 * 挑战
 * 在原地一次翻转完成
 */
public class Q3_翻转链表 {

    public static void main(String[] args) {
        ListNode cur = null;
        ListNode next = null;
        for (int i = 0; i < 10; i++) {
            cur = new ListNode(i, next);
            next = cur;
            System.out.println(cur.val);
        }
        reverse(cur);
    }

    /**
     * 我们可以申请两个指针，第一个指针叫 pre，最初是指向 null 的。
     * 第二个指针 cur 指向 head，然后不断遍历 cur。
     * 每次迭代到 cur，都将 cur 的 next 指向 pre，然后 pre 和 cur 前进一位。
     * 都迭代完了(cur 变成 null 了)，pre 就是最后一个节点了。
     * @param head: n
     * @return: The new head of reversed linked list.
     */
    public static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = null;
        while (head != null) {//1  2  3  4
            cur = head.next;
            head.next = pre;
            pre = head;
            head = cur;
            System.out.println(pre.val);
        }
        return pre;
    }

}
