package com.algorithm.demo.math;

/**
 * 326. 3 的幂
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3^x
 * 示例 1：
 * 输入：n = 27
 * 输出：true
 * 示例 2：
 * 输入：n = 0
 * 输出：false
 *
 */
public class Q17_3的幂 {

    /**
     * 时间复杂度：O(logn)。当 n 是 3 的幂时，需要除以 3 的次数为 log_3 n = O(logn)；当 n 不是 3 的幂时，需要除以 3 的次数小于该值。
     * 空间复杂度：O(1)。
     */
    public boolean isPowerOfThree(int n) {
        while( n != 0 && n % 3 == 0){
            n = n / 3 ;
        }
        return n == 1;
    }

}
