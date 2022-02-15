package com.algorithm.demo.array;

/**
 * 描述
 * 计算数字 k 在 0 到 n 中出现的次数，k 可能是 0~9 的一个值。
 * <p>
 * 样例
 * 样例 1：
 * <p>
 * 输入：
 * <p>
 * k = 1
 * n = 1
 * 输出：
 * <p>
 * 1
 * 解释：
 * <p>
 * 在 [0, 1] 中，我们发现 1 出现了 1 次 (1)。
 * <p>
 * 样例 2：
 * <p>
 * 输入：
 * <p>
 * k = 1
 * n = 12
 * 输出：
 * <p>
 * 5
 * 解释：
 * <p>
 * 在 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12] 中，我们发现 1 出现了 5 次 (1, 10, 11, 12)(注意11中有两个1)。
 */
public class Q11_统计数字 {

    public static void main(String[] args) {
        int result = digitCounts(1,12);
        System.out.println("result="+result);
    }
    /**
     * @param k: An integer
     * @param n: An integer
     * @return: An integer denote the count of digit k in 1..n
     */
    public static int digitCounts(int k, int n) {
        //题目要求是输出0到n之间所有数中包含k这个数字的个数，理所当然需要遍历每个数进行统计
        //因为k只能是0-9之间的数字，k之前的数绝对不会出现k这个数字
        //因为从k开始必定第一个含有k的数就是它本身，所以直接从它的下一个数开始遍历，并设定一个初始值为1的计数变量
        //但是如果所输入的n比k还小，则0-n之间绝无可能出现k数字，直接返回0
        if (n < k) {
            return 0;
        }
        int count = 1;
        int temp;
        //如果k=n，则不会进入循环，返回初始值为1的count
        for (int i = k + 1; i <= n; i++) {
            //使用一个临时变量，因为后面对i进行变值会影响到循环的进行
            temp = i;
            while (temp > 0) {
                if (temp % 10 == k) {
                    count++;
                }
                temp /= 10;
            }
        }
        return count;
    }
}
