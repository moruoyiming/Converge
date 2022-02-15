package com.algorithm.demo.iq;

/**
 * 254 · 丢鸡蛋
 * 题目题解笔记讨论排名
 * 描述
 * 楼有 n 层高，鸡蛋若从 k 层或以上扔，就会碎。从 k 层以下扔，就不会碎。
 *
 * 现在给两个鸡蛋，用最少的扔的次数找到 k。返回最坏情况下次数。
 *
 * 对于 n = 10， 一种找 k 的初级方法是从 1、2 ... k 层不断找。但最坏情况下要扔 10 次。
 *
 * 注意有两个鸡蛋可以使用，所以可以从 4、7、9 层扔。这样最坏就只需要 4 次 (例如 k = 9 时)。
 *
 * 样例
 * 样例 1：
 *
 * 输入：100
 * 输出：14
 */
public class 丢鸡蛋 {

    public static void main(String[] args) {

    }

    /**
     * @param n: An integer
     * @return: The sum of a and b
     */
    public int dropEggs(int n) {
        // write your code here
        long x = 2 * (long)n;
        long sqrt = (long)Math.sqrt(x);
        if(sqrt*(sqrt+1) >= x){
            return (int)sqrt;
        }else{
            return (int)(sqrt + 1);
        }
    }

}
