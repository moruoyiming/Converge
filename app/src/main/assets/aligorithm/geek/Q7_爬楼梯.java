package com.algorithm.demo.geek;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class Q7_爬楼梯 {

    public int climbStairs(int n) {
        int[] numbers = new int[n + 1];
        numbers[0] = 1;
        numbers[1] = 1;
        for(int i = 2; i <= n ; i++){
            numbers[i] = numbers[i - 1] + numbers[i - 2 ];
        }
        return numbers[n];
    }

    public int climbStairs2(int n){
        int p = 0 , q = 0 , r = 1;
        for(int i = 1 ;i <= n ; ++i ){
            p = q;
            q = r;
            r = p + q ;
        }
        return r;
    }
}
