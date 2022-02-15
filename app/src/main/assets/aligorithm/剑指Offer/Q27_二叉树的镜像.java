package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.tree.TreeNode;

/**
 * 直接利用递归遍历二叉树，交换每个节点的左 / 右子节点，即可生成二叉树的镜像。
 * 1.先序遍历这颗树的每个节点，如果遍历到的节点有子节点，就交换它的两个子节点。
 * 当交换完所有非叶子节点的左、右子节点之后，就得到了树的镜像
 */
public class Q27_二叉树的镜像 {

    /**
     * 时间复杂度为 O(N)。
     * 空间复杂度为 O(N)。
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        //当节点为空时，直接返回
        if (root == null) {
            return null;
        }
        //设置一个临时节点tmp 用来存储当前节点的左子树
        TreeNode tmp = root.left;
        //以下两个操作是在交换当前节点的左右子树

        //当前节点的左子树为节点的右子树
        //同时递归下去，不停的交换子树中的节点
        root.left = mirrorTree(root.right);
        //当前节点的右子树为节点的左子树
        //同时递归下去，不停的交换子树中的节点
        root.right = mirrorTree(tmp);
        //最后返回根节点
        return root;

    }

}
