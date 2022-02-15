package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.tree.TreeNode;

import java.util.*;

/**
 * 不分行从上到下打印二叉树
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印
 * 例如：依次打印出8，6，10，5，7，9，11
 * 规律：每次打印一个节点的时候，如果该节点有子节点，则把该节点的子节点放到一个队列的末尾。接下来到队列的头部去除最早进入队列的节点，重复前面的打印
 * 操作，直至队列中所有的节点都被打印出来。
 */
public class Q32_从上到下打印二叉树 {

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> myQueue = new LinkedList<>();//注意Queue是一个接口
        ArrayList<Integer> list = new ArrayList<>();
        if (root==null)
            return list;

        myQueue.offer(root);//先将根节点入队列

        while(!myQueue.isEmpty()){//一直将队列中所有元素都出队为止
            TreeNode tempNode = myQueue.poll();//出队操作
            list.add(tempNode.val);
            if (tempNode.left!=null)//节点的左右子树入队操作
                myQueue.offer(tempNode.left);
            if (tempNode.right!=null)
                myQueue.offer(tempNode.right);
        }
        return list;
    }
}
