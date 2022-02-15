package com.algorithm.demo.array;

/**
 * 111 · 爬楼梯
 * <p>
 * 描述
 * 假设你正在爬楼梯，需要n步你才能到达顶部。但每次你只能爬一步或者两步，爬到顶部的方法有多少种？
 * <p>
 * 样例
 * 样例 1：
 * <p>
 * 输入：
 * <p>
 * n = 3
 * 输出：
 * <p>
 * 3
 */
public class Q54_爬楼梯 {

    public static void main(String[] args) {
        //1,1,1,1,1,
        //1,1,1,2
        //1,1,2,1
        //1,2,1,1
        //1,2,2
        //2,1,1,1
        //2,2,1
        //2,1,2
        int result = climbStairs(5);
        System.out.println("result=" + result);
    }

    /**
     * @param n: An integer
     * @return: An integer
     */
    public static int climbStairs(int n) {
        if (n == 0) {
            return 0;
        }
        int[] array = new int[n + 1];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }

    public int climbStairs2(int n){
        int p = 0, q = 0 , r = 1;
        for(int i = 1; i <= n ; ++i){
            p = q ;
            q = r ;
            r = p + q;
        }
        return r;
    }

}
