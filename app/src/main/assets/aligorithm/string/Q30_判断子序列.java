package com.algorithm.demo.string;

/**
 * 392. 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 进阶：
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 * 致谢：
 * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
 * 示例 1：
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 * 示例 2：
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false
 */
public class Q30_判断子序列 {

    /**
     * 思路及算法
     * 本题询问的是，ss 是否是 tt 的子序列，因此只要能找到任意一种 ss 在 tt 中出现的方式，即可认为 ss 是 tt 的子序列
     * 这样，我们初始化两个指针 ii 和 jj，分别指向 ss 和 tt 的初始位置。每次贪心地匹配，匹配成功则 ii 和 jj 同时右移，
     * 匹配 ss 的下一个位置，匹配失败则 jj 右移，ii 不变，尝试用 tt 的下一个字符匹配 ss。
     * 最终如果 ii 移动到 ss 的末尾，就说明 ss 是 tt 的子序列。
     * 时间复杂度：O(n+m)，其中 n 为 s 的长度，m 为 t 的长度。每次无论是匹配成功还是失败，都有至少一个指针发生右移，两指针能够位移的总距离为 n+m。
     * 空间复杂度：O(1)
     */
    public boolean isSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();
        int p1 = 0;
        int p2 = 0;
        while (p1 < m && p2 < n) {
            if (s.charAt(p1) == t.charAt(p2)) {
                p1++;
            }
            p2++;
        }
        return p1 == m;
    }

}
