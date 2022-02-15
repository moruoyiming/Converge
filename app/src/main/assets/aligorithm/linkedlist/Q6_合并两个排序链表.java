package com.algorithm.demo.linkedlist;

/**
 * 165. 合并两个排序链表
 * 将两个排序链表合并为一个新的排序链表
 * <p>
 * 样例
 * Example 1:
 * Input: list1 = null, list2 = 0->3->3->null
 * Output: 0->3->3->null
 * <p>
 * <p>
 * Example 2:
 * Input:  list1 =  1->3->8->11->15->null, list2 = 2->null
 * Output: 1->2->3->8->11->15->null
 */
public class Q6_合并两个排序链表 {

    public static void main(String[] args) {

    }


    /**
     * @param l1: ListNode l1 is the head of the linked list
     * @param l2: ListNode l2 is the head of the linked list
     * @return: ListNode head of linked list
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // write your code here
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = new ListNode(0);
        ListNode current = head;
        while (l1 != null && l2 != null) {
            //比较一下，哪个小就把哪个放到新的链表中
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        //然后把那个不为空的链表挂到新的链表中
        current.next = l1 == null ? l2 : l1;
        return head.next;
    }

}
