package com.algorithm.demo.tree;

/**
 * 二叉平衡树，AVL树
 */

public class AVLTree {
    //节点
    public static class Node {
        int data; //数据

        Node leftChild; //左子节点
        Node rightChild;//右子节点
        int height; // 记录该节点所在的高度

        public Node(int data) {
            this.data = data;
        }
    }

    //获取节点的高度
    public static int getHeight(Node p){
        return p == null ? -1 : p.height; // 空树的高度为-1
    }

    public static void main(String[] args) {
        Node root = null;
        root = insert(root,30);
        root = insert(root,20);
        root = insert(root,40);
        root = insert(root,10);
        root = insert(root,25);
        //插入节点在失衡结点的左子树的左边
        root = insert(root,5);
        //打印树，按照先打印左子树，再打印右子树的方式
        printTree(root);

    }

    public static void printTree(Node root) {
        System.out.println(root.data);
        if(root.leftChild !=null){
            System.out.print("left:");
            printTree(root.leftChild);
        }
        if(root.rightChild !=null){
            System.out.print("right:");
            printTree(root.rightChild);
        }
    }

    // AVL树的插入方法
    public static Node insert(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }
        if (data <= root.data) { // 插入到其左子树上
            root.leftChild = insert(root.leftChild, data);
            //平衡调整
            if (getHeight(root.leftChild) - getHeight(root.rightChild) > 1) {
                if (data <= root.leftChild.data) { // 插入节点在失衡结点的左子树的左边
                    System.out.println("LL旋转");
                    root = LLRotate(root); // LL旋转调整
                }else{ // 插入节点在失衡结点的左子树的右边
                    System.out.println("LR旋转");
                    root = LRRotate(root);
                }
            }
        }else{ // 插入到其右子树上
            root.rightChild = insert(root.rightChild, data);
            //平衡调整
            if(getHeight(root.rightChild) - getHeight(root.leftChild) > 1){
                if(data <= root.rightChild.data){//插入节点在失衡结点的右子树的左边
                    System.out.println("RL旋转");
                    root = RLRotate(root);
                }else{
                    System.out.println("RR旋转");//插入节点在失衡结点的右子树的右边
                    root = RRRotate(root);
                }
            }
        }
        //重新调整root节点的高度值
        root.height = Math.max(getHeight(root.leftChild), getHeight(root.rightChild)) + 1;
        return root;
    }

    // LR旋转
    public static Node LRRotate(Node p){
        p.leftChild = RRRotate(p.leftChild); // 先将失衡点p的左子树进行RR旋转
        return LLRotate(p); // 再将失衡点p进行LL平衡旋转并返回新节点代替原失衡点p

    }

    // RL平衡旋转
    public static Node RLRotate(Node p){
        p.rightChild = LLRotate(p.rightChild); // 先将失衡点p的右子树进行LL平衡旋转
        return RRRotate(p); // 再将失衡点p进行RR平衡旋转并返回新节点代替原失衡点p
    }

    /*
     * LL旋转
     * 左旋示意图(对结点20进行左旋)
     *      30                       20
     *     /  \                     /  \
     *    20  40                  10   30
     *   /  \      --LL旋转-       /   /  \
     *  10   25                  5   25   40
     *  /
     * 5
     *
     */
    public static Node LLRotate(Node p){ // 30为失衡点
        Node lsubtree = p.leftChild;   //失衡点的左子树的根结点20作为新的结点
        p.leftChild = lsubtree.rightChild; //将新节点的右子树25成为失衡点30的左子树
        lsubtree.rightChild = p; // 将失衡点30作为新结点的右子树
        // 重新设置失衡点30和新节点20的高度
        p.height = Math.max(getHeight(p.leftChild), getHeight(p.rightChild)) + 1;
        lsubtree.height = Math.max(getHeight(lsubtree.leftChild), p.height) + 1;
        return lsubtree; // 新的根节点取代原失衡点的位置
    }
    /*
     * RR旋转
     * 右旋示意图(对结点30进行左旋)
     *      20                          30
     *     /  \                        /  \
     *    10  30                     20   40
     *       /  \      --RR旋转-     /  \   \
     *      25  40                 10  25  50
     *           \
     *           50
     *
     */
    // RR旋转
    public static Node RRRotate(Node p){ // 20为失衡点
        Node rsubtree = p.rightChild;  //失衡点的右子树的根结点30作为新的结点
        p.rightChild = rsubtree.leftChild; //将新节点的左子树25成为失衡点20的右子树
        rsubtree.leftChild = p; // 将失衡点20作为新结点的左子树
        // 重新设置失衡点20和新节点30的高度
        p.height = Math.max(getHeight(p.leftChild), getHeight(p.rightChild)) + 1;
        rsubtree.height = Math.max(getHeight(rsubtree.leftChild), getHeight(rsubtree.rightChild)) + 1;
        return rsubtree; // 新的根节点取代原失衡点的位置
    }


}