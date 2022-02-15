package com.algorithm.demo.剑指Offer;

/**
 * 输入一个整数素组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回true，否则返回false。假设输入的数组的任意两个数字都互相不相同。
 * 例如：输入数组{5，7，6，9，11，10，8}，则返回true，因为这个整数序列二叉树的后序遍历结果。如果输入的数组是{7，4，6，5}，则由于没有哪颗二叉树
 * 的后序遍历结果是这个序列，因此返回false。
 * 8
 * 6        10
 * 5       7 9       11
 * 在后序遍历得到的序列中 ， 最后一个数字是树的根节点的值。数组中前面的数字可以分成两部分：
 * 第一部分是左子树节点的值，他们都比根节点的值小；
 * 第二部分是右子树节点的值，他们都比根节点的值大。
 * 以数组{5，7，6，9，11，10，8}为例，后序遍历结果的最后一个数字8就是根节点的值。在这个数组中，前3个数字5，7和6都比8小，是值为8的节点的左子树节点；
 * 后3个数字9，11和10都比8大，是值为8的节点的右子树节点。
 */
public class Q33_二叉搜索树的后序遍历序列 {

    boolean verifySquenceOfBST(int[] sequencce, int length) {
        if (sequencce == null || length <= 0) {
            return false;
        }
        int root = sequencce[length - 1];
        //在二叉搜素树中左子树节点的值小于根节点的值
        int i = 0;
        for (; i < length - 1; ++i) {
            if (sequencce[i] > root) {
                break;
            }
        }
        //在二叉搜索树中右子树节点的值大于根节点的值
        int j = i;
        for (; j < length - 1; ++j) {
            if (sequencce[j] < root) {
                return false;
            }
        }
        //判断左子树是不是二叉搜素树
        boolean left = true;
        if (i > 0) {
            left = verifySquenceOfBST(sequencce, i);
        }
        //判断右子树是不是二叉搜索树
        boolean right = true;
        if (i < length - 1) {//sequence + i
            right = verifySquenceOfBST(sequencce, length - i - 1);
        }
        return (left && right);
    }

}
