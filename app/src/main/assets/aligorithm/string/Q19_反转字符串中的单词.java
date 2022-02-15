package com.algorithm.demo.string;

/**
 * 53. 翻转字符串中的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * 样例
 * Example 1:
 * Input:  "the sky is blue"
 * Output:  "blue is sky the"
 * <p>
 * Explanation:
 * return a reverse the string word by word.
 * <p>
 * Example 2:
 * Input:  "hello world"
 * Output:  "world hello"
 * 说明：
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 注意：
 * <p>
 * 字符串首位都可包含多个空格，但是翻转后不能有，所以第一步先除去首位的空格，可以用到trim()函数（trim函数只除去首位的空格）
 * 字符串中两个单词之间存在多个空格的情况，所以在运用split分割的时候，正则表达式应该包含多个空格的情况，本文中用到split(" +")。
 * 解决思路：
 * <p>
 * 运用trim()函数除去首位的空格
 * 运用split()函数将字符串中的单词分割开，存入字符串数组中
 * 创建StringBuilder对象，将分割出来的单词依次按照要求添加到StringBuilder中
 * 输出字符串
 */
public class Q19_反转字符串中的单词 {

    public static void main(String[] args) {
        String str = "  Life  doesn't  always    give     us  the       joys we want.";
//        String str = "How are you?";
        String temp = reverseWords(str);
        System.out.println(" " + temp);
    }

    /*
     * @param s: A string
     * @return: A string
     */
    public static String reverseWords(String s) {
        // write your code here
        if (s.equals("")) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("xxx");
        String[] array = s.split(" ");
        for (int i = 0; i < array.length; i++) {
            if(!array[array.length-1-i].equals("")){
                sb.append(array[array.length-1-i]+" ");
            }

        }
        sb.append("xxx");
        return sb.toString();
    }
}
