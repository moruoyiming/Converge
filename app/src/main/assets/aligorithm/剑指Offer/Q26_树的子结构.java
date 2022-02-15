package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.tree.TreeNode;

/**
 * 输入两颗二叉树A和B，判断B是不是A的字结构。
 * <p>
 * 要查找树A是否存在和树B结构一样的紫薯，分成两步：
 * 1.在树A中找到和树B的跟节点的值一样的节点R
 * 2.判断树A中以R为根节点的子树是不是包含和树B一样的结构
 */
public class Q26_树的子结构 {

    public boolean HasSubtree(TreeNode rootA, TreeNode rootB) {
        boolean result = false;
        if (rootA != null && rootB != null) {
            if (!Equal(rootA.val, rootB.val)) {
                result = DoesTreeHaveTree(rootA, rootB);
            }
            if (!result) {
                result = HasSubtree(rootA.left, rootB);
            }
            if (!result) {
                result = HasSubtree(rootA.right, rootB);
            }
        }
        return result;
    }

    private boolean DoesTreeHaveTree(TreeNode rootA, TreeNode rootB) {
        if (rootB == null) {
            return true;
        }
        if (rootA == null) {
            return false;
        }
        if (!Equal(rootA.val, rootB.val)) {
            return false;
        }
        return DoesTreeHaveTree(rootA.left, rootB.left) && DoesTreeHaveTree(rootA.right, rootB.right);
    }


    public boolean Equal(double num1, double num2) {
        if ((num1 - num2 > -0.0000001) && (num1 - num2 < 0.0000001)) {
            return true;
        } else {
            return false;
        }
    }
}
