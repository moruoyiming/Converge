package com.example.basics.algorithm;

public class LogUtils {
    public static void log(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.print("     " + A[i]);
        }
        System.out.println("");
    }

    public static void log2(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.print("_____" + A[i]);
        }
        System.out.println("");
    }
}
