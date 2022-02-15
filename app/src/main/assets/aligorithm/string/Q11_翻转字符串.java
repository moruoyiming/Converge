package com.algorithm.demo.string;

/**
 *
 * 53 · 翻转字符串
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * 样例
 * 样例 1：
 *
 * 输入：
 *
 * s = "the sky is blue"
 * 输出：
 *
 * "blue is sky the"
 * 解释：
 *
 * 返回逐字反转的字符串。
 */
public class Q11_翻转字符串 {

    public static void main(String[] args) {
        String str = "How are you?";
        String temp = reverseWords(str);
    }

    /*
     * @param s: A string
     * @return: A string
     */
    public static String reverseWords(String s) {
        // write your code here
        if(s.length() == 0 || s == null){
            return " ";
        }
        //按照空格将s切分
        String[] array = s.split(" ");
        StringBuilder sb = new StringBuilder();
        //从后往前遍历array，在sb中插入单词
        for(int i = array.length - 1; i >= 0; i--){
            if(!array[i].equals("")) {
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(array[i]);
            }
        }
        return sb.toString();
    }

}
