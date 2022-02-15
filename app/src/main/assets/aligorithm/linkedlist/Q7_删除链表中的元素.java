package com.algorithm.demo.linkedlist;

/**
 * 452. 删除链表中的元素
 * 删除链表中等于给定值 val 的所有节点。
 * <p>
 * 样例
 * Example 1:
 * <p>
 * Input: head = 1->2->3->3->4->5->3->null, val = 3
 * Output: 1->2->4->5->null
 * Example 2:
 * <p>
 * Input: head = 1->1->null, val = 1
 * Output: null
 * <p>
 * <p>
 * 方法：哨兵节点
 * 如果删除的节点是中间的节点，则问题似乎非常简单：
 * <p>
 * 选择要删除节点的前一个结点 prev。
 * 将 prev 的 next 设置为要删除结点的 next。
 * <p>
 * <p>
 * 当要删除的一个或多个节点位于链表的头部时，事情会变得复杂。
 * <p>
 * <p>
 * <p>
 * 可以通过哨兵节点去解决它，哨兵节点广泛应用于树和链表中，如伪头、伪尾、标记等，它们是纯功能的，通常不保存任何数据，其主要目的是使链表标准化，如使链表永不为空、永不无头、简化插入和删除。
 * <p>
 * <p>
 * <p>
 * 在这里哨兵节点将被用于伪头。
 * <p>
 * 算法：
 * <p>
 * 初始化哨兵节点为 ListNode(0) 且设置 sentinel.next = head。
 * 初始化两个指针 curr 和 prev 指向当前节点和前继节点。
 * 当 curr != nullptr：
 * 比较当前节点和要删除的节点：
 * 若当前节点就是要删除的节点：则 prev.next = curr.next。
 * 否则设 prve = curr。
 * 遍历下一个元素：curr = curr.next。
 * 返回 sentinel.next。
 */
public class Q7_删除链表中的元素 {

    public static void main(String[] args) {
        ListNode temp3 = new ListNode(5);
        ListNode temp2 = new ListNode(1, temp3);
        ListNode temp1 = new ListNode(3, temp2);
        ListNode temp = removeElements(temp1, 1);
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    /**
     * @param head: a ListNode
     * @param val:  An integer
     * @return: a ListNode
     */
    public static ListNode removeElements(ListNode head, int val) {
        // write your code here
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode pre = sentinel;
        ListNode curr = head;

        while (curr != null) {
            if (curr.val == val) {
                pre.next = curr.next;
            } else {
                pre = curr;
            }
            curr = curr.next;
        }
        return sentinel.next;
    }

    /**
     * 方法一：递归
     * 链表的定义具有递归的性质，因此链表题目常可以用递归的方法求解。这道题要求删除链表中所有节点值等于特定值的节点，可以用递归实现。
     * 对于给定的链表，首先对除了头节点 \textit{head}head 以外的节点进行删除操作，然后判断 \textit{head}head 的节点值是否等于给定的 \textit{val}val。如果 \textit{head}head 的节点值等于 \textit{val}val，则 \textit{head}head 需要被删除，因此删除操作后的头节点为 \textit{head}.\textit{next}head.next；如果 \textit{head}head 的节点值不等于 \textit{val}val，则 \textit{head}head 保留，因此删除操作后的头节点还是 \textit{head}head。上述过程是一个递归的过程。
     * 递归的终止条件是 \textit{head}head 为空，此时直接返回 \textit{head}head。当 \textit{head}head 不为空时，递归地进行删除操作，然后判断 \textit{head}head 的节点值是否等于 \textit{val}val 并决定是否要删除 \textit{head}head。
     * 时间复杂度：O(n)O(n)，其中 nn 是链表的长度。递归过程中需要遍历链表一次。
     * 空间复杂度：O(n)O(n)，其中 nn 是链表的长度。空间复杂度主要取决于递归调用栈，最多不会超过 nn 层。
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        head.next = removeElements2(head, val);
        return head.val == val ? head.next : head;
    }

    /**
     * 方法二：迭代
     * 也可以用迭代的方法删除链表中所有节点值等于特定值的节点。
     * 用 \textit{temp}temp 表示当前节点。如果 \textit{temp}temp 的下一个节点不为空且下一个节点的节点值等于给定的 \textit{val}val，则需要删除下一个节点。删除下一个节点可以通过以下做法实现：
     * \textit{temp}.\textit{next} = \textit{temp}.\textit{next}.\textit{next}
     * temp.next=temp.next.next
     * 如果 \textit{temp}temp 的下一个节点的节点值不等于给定的 \textit{val}val，则保留下一个节点，将 \textit{temp}temp 移动到下一个节点即可。
     * 当 \textit{temp}temp 的下一个节点为空时，链表遍历结束，此时所有节点值等于 \textit{val}val 的节点都被删除。
     * 具体实现方面，由于链表的头节点 \textit{head}head 有可能需要被删除，因此创建哑节点 \textit{dummyHead}dummyHead，令 \textit{dummyHead}.\textit{next} = \textit{head}dummyHead.next=head，初始化 \textit{temp}=\textit{dummyHead}temp=dummyHead，然后遍历链表进行删除操作。最终返回 \textit{dummyHead}.\textit{next}dummyHead.next 即为删除操作后的头节点。
     * 时间复杂度：O(n)O(n)，其中 nn 是链表的长度。需要遍历链表一次。
     * 空间复杂度：O(1)O(1)。
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements3(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while(temp.next != null){
            if(temp.next.val == val){
                temp.next = temp.next.next;
            }else{
                temp = temp.next;
            }
        }
        return dummyHead.next;
    }


}
