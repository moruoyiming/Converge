package com.algorithm.demo.array;

import java.util.HashMap;

/**
 * 1910 · 数组中出现次数最多的值
 * 描述
 * 在给定的数组中，找到出现次数最多的数字。
 * 出现次数相同时，返回数值最小的数字。
 * <p>
 * 输入：
 * [1,1,2,3,3,3,4,5]
 * 输出：
 * 3
 */
public class Q49_数组中出现次数最多的值 {

    public static void main(String[] args) {
        int[] array = {1, 1, 2, 3, 3, 3, 4, 5};
        System.out.println(" " + findNumber(array));
    }

    /**
     * @param array: An array.
     * @return: An integer.
     */
    public static int findNumber(int[] array) {
        int result = 0;
        int max = 0;
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            System.out.println(" key =" + array[i]);
            if (!hash.containsKey(array[i])) {
                System.out.println(" key =" + array[i] + " value=" + 1);
                hash.put(array[i], 1);
            } else {
                hash.put(array[i], hash.get(array[i]) + 1);
                System.out.println(" key =" + array[i] + " value=" + (hash.get(array[i]) + 1) + "max=" + max + "result=" + result);
            }
            if (hash.get(array[i]) > max) {
                max = hash.get(array[i]);
                result = array[i];
            } else if (hash.get(array[i]) == max && array[i] < result) {
                result = array[i];
            }
        }
        return result;
    }

}
