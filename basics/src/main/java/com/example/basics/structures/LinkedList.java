package com.example.basics.structures;

//单链表
public class LinkedList<T> {

	Node list;
	int size; //链表有多少个节点

	public LinkedList() {
		size = 0;
	}

	//添加节点
	//在头部添加节点
	public void put(T data) {
		Node head = list;
		Node curNode = new Node(data, list);
		list = curNode;
		size++;
	}

	//在链表的index 位置插入一个新的数据data
	public void put(int index,T data) {
		checkPositionIndex(index);
		Node cur = list;
		Node head = list;
		for(int i = 0; i < index; i++) {
			head = cur;
			cur = cur.next;
		}
		Node node = new Node(data, cur);
		head.next = node;
		size++;

	}

	//删除节点
	//删除头部节点
	public T remove() {
		if (list != null) {
			Node node = list;
			list = list.next;
			node.next = null; // GC 回收
			size--;
			return node.data;
		}
		return null;
	}

	public T remove(int index) {
		checkPositionIndex(index);
		Node head = list;
		Node cur = list;
		for(int i = 0; i < index; i++) {
			head = cur;
			cur = cur.next;
		}
		head.next = cur.next;
		cur.next = null;//GC
		return cur.data;
	}

	public T removeLast() {
		if (list != null) {
			Node node = list;
			Node cur = list;
			while(cur.next != null) {
				node = cur;
				cur = cur.next;
			}
			node.next = null;
			size--;
			return cur.data;

		}
		return null;
	}
	//修改节点
	public void set(int index,T newData) {
		checkPositionIndex(index);
		Node head = list;
		for(int i = 0; i < index; i++) {
			head = head.next;
		}
		head.data = newData;
	}

	//查询节点
	//get 头部节点
	public T get() {
		Node node = list;
		if (node != null) {
			return node.data;
		} else {
			return null;
		}
	}

	public T get(int index) {
		checkPositionIndex(index);
		Node node = list;
		for(int i = 0; i < index; i++) {
			node = node.next;
		}
		return node.data;
	}

	//检测index是否在链表范围以内
	public void checkPositionIndex(int index) {
		if(!(index >=0 && index <=size)) {
			throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
		}

	}

	@Override
	public String toString() {
		Node node = list;
		for (int i = 0; i < size; i++) {
			System.out.print(node.data + " ");
//			System.out.print(" ");
			node = node.next;
		}
		System.out.println();
		return super.toString();
	}
	//节点的信息
	class Node {
		T data;
		Node next;

		public Node(T data,Node node) {
			this.data = data;
			this.next = node;
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
