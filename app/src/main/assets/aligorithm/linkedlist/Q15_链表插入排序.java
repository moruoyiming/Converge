package com.algorithm.demo.linkedlist;

/**
 * 173. 链表插入排序
 * 用插入排序对链表排序
 * <p>
 * 样例
 * 样例 1:
 * 输入: 0->null
 * 输出: 0->null
 * <p>
 * <p>
 * 样例 2:
 * 输入:  1->3->2->0->null
 * 输出 :0->1->2->3->null
 */
public class Q15_链表插入排序 {

    public static void main(String[] args) {

    }


    /**
     * @param head: The first node of linked list.
     * @return: The head of linked list.
     */
    public static ListNode insertionSortList(ListNode head) {
        // 1. 首先判断给定的链表是否为空，若为空，则不需要进行排序，直接返回。
        if(head == null){
            return head;
        }
        // 2. 链表初始化操作
        ListNode dummyHead = new ListNode(0); // 引入哑节点
        dummyHead.next = head;                // 目的是在head之前插入节点
        ListNode lastSorted = head;           // 维护lastSorted为链表已经排好序的最后一个节点并初始化
        ListNode curr = head.next;            // 维护curr 为待插入的元素并初始化
        // 3. 插入排序
        while(curr != null){
            if(lastSorted.val <= curr.val){     // 说明curr应该位于lastSorted之后
                lastSorted = lastSorted.next;   // 将lastSorted后移一位,curr变成新的lastSorted
            }else{                              // 否则,从链表头结点开始向后遍历链表中的节点
                ListNode prev = dummyHead;      // 从链表头开始遍历 prev是插入节点curr位置的前一个节点
                while(prev.next.val <= curr.val){ // 循环退出的条件是找到curr应该插入的位置
                    prev = prev.next;
                }
                // 以下三行是为了完成对curr的插入（配合题解动图可以直观看出）
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next; // 此时 curr 为下一个待插入的元素
        }
        // 返回排好序的链表
        return dummyHead.next;
    }

}
