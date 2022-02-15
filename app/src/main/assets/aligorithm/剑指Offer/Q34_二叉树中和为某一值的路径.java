package com.algorithm.demo.剑指Offer;

import com.algorithm.demo.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
 * 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径
 * <p>
 * 10
 * 5        12
 * 4     7
 * 由于路径是从根节点出发到叶节点，路径总是以根节点为起始点，因此需要遍历根节点。
 * 在树的前序、中序、后序3种遍历方式中，只有前序遍历是首先访问根节点。
 * 没访问一个节点，需要把当前节点添加到路径中去。当到达节点5时，路径包含两个节点，分别是10和5.接下来遍历到节点4，把这个节点叶添加到路径中。这时
 * 达到叶节点，但路径上3个节点的值之和是19.这个和不等于输入的值22，因此不是符合要求的路径。
 * 接着要遍历其他的节点，在遍历下一个节点之前，先要从节点4回到节点5，再去便利节点5的右子节点7.值得注意的是，当回到节点5的时候，由于节点4已经
 * 不在前往节点7的路径上了，所以我们需要把节点4从路径中删除。接下来访问节点7的时候，再把该节点添加到路径中。此时路径中的3个节点10、5、7之和刚好是22，
 * 是一条符合要求的路径。
 * 当用前序遍历的方式访问到某一节点时，我们把该节点添加到路径上，并累加该节点的值。如果该节点为叶节点，并且路径中节点值的和刚好等于输入的整数，
 * 则当前路径符合要求，把它打印出来。
 * 如果当前节点不是叶节点，则继续访问它的子节点。当前节点访问结束后，递归函数将自动回到它的夫系欸但。因此，我们再函数退出之前要再路径上删除当前节点
 * 并减去当前节点的值，以确保返回父节点时路径刚好是从根节点到父节点。保存路径的数据结构实际上是一个栈，因为路径要与递归调用状态一直，而递归调用的本质
 * 就是一个压栈和出栈的过程
 */
public class Q34_二叉树中和为某一值的路径 {

    public static void main(String[] args) {
        TreeNode treeNode10 = new TreeNode(10);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode12 = new TreeNode(12);
        treeNode5.left = treeNode4;
        treeNode5.right = treeNode7;
        treeNode10.left = treeNode5;
        treeNode10.right = treeNode12;
        findPath(treeNode10,22);
    }

    public static void findPath(TreeNode root, int expectedSum) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> path = new Stack<>();
        int currentSum = 0;
        findPath(root, expectedSum, path, currentSum);
    }

    private static void findPath(TreeNode root, int expectedSum, Stack<TreeNode> path, int currentSum) {
        currentSum += root.val;
        path.push(root);
        //如果是叶节点，并且路径上节点值的和等于输入的值，则打印出这条路径
        boolean isLeaf = root.left == null && root.right == null;
        if (currentSum == expectedSum && isLeaf) {
            for(TreeNode tree: path){
                System.out.println(" " + tree.val);
            }
        }
        //如果不是叶节点，则遍历它的子节点
        if (root.left != null)
            findPath(root.left, expectedSum, path, currentSum);
        if (root.right != null)
            findPath(root.right, expectedSum, path, currentSum);
        //在返回父节点之前，在路径上删除当前节点
        path.pop();
    }

}
