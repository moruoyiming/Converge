package com.algorithm.demo.array;

import java.util.HashSet;

/**
 * 547 · 两数组的交集
 * 描述
 * 给出两个数组，写出一个方法求出它们的交集
 *
 * 结果中的每个元素必须是唯一的。
 * 样例
 * 例1:
 *
 * 输入: nums1 = [1, 2, 2, 1], nums2 = [2, 2],
 * 输出: [2].
 */
public class Q47_两数组的交集 {

    /**
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1==null || nums2 ==null){
            return null;
        }
        HashSet<Integer> hash1 = new HashSet<Integer>();
        for(int i = 0 ; i < nums1.length ; i++){
            hash1.add(nums1[i]);
        }

        HashSet<Integer> resultHash = new HashSet<Integer>();
        for(int j = 0 ; j < nums2.length ; j++){
            if(hash1.contains(nums2[j]) && !resultHash.contains(nums2[j])){
                resultHash.add(nums2[j]);
            }
        }
        int size = resultHash.size();
        int[] result = new int[size];
        int index = 0;
        for (Integer num : resultHash) {
            result[index++] = num;
        }

        return result;
    }

}
