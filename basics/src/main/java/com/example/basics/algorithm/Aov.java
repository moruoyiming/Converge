package com.example.basics.algorithm;

import java.util.Stack;

public class Aov {
	//节点数目
	protected int size;
	//定义数组，保存顶点信息
	protected String[] nodes;

	//定义矩阵保存顶点信息
	protected int[][] edges;

	public Aov(){
		init();
	}

	//入度数组
	private int[] eSize;
	private int[] fast;//最早时间
	private int[] last;//最晚时间
	public static void main(String[] args) {
		Aov aov = new Aov();
		aov.flush();
//		int[] path = aov.getPath();
		aov.exeKey();
	}

	public void exeKey(){
		int[] path = getPath();
		int start = path[0],end = path[size-1];

		exeFast(start);

		for (int i=0;i<size;i++){//初始化成工程最大值
			last[i] = fast[end];
		}
		exeLast(end);

		for (int i=0;i<size;i++){
			int node = path[i];
			if (fast[node] == last[node]){
				System.out.print("--->"+nodes[node]);
			}
		}

		System.out.println();
	}


	private void exeFast(int node){
		for (int i=0;i<size;i++){
			if (this.edges[node][i] > 0){
				int cost = fast[node] + this.edges[node][i];
				if (cost > fast[i]){
					fast[i] = cost;
					exeFast(i);
				}
			}
		}
	}

	private void exeLast(int node){
		for (int i=0;i<size;i++){
			if (this.edges[i][node] > 0){
				int cost = last[node] - this.edges[i][node];
				if (cost < last[i]){
					last[i] = cost;
					exeLast(i);
				}
			}
		}
	}


	//1、计算出各个节点的入度
	private void flush(){
		eSize = new int[size];

		for (int node=0;node<size;node++){
			for (int i=0;i<size;i++){
				if (edges[i][node] > 0){
					eSize[node]++;
				}
			}
		}
	}

	private int[] getPath(){
		int count = 0;
		int[] path = new int[size];

		//	2、入度为0节点入队
//		Queue<Integer> queue = new LinkedList<>();
		Stack<Integer> stack = new Stack<>();
		for (int i=0;i<size;i++){
			if (eSize[i] == 0){
//				queue.offer(i);
				stack.push(i);
			}
		}

		//	3、入队节点的邻接点入度-1
		while (!stack.empty()){
			Integer node = stack.pop();

//			System.out.print("---->"+nodes[node]);
			path[count++] = node;

			for (int i=0;i<size;i++){
				if (this.edges[node][i] > 0){
					eSize[i]-- ;
					if (eSize[i] == 0){
//						queue.offer(i);
						stack.push(i);
					}
				}
			}
		}


		return path;
	}




	public void init(){
		//初始化顶点
		nodes = new String[]{"AA","A","B","C","D","E","F","G","H","M","K","N"};
		//节点编号-常量
		final int AA=0,A=1,B=2,C=3,D=4,E=5,F=6,G=7,H=8,M=9,K=10,N=11;
		size=nodes.length;

		fast = new int[size];
		last = new int[size];

		edges = new int[size][size];
		edges[AA][A] = 3;
		edges[AA][B] = 2;
		edges[AA][C] = 5;
		edges[A][D] = 4;
		edges[B][G] = 2;
		edges[B][E] = 3;
		edges[C][E] = 2;
		edges[C][F] = 3;
		edges[D][G] = 1;
		edges[E][K] = 1;
		edges[E][M] = 8;
		edges[F][K] = 4;
		edges[G][H] = 2;
		edges[H][M] = 3;
		edges[K][N] = 2;
		edges[M][N] = 3;
	}


}
