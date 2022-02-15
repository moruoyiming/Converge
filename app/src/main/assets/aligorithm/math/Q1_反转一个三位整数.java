package com.algorithm.demo.math;

public class Q1_反转一个三位整数 {

    public static void main(String[] args) {
        int result = reverseInteger(3243);
        System.out.println("result=" + result);
    }

    /**
     * @param number: A 3-digit number.
     * @return: Reversed number.
     */
    public static int reverseInteger(int number) {
        if (number == 0) {
            return 0;
        }
        int result = 0;
        while (number != 0) {
            result = result * 10 + number % 10;
            number = number / 10;
        }
        return result;
    }

}
