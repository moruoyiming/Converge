package com.example.basics.algorithm;

//������
public class LinkedList<T> {
	
	Node list;
	int size; //�����ж��ٸ��ڵ�
	
	public LinkedList() {
		size = 0;
	}
	
	//��ӽڵ�
	//��ͷ����ӽڵ�
	public void put(T data) {
		Node head = list;
		Node curNode = new Node(data, list);
		list = curNode;
		size++;
	}
	
	//�������index λ�ò���һ���µ�����data
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

	//ɾ���ڵ�
	//ɾ��ͷ���ڵ�
	public T remove() {
		if (list != null) {
			Node node = list;
			list = list.next;
			node.next = null; // GC ����
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
	//�޸Ľڵ�
	public void set(int index,T newData) {
		checkPositionIndex(index);
		Node head = list;
		for(int i = 0; i < index; i++) {
			head = head.next;
		}
		head.data = newData;
	}
	
	//��ѯ�ڵ�
	//get ͷ���ڵ�
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
	
	//���index�Ƿ�������Χ����
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
	//�ڵ����Ϣ
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
