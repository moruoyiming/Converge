package com.algorithm.demo.matrix;

public class 多维数组 {

    public static void main(String[] args) {
        method1();
    }

    public static void method1() {
        int intArray[][] = {{1, 2}, {3, 4}, {5, 6}};
        for (int i = 0; i < intArray.length; i++) {
            for (int j = 0; j < intArray[i].length; j++) {
                System.out.print("   "+intArray[i][j]);
            }
            System.out.println();
        }

    }
}
