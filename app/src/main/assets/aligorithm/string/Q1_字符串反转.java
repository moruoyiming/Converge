package com.algorithm.demo.string;

/**
 * 1283. 翻转字符串
 * 写一个方法，接受给定字符串作为输入，返回将这个字符串逐个字符翻转后的新字符串。
 * <p>
 * 样例
 * 样例 1：
 * <p>
 * 输入："hello"
 * 输出："olleh"
 * 样例 2：
 * <p>
 * 输入："hello world"
 * 输出："dlrow olleh"
 * <p>
 * string:  Hello
 * length:  5
 * <p>
 * 0 1 2 3 4
 * before: H e l l o
 * after:  o l l e H
 * <p>
 * index             sum
 * 0: H--->o  0-->4  4
 * 1: e--->l  1-->3  4
 * 2: l--->l  2-->2  4
 */
public class Q1_字符串反转 {
    public static void main(String[] args) {
        String before = "hello world";
        String after = reverseString2(before);
        System.out.println(" " + after);
    }

    /**
     * @param string: a string
     * @return: return a string
     */
    public static String reverseString(String string) {
        if (string == null || string.length() == 0) return string;
        int length = string.length();
        char[] temp = string.toCharArray();
        for (int i = 0; i < length; i++) {
            temp[i] = string.charAt(length - i - 1);
        }
        return new String(temp);
    }

    /**
     * @param string: a string
     * @return: return a string
     */
    public static String reverseString2(String string) {
        // write your code here
        if (string == null || string.length() == 0) return string;
        int length = string.length();
        char[] temp = string.toCharArray();
        for (int i = 0; i < length / 2; i++) {
            temp[i] = string.charAt(length - i - 1);
            temp[length - i - 1] = string.charAt(i);
        }
        return new String(temp);
    }
}
