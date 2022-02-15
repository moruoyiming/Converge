package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.tree.TreeNode;

/**
 * 给定一棵二叉树和其中的一个节点，如何找出中序遍历序列的洗一个节点？
 * 书中的节点除了有两个分别指向左右子节点的指针，还有一个指向父节点的指针
 * 中序遍历 {d,b,h,e,i,a,f,c,g}
 * 1.如果一个节点有右子树，那么他的下一个节点就是他的右子树中的最左子节点。
 * 从右子节点出发一直沿着指向左子节点的指针 ，就能找到他的下一个节点。
 * 2.如果一个节点没有右子树，如果节点是它父节点的左子节点，那么它的下一个节点就是它的父节点。
 * 3.如果一个节点既没有右子树，并且还是父节点的右子节点，可以沿着指向父节点的指针一直向上便利，知道找到一个是它父节点的左子节点的节点。
 * 如果这样的节点存在，那么这个节点的父节点就是我们要找的下一个节点。
 *      a
 *   b      c
 * d  e   f   g
 *   h i
 */
public class Q8_二叉树的下一个节点 {

    public TreeNode getNext(TreeNode node){
        if(node.right ==null){
            return null;
        }
        if(node.right != null){
            node = node.right;
            while(node.left != null){
                node = node.left;
            }
            return node;
        }

//        while(node.next!=null){ //没右子树，则找第一个当前节点是父节点左孩子的节点
//            if(node.next.left==node) return node.next;
//            node = node.next;
//        }
        return null;   //退到了根节点仍没找到，则返回null
    }
    
}
