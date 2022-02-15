package com.algorithm.demo.string;

/**
 * 23. 判断数字与字母字符
 * 给出一个字符c，你需要判断它是不是一个数字字符或者字母字符。
 * 如果是，返回true，如果不是返回false。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入：'1'
 * 输出：true
 * 注意事项
 * 如果您使用的是Python语言，那么输入将是一个长度为1的字符串。
 */
public class Q18_判断数字与字母字符 {

    public static void main(String[] args) {
        char c = 'c';
        boolean value = isAlphanumeric(c);
        System.out.println("  " + value);
    }

    /**
     * @param c: A character.
     * @return: The character is alphanumeric or not.
     */
    public static boolean isAlphanumeric(char c) {
        // write your code here
        boolean value = Character.isDigit(c) || Character.isLowerCase(c) || Character.isUpperCase(c);
        return value;
    }

}
