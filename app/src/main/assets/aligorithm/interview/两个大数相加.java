package com.algorithm.demo.interview;

public class 两个大数相加 {

    public static void main(String[] args) {

        String result = addStrings("12312312314", "123123124565");
        System.out.println("result=" + result);

    }


    /**
     * @param num1: a non-negative integers
     * @param num2: a non-negative integers
     * @return: return sum of num1 and num2
     */
    public static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int a = i >= 0 ? num1.charAt(i) - '0' : 0;
            int b = j >= 0 ? num2.charAt(j) - '0' : 0;
            int temp = a + b + carry;
            carry = temp / 10;
            i--;
            j--;
            sb.append(temp % 10);
        }
        if (carry == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

}
