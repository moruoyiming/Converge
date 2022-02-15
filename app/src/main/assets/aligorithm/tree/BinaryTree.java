package com.algorithm.demo.tree;

/**
 * 二叉树
 * 前序遍历，对树中的任意结点来说，先打印这个结点，然后前序遍历它的左子树，最后前序遍历它的右子树。
 * 中序遍历，对树中的任意结点来说，先中序遍历它的左子树，然后打印这个结点，最后中序遍历它的右子树。
 * 后序遍历，对树中的任意结点来说，先后序遍历它的左子树，然后后序遍历它的右子树，最后打印它本身。
 *
 */
public class BinaryTree {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree("A");
        binaryTree.createTree();
        binaryTree.midOrderTraverse(binaryTree.root);
        binaryTree.preOrderTraverse(binaryTree.root);
        binaryTree.postOrderTraverse(binaryTree.root);
    }

    Node<String> root;

    public BinaryTree(String data) {
        //初始化根结点
        this.root = new Node<>(data, null, null);
    }


    /**
     *        A
     *      /   \
     *    B      C
     *  /  \    / \
     * D    E  F   G
     * 前序遍历：根结点 ---> 左子树 ---> 右子树
     * 中序遍历：左子树 ---> 根结点 ---> 右子树
     * 后序遍历：左子树 ---> 右子树 ---> 根结点
     * 前序遍历 1A  2B  3D  4E  5C  6F  7G
     * 中序遍历 1D  2B  3E  4A  5F  6C  7G
     * 后序遍历 1D  2E  3B  4F  5G  6C  7A
     */
    public class Node<T> {
        T data;//数据
        Node<T> leftChild;//左子结点
        Node<T> rightChild;//右子结点

        public Node(T data, Node<T> leftChild, Node<T> rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

    /**
     * 创建一棵二叉树
     */
    public void createTree() {
        Node<String> nodeB = new Node<String>("B", null, null);
        Node<String> nodeC = new Node<String>("C", null, null);
        Node<String> nodeD = new Node<String>("D", null, null);
        Node<String> nodeE = new Node<String>("E", null, null);
        Node<String> nodeF = new Node<String>("F", null, null);
        Node<String> nodeG = new Node<String>("G", null, null);
        root.leftChild = nodeB;
        root.rightChild = nodeC;
        nodeB.leftChild = nodeD;
        nodeC.leftChild = nodeE;
        nodeC.rightChild = nodeF;
    }

    /**
     * 中序遍历
     */
    public void midOrderTraverse(Node root) {
        if (root == null) {
            return;
        }
        midOrderTraverse(root.leftChild);
        System.out.println("mid: " + root.data);
        midOrderTraverse(root.rightChild);
    }


    /**
     * 前序遍历
     */
    public void preOrderTraverse(Node root) {
        if (root == null) {
            return;
        }
        System.out.println("pre: " + root.data);
        preOrderTraverse(root.leftChild);
        preOrderTraverse(root.rightChild);
    }

    /**
     * 后序遍历
     */
    public void postOrderTraverse(Node root) {
        if (root == null) {
            return;
        }
        postOrderTraverse(root.leftChild);
        postOrderTraverse(root.rightChild);
        System.out.println("post: " + root.data);
    }
}
