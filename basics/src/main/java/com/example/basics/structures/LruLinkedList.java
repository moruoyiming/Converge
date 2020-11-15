package com.example.basics.structures;

public class LruLinkedList<T> extends LinkedList<T> {

	int memory_size; // 用于限定内存空间大小，也就是缓存的大小
	static final int DEFAULT_CAP = 5;

	public LruLinkedList() {
		this(DEFAULT_CAP);
	}

	public LruLinkedList(int default_memory_size) {
		memory_size = default_memory_size;
	}

	//LRU添加节点
	public void lruPut(T data) {
		if (size >= memory_size) {
			removeLast();
			put(data);
		} else {
			put(data);
		}
	}

	//LRU删除
	public T lruRemove(){
		return removeLast();
	}

	//LRU访问
	public T lruGet(int index) {
		checkPositionIndex(index);
		Node node = list;
		Node pre = list;
		for(int i = 0; i < index; i++) {
			pre = node;
			node = node.next;
		}
		T resultData = node.data;
		//将访问的节点移到表头
		pre.next = node.next;
		Node head = list;
		node.next = head;
		list = node;
		return resultData;
	}


	public static void main(String[] args) {
		LruLinkedList<Integer> lruLinkedList = new LruLinkedList<>(5);
		for(int i = 0; i <4; i++) {
			lruLinkedList.lruPut(i);
		}
		lruLinkedList.toString();
		System.out.println(lruLinkedList.lruGet(3));
		lruLinkedList.toString();
		lruLinkedList.lruPut(20);
		lruLinkedList.toString();

		lruLinkedList.lruPut(18);
		lruLinkedList.toString();

	}

}
