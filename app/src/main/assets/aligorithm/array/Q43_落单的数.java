package com.algorithm.demo.array;

/**
 * 82 · 落单的数
 * 描述
 * 给出 2 * n + 1个数字，除其中一个数字之外其他每个数字均出现两次，找到这个数字。
 * <p>
 * n≤100
 * 样例
 * 样例 1：
 * <p>
 * 输入：
 * <p>
 * A = [1,1,2,2,3,4,4]
 * 输出：
 * <p>
 * 3
 */
public class Q43_落单的数 {

    public static void main(String[] args) {
        int[] what = {1, 1, 2, 2, 3, 4, 4};
        int result = singleNumber(what);
        System.out.println("result="+result);
    }

    /**
     * @param A: An integer array
     * @return: An integer
     */
    public static int singleNumber(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int rst = 0;
        for (int i = 0; i < A.length; i++) {
            rst ^= A[i];
            System.out.println(" rst " + rst);
        }
        return rst;
    }

}
