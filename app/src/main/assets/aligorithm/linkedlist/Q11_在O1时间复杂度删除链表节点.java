package com.algorithm.demo.linkedlist;

/**
 * 372. 在O(1)时间复杂度删除链表节点
 * 给定一个单链表中的一个等待被删除的节点(非表头或表尾)。请在在 O(1) 时间复杂度删除该链表节点。
 *
 * 样例
 * 样例 1：
 *
 * 输入：
 * 1->2->3->4->null
 * 3
 * 输出：
 * 1->2->4->null
 * 样例 2：
 *
 * 输入：
 * 1->3->5->null
 * 3
 * 输出：
 * 1->5->null
 */
public class Q11_在O1时间复杂度删除链表节点 {

    public static void main(String[] args) {

    }

    /*
     * @param node: the node in the list should be deleted
     * @return: nothing
     */
    public static ListNode deleteNode(ListNode head, ListNode tobeDelete) {
        if (head == null || tobeDelete == null){
            return null;
        }
        if (tobeDelete.next != null){   // 要删除的节点不是尾节点
            ListNode next = tobeDelete.next;
            tobeDelete.val = next.val;
            tobeDelete.next = next.next;
        } else if (head == tobeDelete){   // 删除的节点只有一个节点，此节点为头节点
            head = null;
        } else {   // 删除的节点为尾节点
            ListNode cur = head;
            while (cur.next != tobeDelete){
                cur = cur.next;
            }
            cur.next = null;
        }
        return head;
    }

}
