package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.linkedlist.ListNode;

/**
 * 在一个排序的链表中，如何删除重复的节点？在链表中重复的节点被删除之后下
 * 1 -> 2 -> 3 -> 3 -> 4 -> 4 ->5
 * 1 -> 2 -> 5
 * 一个有7个节点的链表，当重复的节点被删除之后，链表中只剩下3个节点。
 * 解决这个问题的第一步是确定删除函数的参数。当然，这个函数需要输入待删除链表的头节点。头节点可能与后面的节点重复，也就是说头节点也可能被删除。
 * 从头遍历整个链表，如果当前节点(pNode)的值与下一个节点的值相同，那么它们就是重复的节点，都可以被删除。为了保证删除之后的便表仍然是相连的，我们要把当前
 * 节点的前一个节点(mPreNode)和后面值比较当前节点的值大的节点相连。我们要确保mPreNode始终雨下一个节点没有重复的节点连接在一起。
 */
public class 删除链表中重复的节点 {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2, n1);
        ListNode n3 = new ListNode(2, n2);
        ListNode n4 = new ListNode(3, n3);
        ListNode n5 = new ListNode(4, n4);
        ListNode n6 = new ListNode(4, n5);
        ListNode n7 = new ListNode(5, n6);
        ListNode node = n1;
        System.out.println("删除前：");
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
        ListNode nodeafterdelete = delete(n1);
        System.out.println("删除后:");
        while (nodeafterdelete != null) {
            System.out.print(nodeafterdelete.val + " ");
            nodeafterdelete = nodeafterdelete.next;
        }
        System.out.println();
    }

    /**
     * 以链表为例来分析删除重复节点的过程，当我们遍历到第一个值为3的节点的时候，pPreNode指向值为2的节点。由于接下来的节点的值还是3，这两个
     * 节点应该被删除，因此pPreNode就和第一个值为4的节点相连。接下来来由于值为4的两个节点也重复了，还是会被删除，所以pPreNode最终会和值为5的
     * 节点相连。
     *
     * @param pHead
     */
    public static void deleteDuplication(ListNode pHead) {
        if (pHead == null) {
            return;
        }
        ListNode mPreNode = null;
        ListNode pNode = pHead;
        while (pNode != null) {
            ListNode pNext = pNode.next;
            boolean needDelete = false;
            if (pNext != null && pNext.val == pNode.val) {
                needDelete = true;
            }
            if (!needDelete) {
                mPreNode = pNode;
                pNode = pNode.next;
                System.out.println("pNode " + pNode.val);
            } else {
                int value = pNode.val;
                ListNode pToBeDel = pNode;
                while (pToBeDel != null && pToBeDel.val == value) {
                    pNext = pToBeDel.next;
                    pToBeDel = null;
                    pToBeDel = pNext;
                }
                if (mPreNode == null) {
                    pHead = pNext;
                } else {
                    mPreNode.next = pNext;
                }
                pNode = pNext;
                System.out.println("pNode " + pNode.val);
            }
        }
    }

    public static ListNode delete(ListNode node) {
        if(node==null) return null;
        if(node.next!=null) {
            ListNode first=node;
            ListNode pre=node;
            node=node.next;
            if(first.val!=first.next.val) {
                while(node.next!=null) {
                    if(node.val!=node.next.val) {             //如果node和它后面元素，两个不相等，各自移动
                        node=node.next;
                        pre=pre.next;
                    }else {
                        while(node.val==node.next.val) {     //如果node和它后面的相等
                            node=node.next;                        //node移动到最后一个重复元素
                        }
                        node=node.next;                            //再移动一个，重复元素的后一个
                        pre.next=node;                            //pre指向node
                    }
                }
            }else {
                while(node.val==node.next.val) node=node.next;
                node=node.next;
                first=node;
                delete(first);
            }
            return first;//返回删除重复元素的指针
        }
        return null;
    }
}
