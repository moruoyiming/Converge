package com.algorithm.demo.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 209. 第一个只出现一次的字符
 * 给出一个字符串，找出第一个只出现一次的字符。
 * <p>
 * 样例
 * 样例 1:
 * 输入: "abaccdeff"
 * 输出:  'b'
 * <p>
 * 解释:
 * 'b' 是第一个出现一次的字符
 * <p>
 * <p>
 * 样例 2:
 * 输入: "aabccd"
 * 输出:  'b'
 * <p>
 * 解释:
 * 'b' 是第一个出现一次的字符
 */
public class Q28_第一个只出现一次的字符 {

    public static void main(String[] args) {

        String str = "aabc";
        char c = firstUniqChar(str);
        System.out.println("  " + c);
    }

    /**
     * @param str: str: the given string
     * @return: char: the first unique character in a given string
     */
    public static char firstUniqChar(String str) {
        // Write your code here
        char[] array = str.toCharArray();
        char result = ' ';
        if (array == null || array.length < 0) {
            throw new RuntimeException("wrong");
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                int value = map.get(array[i]);
                value++;
                map.put(array[i], value);
            } else {
                map.put(array[i], 1);
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i]) && map.get(array[i]) == 1) {
                result = array[i];
                return result;
            }
        }
        return result;
    }
}
