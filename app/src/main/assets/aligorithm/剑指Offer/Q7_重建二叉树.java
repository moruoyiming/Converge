package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.tree.TreeNode;

import java.util.Arrays;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
 * 前序遍历{1,2,4,7,3,5,6,8}
 * 中序遍历{4,7,2,1,5,3,8,6}
 * 1 根节点
 * 1
 * 2     3
 * 4      5    6
 * 7      8
 */
public class Q7_重建二叉树 {

    public static void main(String[] args) {
        int[] preorder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inorder = {4, 7, 2, 1, 5, 3, 8, 6};
        buildTree(preorder, inorder);
    }

    /**
     * 通过【前序遍历列表】确定【根节点 (root)】
     * 将【中序遍历列表】的节点分割成【左分支节点】和【右分支节点】
     * 递归寻找【左分支节点】中的【根节点 (left child)】和 【右分支节点】中的【根节点 (right child)】
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        if (n == 0) {
            return null;
        }
        int rootVal = preorder[0], rootIndex = 0;
        for (int i = 0; i < n; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, 1 + rootIndex), Arrays.copyOfRange(inorder, 0, rootIndex));
        root.right = buildTree(Arrays.copyOfRange(preorder, 1 + rootIndex, n), Arrays.copyOfRange(inorder, rootIndex + 1, n));
        return root;
    }
}
