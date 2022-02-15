package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.tree.TreeNode;

/**
 * 输入一棵二叉树，将该二叉树转换成一个排序的双向链表。要求不能创建任何新的节点，只能调整树中节点指针的方向。
 * <p>
 * 10
 * 6         14
 * 4       8  12     16
 * <p>
 * 双向链表：  4  6  8  10  12  14  16
 * 在二叉树中，每个节点都有两个指向子节点的指针。在双向链表中，每个节点也有两个指针，分别指向前一个节点和后一个节点。
 * 在把左、右子树都转换成排序双向链表之后再和根节点链接起来，整棵二叉搜索树转换成了排序双向链表。
 */
public class Q36_二叉搜索树与双向链表 {

    public TreeNode convert(TreeNode root) {
        TreeNode pLastNodeInList = new TreeNode();
        ConvertNode(root, pLastNodeInList);
        //pLastNodeInList指向双向链表的尾节点
        //我们需要返回头节点
        TreeNode pHeadOfList = pLastNodeInList;
        while (pHeadOfList != null && pHeadOfList.left != null) {
            pHeadOfList = pHeadOfList.left;
        }
        return pHeadOfList;
    }

    /**
     * 用pLastNodeInList指向已经转换好的链表的最后一个节点(值最大的节点)。当遍历到值为10的节点的时候，它的左子树都已经转换好了，因此
     * pLastNodeInList指向值为8的系欸但。接着把根节点链接到链表中之后，值为10的节点成了链表中的最后一个节点(新的值最大的节点)，于是pLastNodeInList
     * 指向了这个值为10的节点。接下来把pLastNodeInList作为参数传入函数递归遍历右子树。得到右子树中最左边的子节点(值为12的节点，再右子树树中值最小)
     * 把该节点和值为10的节点链接起来。
     * @param pNode
     * @param pLastNodeInList
     */
    public void ConvertNode(TreeNode pNode, TreeNode pLastNodeInList) {
        if (pNode == null) {
            return;
        }
        TreeNode pCurrent = pNode;
        if (pCurrent.left != null) {
            ConvertNode(pCurrent.left, pLastNodeInList);
        }
        pCurrent.left = pLastNodeInList;
        if (pLastNodeInList != null) {
            pLastNodeInList.right = pCurrent;
        }
        pLastNodeInList = pCurrent;
        if (pCurrent.right != null) {
            ConvertNode(pCurrent.right, pLastNodeInList);
        }
    }

}
