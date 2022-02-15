package com.algorithm.demo.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 50 · 数组剔除元素后的乘积
 * 描述
 * 给定一个整数数组A。
 * 定义B[i] = A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]B[i]=A[0]∗...∗A[i−1]∗A[i+1]∗...∗A[n−1]， 计算B的时候请不要使用除法。请输出B。
 * <p>
 * 样例
 * 样例 1：
 * <p>
 * 输入：
 * <p>
 * A = [1,2,3]
 * 输出：
 * <p>
 * [6,3,2]
 * 解释：
 * <p>
 * B[0] = A[1] * A[2] = 6; B[1] = A[0] * A[2] = 3; B[2] = A[0] * A[1] = 2
 */
public class Q57_数组剔除元素后的乘积 {

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        productExcludeItself(nums);
    }

    /*
     * @param nums: Given an integers array A
     * @return: A long long array B and B[i]= A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]
     */
    public static List<Long> productExcludeItself(List<Integer> nums) {
        // write your code here
        List<Long> result = new ArrayList<>();
        long product = 1;
        result.add(product);

        int len = nums.size();
        for (int i = 1; i < len; i++) {
            product *= nums.get(i - 1);
            result.add(product);
            System.out.println(" ssss "+result);
        }
        product = 1;
        for (int i = len - 2; i >= 0; i--) {
            product *= nums.get(i + 1);
            result.set(i, result.get(i) * product);
            System.out.println(" cccc "+result);
        }

        return result;
    }

}
