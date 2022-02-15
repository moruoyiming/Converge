package com.algorithm.demo.math;

import java.util.HashMap;

/**
 * 311 · 重构回文
 * 题目题解笔记讨论排名
 * 描述
 * 给定一个字符串，通过交换某些字符后，请问此字符串能否变成一个回文串。
 * <p>
 * 1<=s.size()<=10000001<=s.size()<=1000000
 * 字符串仅包含大小写字符
 * 样例
 * 样例 1：
 * <p>
 * 输入：s = “abc"
 * 输出：
 * false
 * 样例 2：
 * <p>
 * 输入：s = “aab"
 * 输出：
 * true
 * <p>
 * 算法思路
 * 实际上这题很简单，我们并不需要通过调换位置去判断能不能变成回文串
 * 一个回文串中，最多一个字符出现奇数次
 * 我们通过一个hashmap/dict来记录每一个字母都出现了几次
 * 然后我们统计一下出现次数为奇数的个数
 * 如果这个个数大于1，说明无法构成回文串
 */
public class Q11_重构回文 {

    /**
     * 时间复杂度为O(n)
     * 为字符串的大小
     * 空间复杂度为O(n)
     * 为字符串的大小
     *
     * @param s: A string containing only uppercase and lowercase letters
     * @return: Judge whether it can become a palindrome
     */
    public boolean isPalindrome(String s) {
        // write your code here
        HashMap<Character, Integer> um = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (um.containsKey(c)) {
                um.put(c, um.get(c) + 1);
            } else {
                um.put(c, 1);
            }
        }
        int ans = 0;
        for (char key : um.keySet()) {
            if (um.get(key) % 2 == 1) {
                ans++;
            }
        }
        if (ans < 2) {
            return true;
        }
        return false;
    }

}
