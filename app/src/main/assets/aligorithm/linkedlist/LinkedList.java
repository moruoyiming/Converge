package com.algorithm.demo.linkedlist;

public class LinkedList<T> {

    Node list;
    int size;

    public LinkedList() {
        size = 0;
    }

    //增加节点
    //从头部增加节点
    public void put(T data) {
        Node curr = new Node(data, list);
        list = curr;
        size++;
    }

    //在index处增加节点
    public void put(int index, T data) {
        checkPositionIndex(index);
        Node curr = list;
        Node head = list;
        for (int i = 0; i < index; i++) {
            head = curr;
            curr = curr.next;
        }
        Node node = new Node(data, curr);
        head.next = node;
        size++;
    }

    //删除节点
    //删除头部节点
    public T remove() {
        if (list != null) {
            Node node = list;
            list = node.next;
            node.next = null;
            size--;
            return node.data;
        }
        return null;
    }

    public T remove(int index) {
        checkPositionIndex(index);
        Node head = list;
        Node curr = list;
        for (int i = 0; i < index; i++) {
            head = curr;
            curr = curr.next;
        }
        head.next = curr.next;
        curr.next = null;

        return curr.data;
    }

    public T removeLast() {
        if (list != null) {
            Node pre = list;
            Node curr = list;
            while (curr.next != null) {
                pre = curr;
                curr = curr.next;
            }
            pre.next = null;
            size--;
            return curr.data;
        }
        return null;
    }

    public void set(int index, T newdata) {
        checkPositionIndex(index);
        Node head = list;
        for (int i = 0; i < index; i++) {
            head = head.next;
        }
        head.data = newdata;
    }

    public T get() {
        Node node = list;
        if (node != null) {
            return node.data;
        }
        return null;
    }

    public T get(int index) {
        checkPositionIndex(index);
        Node curr = list;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.data;
    }


    public void checkPositionIndex(int index) {
        if (!(index >= 0 && index <= size)) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
    }

    @Override
    public String toString() {
        Node node = list;
        for (int i = 0; i < size; i++) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
        return super.toString();
    }

    class Node {
        T data;
        Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 0; i < 5; i++) {
            list.put(i);
        }
        list.toString();
        list.put(3,3);
        list.toString();
        list.put(8);
        list.toString();
        System.out.println(list.get(2));
    }

}
