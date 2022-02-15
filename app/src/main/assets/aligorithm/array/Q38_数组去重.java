package com.algorithm.demo.array;

import java.util.*;

/**
 * 296. 数组去重
 * 给定一个长度为NN的整数数组arrarr，返回去掉重复元素之后的数组（去掉重复元素前后数组元素相对次序不变）
 * <p>
 * 样例
 * 输入:[3,4,3,6]
 * 输出:[3,4,6]
 * 解释:元素3重复，所以只需要保留一个元素3
 * 而且去重前后数组元素相对次序不变，所以元素4还在元素3后面，元素6还在元素3，4后面
 * <p>
 * 注意事项
 * 1 \leq N \leq 10^51≤N≤10
 * ​5
 * ​​
 * -10^9 \leq arr[i] \leq 10^9−10
 * ​9
 * ​​ ≤arr[i]≤10
 * ​9
 * ​​
 */
public class Q38_数组去重 {
    public static void main(String[] args) {
//        int[] nums = {3, 4, 3, 6};
        int[] nums = {0, 7, 2, 2, 6, 7, 6, 9, 4};
        getUniqueArray(nums);
    }

    /**
     * @param arr: a integer array
     * @return: return the unique array
     */
    public static int[] getUniqueArray(int[] arr) {
        //HashMap
        Map<Integer, Boolean> isVisited = new HashMap<>();
        //数组长度
        int n = arr.length;
        //答案数组
        ArrayList<Integer> uniqueArray = new ArrayList<>();
        //遍历整个数组，通过isVisited这个Map判断这个数有没有出现过
        //如果出现了则跳过，没有跳过，就把这个数压入答案数组
        for (int i = 0; i < n; i++) {
            if (!isVisited.containsKey(arr[i])) {
                isVisited.put(arr[i], true);
                uniqueArray.add(arr[i]);
            }
        }
        return uniqueArray.stream().mapToInt(Integer::valueOf).toArray();
    }
}
