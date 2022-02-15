package com.algorithm.demo.linkedlist;

/**
 * 174. 删除链表中倒数第n个节点
 * 给定一个链表，删除链表中倒数第n个节点，返回链表的头节点。
 * <p>
 * 样例
 * 样例 1:
 * 输出: list = 1->2->3->4->5->null， n = 2
 * 输出: 1->2->3->5->null
 * <p>
 * <p>
 * 样例 2:
 * 输入:  list = 5->4->3->2->1->null, n = 2
 * 输出: 5->4->3->1->null
 * <p>
 * 挑战
 * O(n)时间复杂度
 * <p>
 * 注意事项
 * 链表中的节点个数大于等于n
 */
public class Q8_删除链表中倒数第n个节点 {

    public static void main(String[] args) {

    }


    /**
     * @param head: The first node of linked list.
     * @param n:    An integer
     * @return: The head of linked list.
     * <p>
     * 我们也可以在不预处理出链表的长度，以及使用常数空间的前提下解决本题。
     * 由于我们需要找到倒数第 n 个节点，因此我们可以使用两个指针 first 和 second 同时对链表进行遍历，并且 first 比 second 超前 n 个节点。当 first 遍历到链表的末尾时，second 就恰好处于倒数第 n 个节点。
     * 具体地，初始时 first 和 second 均指向头节点。我们首先使用 first 对链表进行遍历，遍历的次数为 n。此时，first 和 second 之间间隔了 n−1 个节点，即 first 比 second 超前了 n 个节点。
     * 在这之后，我们同时使用 first 和 second 对链表进行遍历。当 first 遍历到链表的末尾（即 first 为空指针）时，second 恰好指向倒数第 n 个节点。
     * 根据方法一和方法二，如果我们能够得到的是倒数第 n 个节点的前驱节点而不是倒数第 n 个节点的话，删除操作会更加方便。因此我们可以考虑在初始时将 second 指向哑节点，其余的操作步骤不变。
     * 这样一来，当 first 遍历到链表的末尾时，second 的下一个节点就是我们需要删除的节点。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // write your code here
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

}
