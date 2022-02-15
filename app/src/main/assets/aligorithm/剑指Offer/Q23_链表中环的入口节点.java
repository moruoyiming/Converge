package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.linkedlist.ListNode;

/**
 * 如果一个链表中包含环，如何找出环的入口节点？
 * 解决这个问题的第一步是如何确定一个链表中包含环。
 * 用两个指针来解决这个问题，和前面的问题一样，定义两个指针，同时从链表的头节点出发，一个指针一次走一步，另一个指针一次走两步。
 * 如果走得快的指针追上了走得慢的指针，那么链表就包含环；
 * 如果走得快的指针走到了链表的末尾都没有追上走得慢的指针，那么链表就不包含环。
 * <p>
 * 剩下的问题是如何得到环中节点的数目。前面提到判断一个链表里是否有环时用了一块一慢两个指针。如果两个指针相遇，则表明链表中存在环。两个指针相遇
 * 的节点一定是在环中。如果从这个节点出发，一边继续向前移动一边计数，当再次回到这个节点时，就可以得到环中节点数了。
 */
public class Q23_链表中环的入口节点 {

    public ListNode meetingNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head.next;
        if (slow == null) {
            return null;
        }
        ListNode fast = slow.next;
        while (fast != null && slow != null) {
            if (fast == slow) {
                return fast;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        return null;

    }

}
