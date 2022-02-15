package com.algorithm.demo.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 描述
 * 给出一棵二叉树,返回其中序遍历。
 * 样例
 * 样例 1：
 * 输入：
 * 二叉树 = {1,2,3}
 * 输出：
 * [2,1,3]
 * 解释：
 * 1
 * /  \
 * 2     3
 * 它将被序列化为{1,2,3}中序遍历
 * 样例 2：
 * 输入：
 * 二叉树 = {1,#,2,3}
 * 输出：
 * [1,3,2]
 * 解释：
 * 1
 * \
 * 2
 * /
 * 3
 * 它将被序列化为{1,#,2,3}中序遍历
 */
public class 二叉树的中序遍历 {

    /**
     * @param root: A Tree
     * @return: Inorder in ArrayList which contains node values.
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            ans.add(root.val);
            root = root.right;
        }
        return ans;
    }

    /**
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    /**
     * 首先我们需要了解什么是二叉树的中序遍历：按照访问左子树——根节点——右子树的方式遍历这棵树，而在访问左子树或者右子树的时候我们按照同样的方式遍历，
     * 直到遍历完整棵树。因此整个遍历过程天然具有递归的性质，我们可以直接用递归函数来模拟这一过程。
     * 定义 inorder(root) 表示当前遍历到 \textit{root}root 节点的答案，那么按照定义，我们只要递归调用 inorder(root.left)
     * 来遍历 \textit{root}root 节点的左子树，然后将 \textit{root}root 节点的值加入答案，再递归调用inorder(root.right)
     * 来遍历 \textit{root}root 节点的右子树即可，递归终止的条件为碰到空节点。
     * @param root
     * @param res
     */
    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }
}
