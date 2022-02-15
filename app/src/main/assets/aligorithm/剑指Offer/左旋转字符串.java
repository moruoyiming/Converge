package com.algorithm.demo.剑指Offer;

/**
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现左旋转操作的功能呢。
 * 例如：输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 * 可以将字符串分为两个部分，由于想把它的前两个字符移到后面，就把前两个字符分到第一部分，把后面的所有字符分到第二部分。
 * 先分别旋转这两部分，得到"bagfedc"。接下来反转整个字符串，得到"cdefgab"刚好把原始字符串左旋转两位的结果。
 */
public class 左旋转字符串 {

    public static void main(String[] args) {
        String str = "abcdefg";
        String result = leftRotateString(str, 2);
//        System.out.println("result=" + result);
    }

    public static String leftRotateString(String str, int n) {
        if (str != null && !"".equals(str) && n >= 0) {
            char[] array = str.toCharArray();
            //翻转字符串的前面n个字符
            reverse(array, 0, n - 1);
            System.out.println("result1=" + new String(array));
            //翻转字符串的后面部分
            reverse(array, n, array.length - 1);
            System.out.println("result2=" + new String(array));
            //翻转整个字符串
            reverse(array, 0, array.length - 1);
            System.out.println("result=" + new String(array));
        }
        return str;
    }

    public static void reverse(char[] chars, int start, int end) {
        while (start <= end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }

}
