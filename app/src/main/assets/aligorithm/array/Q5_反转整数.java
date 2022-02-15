package com.algorithm.demo.array;

/**
 * 给定一个 32 位有符号整数，将整数中的数字进行反转。
 * 示例 1:
 * <p>
 * 输入: 1234
 * 输出: 4321
 * 示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 */
public class Q5_反转整数 {

    public static void main(String[] args) {
        int what = reverse(656334);
        System.out.println(" " + what);
    }

    /**
     * 复杂度分析：
     * 时间复杂度：O(1)。
     * 空间复杂度：O(1)。
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int sum = 0;
        while (x != 0) {//656334
            int y = x % 10;
            if (sum < Integer.MIN_VALUE / 10 || sum > Integer.MAX_VALUE / 10) {
                return 0;
            }
            sum = y + sum * 10; // 4  3  3   6   5  6
            System.out.println(" sum= " + sum);
            x = x / 10;// 65633  6563  656  65  6
            System.out.println(" x= " + x);
        }
        return sum;
    }

    public static int reverse2(int x) {
        int sum = 0;
        while (x != 0) {
            int y = x % 10;
            if (sum < Integer.MIN_VALUE / 10 || sum > Integer.MAX_VALUE / 10) {
                return 0;
            }
            sum = y + sum * 10;
            x = x / 10;
        }
        return sum;
    }

    public static int reverse3(int x) {
        int sum = 0;
        while (x != 0) {
            int y = x % 10;
            if (sum > Integer.MIN_VALUE / 10 || sum > Integer.MAX_VALUE / 10) {
                return 0;
            }
            sum = y + sum * 10;
            x = x / 10;
        }
        return sum;
    }

}