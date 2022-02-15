package com.algorithm.demo.剑指Offer;

/**
 * 用一条语句判断一个整数是不是2的整数次方。一个整数如果是2的整数次方，那么它的二进制表示中有且只有一位是1，而其他所有位都是0。根据前面的分析，
 * 把这个整数减去1之后再和它自己做与运算，这个整数中唯一的1就会变成0。
 */
public class 判断一个整数是不是2的整数次方 {

    public static void main(String[] args) {
        boolean result = checkNums(8);
        System.out.println("result=" + result);
    }

    public static boolean checkNums(int n) {
        if (((n - 1) & n) == 0) {
            return true;
        }
        return false;
    }


}
