package com.algorithm.demo.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 822. 相反的顺序存储
 * 给出一个链表，并将链表的值以倒序存储到数组中。
 *
 * 样例
 * 样例1
 *
 * 输入: 1 -> 2 -> 3 -> null
 * 输出: [3,2,1]
 * 样例2
 *
 * 输入: 4 -> 2 -> 1 -> null
 * 输出: [1,2,4]
 * 注意事项
 * 您不能改变原始链表的结构。
 * ListNode 有两个成员变量：ListNode.val 和 ListNode.next
 */
public class Q13_相反的顺序存储 {

    public static void main(String[] args) {

    }

    /**
     * @param head: the given linked list
     * @return: the array that store the values in reverse order
     */
    public List<Integer> reverseStore2(ListNode head) {
        List<Integer> ls = new ArrayList<Integer>();
        while (head == null) {
            return null;
        }
        Stack<Integer> s = new Stack<Integer>();
        s.size();
        while (head != null) {
            s.push(head.val);
            head = head.next;
        }
        while (s.empty() == false) {
            int tmp = s.pop();
            ls.add(tmp);
        }
        return ls;
    }

    /**
     * @param head: the given linked list
     * @return: the array that store the values in reverse order
     */
    public List<Integer> reverseStore(ListNode head) {
        List<Integer> integers = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            System.out.println(" " + curr.val);
            curr = curr.next;
            integers.add(curr.val);
        }
        int j = integers.size() - 1;
        for (int i = 0; i < integers.size(); i++) {
            temp.add(i, integers.get(j));
            j--;
        }
        return temp;
    }

}
