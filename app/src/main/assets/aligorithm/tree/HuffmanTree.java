package com.algorithm.demo.tree;
import java.util.ArrayList;
import java.util.List;
/**
 * 哈夫曼树
 */

public class HuffmanTree {
    //节点
    public static class Node<E> {
        E data; //数据
        int weight; //权重
        Node leftChild; //左子节点
        Node rightChild;//右子节点

        public Node(E data, int weight) {
            super();
            this.data = data;
            this.weight = weight;
        }

        public String toString() {
            return "Node[" + weight + ",data=" + data + "]";
        }
    }

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<Node>();
        //把节点加入至list中
        nodes.add(new Node("a", 10));
        nodes.add(new Node("b", 15));
        nodes.add(new Node("c", 12));
        nodes.add(new Node("d", 3));
        nodes.add(new Node("e", 4));
        nodes.add(new Node("f", 13));
        nodes.add(new Node("g", 1));
        //进行哈夫曼树的构造
        Node root = HuffmanTree.createTree(nodes);
        //打印哈夫曼树
        printTree(root);

    }

    /**
     * 构造哈夫曼树
     *
     * @param nodes
     *            节点集合
     * @return 构造出来的哈夫曼树的根节点
     */
    private static Node createTree(List<Node> nodes) {
        //如果节点node列表中海油2个和2个以上的节点
        while(nodes.size()>1){
            //什么是最小的，list表进行排序，增序的方式， 0,1，
            sort(nodes);//排序方式是增序的
            Node left = nodes.get(0);//权重最小的
            Node right = nodes.get(1);//权重第二小的
            //生成一个新的节点（父节点），父节点的权重为两个子节点的之和
            Node parent = new Node(null,left.weight+right.weight);
            //树的连接，让子节点与父节点进行连接
            parent.leftChild = left;
            parent.rightChild = right;
            nodes.remove(0);//删除最小的
            nodes.remove(0);//删除第二小的。
            nodes.add(parent);
        }
        return nodes.get(0); //返回根节点
    }
    /**
     * 冒泡排序，用于对节点进行排序（增序排序）
     *
     * @param nodes
     */
    public static void sort(List<Node> nodes) {
        if (nodes.size() <= 1)
            return ;
        /*循环数组长度的次数*/
        for (int i = 0; i < nodes.size(); i++){
            /*从第0个元素开始，依次和后面的元素进行比较
             * j < array.length - 1 - i表示第[array.length - 1 - i]
             * 个元素已经冒泡到了合适的位置，无需进行比较，可以减少比较次数*/
            for (int j = 0; j < nodes.size() - 1 - i; j++){
                /*如果第j个节点比后面的第j+1节点权重大，交换两者的位置*/
                if (nodes.get(j + 1).weight < nodes.get(j).weight) {
                    Node temp = nodes.get(j + 1);
                    nodes.set(j+1,nodes.get(j));
                    nodes.set(j,temp);
                }
            }
        }
        return ;
    }

    /*

     * 递归打印哈夫曼树(先左子树，后右子树打印)
     */

    public static void printTree(Node root) {
        System.out.println(root.toString());
        if(root.leftChild !=null){
            System.out.print("left:");
            printTree(root.leftChild);
        }
        if(root.rightChild !=null){
            System.out.print("right:");
            printTree(root.rightChild);
        }
    }
}
