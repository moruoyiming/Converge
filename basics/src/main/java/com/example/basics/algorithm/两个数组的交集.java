package com.example.basics.algorithm;

import java.util.HashSet;
import java.util.Set;

public class 两个数组的交集 {

    public static void main(String[] args) {
//        int[] a = {1, 2, 2, 1};
//        int[] b = {2, 2};

        int[] a = {4,9,5};
        int[] b = {9,4,9,8,4};
        method(a, b);

    }

    public static void method(int[] a, int[] b) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    set.add(a[i]);
                }
            }
        }
        System.out.println("交集为：");
        int[] nums = new int[set.size()];
        int i = 0;
        for (int n : set) {
            nums[i] = n;
            i++;
            System.out.println("    "+n);
        }
    }


}
