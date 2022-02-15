package com.algorithm.demo.剑指Offer;

/**
 * 求斐波那契数列的第n项
 * 写入一个函数，输入n，求斐波那契数列的第n项。
 * <p>
 * f(n) ={
 * 0    n = 0
 * 1    n = 1
 * f(n-1)(n-2) n > 1
 * }
 * <p>
 * 三种解决方案：
 * 1.递归调用 ：效率低。求解f(10) 想求得f(10)，需要先求得f(9) 和 f(8).同样求f(9),首先求得f(8) f(7).
 * 2.递归循环调用 时间复杂度 O(n)
 * 3.转换为如何求矩阵 1  1 时间复杂度 O(logn)
 *                 1  0
 * f(n)   f(n-1)     1   1
 * f(n-1) f(n-2)     1   0
 *
 */
public class Q10_斐波那契数列 {

    /**
     * 递归方案
     * 时间复杂度
     * @param n
     */
    public long Fibonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 1) {
            return 0;
        }
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    /**
     * 循环
     * 时间复杂度 O(n)
     * @param n
     * @return
     */
    public long Fibonacci2(int n) {
        int nums[] = new int[n];//产生一个整数数组
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                nums[i] = 0;
            } else if (i <= 2) {
                nums[i] = 1;//前两个数为1
            } else {
                nums[i] = nums[i - 1] + nums[i - 2];//第三个数开始等于前两个数之和
            }
            System.out.print(nums[i] + ",");//打印输出
        }
        return nums[n-1];
    }

}
