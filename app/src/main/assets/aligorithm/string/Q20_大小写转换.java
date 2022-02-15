package com.algorithm.demo.string;

/**
 * 145. 大小写转换
 * 将一个字符由小写字母转换为大写字母
 *
 * 样例
 * 样例 1:
 *
 * 输入: 'a'
 * 输出: 'A'
 * 样例 2:
 *
 * 输入: 'b'
 * 输出: 'B'
 * 注意事项
 * 你可以假设输入一定在小写字母 a ~ z 之间
 */
public class Q20_大小写转换 {

    public static void main(String[] args) {

    }

    /**
     * @param character: a character
     * @return: a character
     */
    public char lowercaseToUppercase(char character) {
        // write your code here
        //获得character与'a'的差值，在'A'的基础上加上这个差值
        return (char)(character - 'a' + 'A');
    }

}
