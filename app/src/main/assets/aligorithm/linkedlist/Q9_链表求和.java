package com.algorithm.demo.linkedlist;

/**
 * 167. 链表求和
 * 中文English
 * 你有两个用链表代表的整数，其中每个节点包含一个数字。数字存储按照在原来整数中相反的顺序，使得第一个数字位于链表的开头。写出一个函数将两个整数相加，用链表形式返回和。
 * <p>
 * 样例
 * Example 1:
 * <p>
 * Input: 7->1->6->null, 5->9->2->null
 * Output: 2->1->9->null
 * Explanation: 617 + 295 = 912, 912 to list:  2->1->9->null
 * Example 2:
 * <p>
 * Input:  3->1->5->null, 5->9->2->null
 * Output: 8->0->8->null
 * Explanation: 513 + 295 = 808, 808 to list: 8->0->8->null
 */
public class Q9_链表求和 {

    public static void main(String[] args) {
        ListNode temp3 = new ListNode(5);
        ListNode temp2 = new ListNode(1, temp3);
        ListNode temp1 = new ListNode(3, temp2);

        ListNode temp33 = new ListNode(2);
        ListNode temp22 = new ListNode(9, temp33);
        ListNode temp11 = new ListNode(5, temp22);
        ListNode what = addLists(temp1, temp11);
        while (what != null) {//1  2  3  4
            System.out.println(what.val);
            what = what.next;
        }
    }


    /**
     * @param l1: the first list
     * @param l2: the second list
     * @return: the sum list of l1 and l2
     */
    public static ListNode addLists(ListNode l1, ListNode l2) {
        // write your code here
        ListNode head = new ListNode(0);
        ListNode current = head;
        int carry = 0;
        while (l1 != null || l2 != null || carry > 0) {
            int i = l1 != null ? l1.val : 0;
            int j = l2 != null ? l2.val : 0;
            int num = i + j + carry;
            current.next = new ListNode(num % 10);
            carry = num / 10;
            current = current.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return head.next;
    }

    /**
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while(l1 != null || l2 != null){
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        if(carry == 1){
            cur.next = new ListNode(1);
        }
        return pre.next;
    }
}
