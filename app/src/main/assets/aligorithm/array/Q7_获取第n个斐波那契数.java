package com.algorithm.demo.array;

/**
 * 获取第n个斐波那契数
 * 前提基准：
 * f(0)=0; // 第0个斐波那契数是0
 * f(1)=1; // 第1个斐波那契数是1
 * f(2)=1; // f(2)=f(1)+f(0) // 第2个斐波那契数是1
 */
public class Q7_获取第n个斐波那契数 {

    public static void main(String[] args) {
        int result = getFibo(30);
        System.out.println("result=" + result);

    }

    /**
     * 0，1，1，2，3，5，8，13，21，34，55，89，144，233，377，610，987
     * 这个数列从第3项开始，每一项都等于前两项之和。
     *
     * @param n
     * @return
     */
    private static int getFibo(int n) {
        if (n == 0 || n == 1)
            return 0;
        int[] array = new int[n];
        array[0] = 0; // 第0个斐波那契数是0
        array[1] = 1; // 第1个斐波那契数是1
        System.out.println(" " + array[0]);
        System.out.println(" " + array[1]);
        for (int i = 2; i < array.length; i++) {
            array[i] = array[i - 1] + array[i - 2];
            System.out.println(" " + array[i]);
        }
        return array[n - 1];
    }

    private static int getFibo2(int n) {
        if (n == 0 || n == 1) {
            return 0;
        }
        int[] array = new int[n];
        array[0] = 0;
        array[1] = 0;
        for (int i = 2; i < array.length; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n - 1];
    }

}
