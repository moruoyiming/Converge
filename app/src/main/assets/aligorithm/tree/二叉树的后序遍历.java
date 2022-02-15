package com.algorithm.demo.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 二叉树的后序遍历 {

    /**
     * @param root: A Tree
     * @return: Inorder in ArrayList which contains node values.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while(root != null || !stack.isEmpty()){
            while(root != null){
               stack.push(root);
               root = root.left;
            }
            root = stack.pop();
            if(root.right == null || root.right == prev){
                ans.add(root.val);
                prev = root;
                root = null;
            }else{
                stack.push(root);
                root = root.right;
            }
        }
        return ans;
    }

}
