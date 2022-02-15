package com.algorithm.demo.剑指Offer;

/**
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 加减乘除四则运算符号
 * 位运算
 * 5的二进制是101，17的二进制是10001.
 * 第一步：各位相加但不计进位，得到的结果是10100(最后一位两个数是1，相加的结果是二进制的10.这一步不计进位，因此结果仍然是0)
 * 第二步：记下进位，在这个例子中只在最后一位相加产生一个进位，结果是二进制的10
 * 第三步：把前两步的结果相加，得到的结果是10110，转换成十进制正好是22.
 */
public class Q65_不用加减乘除做加法 {

    public static void main(String[] args) {
        int result = Add(5, 17);
        System.out.println("result="+result);
    }

    public static int Add(int num1, int num2) {
        int sum, carry;
        do {
            sum = num1 ^ num2;
            carry = (num1 & num2) << 1;
            num1 = sum;
            num2 = carry;
        } while (num2 != 0);
        return num1;
    }

}
