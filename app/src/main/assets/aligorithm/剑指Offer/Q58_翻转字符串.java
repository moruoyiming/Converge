package com.algorithm.demo.剑指Offer;

/**
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
 * 例如输入字符串“I am a student.”,则输出"student. a am I"。
 * .tneduts a ma I    student. a am I
 */
public class Q58_翻转字符串 {


    public static void main(String[] args) {
        reverseSentence("I am a student.");
    }



    public static String reverseSentence(String str) {
        if (str == null)
            return null;
        int left = 0;
        int right = str.length() - 1;
        //翻转整个句子
        reverse(str, left, right);
        //翻转所有单词
        return "";
    }

    public static void reverse(String str, int left, int right) {
        char[] chars = str.toCharArray();
        while (left <= right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        System.out.println(" " + new String(chars));
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

}
