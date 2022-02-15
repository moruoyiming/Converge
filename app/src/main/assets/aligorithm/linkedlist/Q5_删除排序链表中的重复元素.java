package com.algorithm.demo.linkedlist;

/**
 * 112. 删除排序链表中的重复元素
 * 给定一个排序链表，删除所有重复的元素每个元素只留下一个。
 * <p>
 * 样例
 * Example 1:
 * Input:  null
 * Output: null
 * <p>
 * <p>
 * Example 2:
 * Input:  1->1->2->null
 * Output: 1->2->null
 * <p>
 * Example 3:
 * Input:  1->1->2->3->3->null
 * Output: 1->2->3->null
 */
public class Q5_删除排序链表中的重复元素 {

    public static void main(String[] args) {
        ListNode temp3 = new ListNode(2);
        ListNode temp2 = new ListNode(1, temp3);
        ListNode temp1 = new ListNode(1, temp2);
        deleteDuplicates(temp1);
    }

    /**
     * 算法
     * <p>
     * 这是一个简单的问题，仅测试你操作列表的结点指针的能力。由于输入的列表已排序，因此我们可以通过将结点的值与它之后的结点进行比较来确定它是否为重复结点。如果它是重复的，我们更改当前结点的 next 指针，以便它跳过下一个结点并直接指向下一个结点之后的结点。
     * <p>
     * 复杂度分析
     * <p>
     * 时间复杂度：O(n)O(n)，因为列表中的每个结点都检查一次以确定它是否重复，所以总运行时间为 O(n)O(n)，其中 nn 是列表中的结点数。
     * <p>
     * 空间复杂度：O(1)O(1)，没有使用额外的空间。
     *
     * @param head: head is the head of the linked list
     * @return: head of linked list
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode node = head;
        while (node.next != null) {
            if (node.val == node.next.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
            System.out.println("sdfasdf="+node.val);
        }
        return head;
    }

}
