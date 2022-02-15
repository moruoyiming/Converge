package com.algorithm.demo.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 483 · 链表转数组
 * 描述
 * 将一个链表转换为一个数组。
 * 样例
 * 样例 1:
 * <p>
 * 输入: 1->2->3->null
 * 输出: [1,2,3]
 */
public class Q20_链表转数组 {

    public static void main(String[] args) {

    }

    /**
     * @param head: the head of linked list.
     * @return: An integer list
     */
    public List<Integer> toArrayList(ListNode head) {
        // write your code here
        List<Integer> result = new ArrayList<>();
        while (head != null) {
            result.add(head.val);
            head = head.next;
        }
        return result;
    }

}
