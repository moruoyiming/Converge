package com.algorithm.demo.matrix;

public class IntArrayTest {
    public static void main(String args[]) {
        int intArray[][] = {{1, 2, 9, 10, 25},
                {4, 3, 8, 11, 24},
                {5, 6, 7, 12, 23},
                {16, 15, 14, 13, 22},
                {17, 18, 19, 20, 21}};
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                if (intArray[i][j] / 10 == 0)
                    System.out.print(" " + intArray[i][j] + " ");
                else
                    System.out.print(intArray[i][j] + " ");
            }
            System.out.println("");
        }
    }
} 