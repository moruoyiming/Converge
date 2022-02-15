package com.algorithm.demo.string;

/**
 * 146 · 大小写转换 II
 * 描述
 * 将一个字符串中的小写字母转换为大写字母。不是字母的字符不需要做改变。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入: str = "abc"
 * 输出: "ABC"
 */
public class Q21_大小写转换2 {

    public static void main(String[] args) {

    }

    /**
     * @param str: A string
     * @return: A string
     */
    public String lowercaseToUppercase2(String str) {
        // write your code here
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            if (Character.isLowerCase(temp)) {
                sb.setCharAt(i, Character.toUpperCase(temp));
            }
        }
        return sb.toString();
    }

}
