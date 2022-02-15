package com.algorithm.demo.string;


import com.algorithm.demo.PrintArray;

/**
 * 13. 字符串查找
 * 对于一个给定的 source 字符串和一个 target 字符串，你应该在 source 字符串中找出 target 字符串出现的第一个位置(从0开始)。如果不存在，则返回 -1。
 * <p>
 * 样例
 * Example 1:
 * <p>
 * Input: source = "source" ，target = "target"
 * Output: -1
 * Explanation: If the source does not contain the target content, return - 1.
 * Example 2:
 * <p>
 * Input:source = "abcdabcdefg" ，target = "bcd"
 * Output: 1
 * Explanation: If the source contains the target content, return the location where the target first appeared in the source.
 * 挑战
 * O(n2)的算法是可以接受的。如果你能用O(n)的算法做出来那更加好。（提示：KMP）
 * <p>
 * 说明
 * 在面试中我是否需要实现KMP算法？
 * <p>
 * 不需要，当这种问题出现在面试中时，面试官很可能只是想要测试一下你的基础应用能力。当然你需要先跟面试官确认清楚要怎么实现这个题。
 */
public class Q8_字符串查找 {

    public static void main(String[] args) {
        int value = strStr("abcdabcdefg", "bcd");
//        int value = strStr("a", "a");
        System.out.println("  " + value);
    }

    /**
     * @param source:
     * @param target:
     * @return: return the index
     */
    public static int strStr(String source, String target) {
        // Write your code here
        if (target.length() == 0) {
            return 0;
        }
        int[] next = new int[target.length()];
        getNext(next, target);
        int j = -1;// 因为next数组里记录的起始位置为-1
        for (int i = 0; i < source.length(); i++) {// 注意i就从0开始
            while (j >= 0 && source.charAt(i) != target.charAt(j + 1)) {// 不匹配
                j = next[j];// j 寻找之前匹配的位置
            }
            if (source.charAt(i) == target.charAt(j + 1)) {// 匹配，j和i同时向后移动
                j++; // i的增加在for循环里
            }
            if (j == target.length() - 1) {// 文本串s里出现了模式串t
                return (i - target.length() + 1);
            }
        }

        return -1;
    }

    public static void getNext(int[] next, String s) {
        int j = -1;
        next[0] = j;
        for (int i = 1; i < s.length(); i++) { // 注意i从1开始
            while (j >= 0 && s.charAt(i) != s.charAt(j + 1)) {// 前后缀不相同了
                j = next[j];// 向前回溯
            }
            if (s.charAt(i) == s.charAt(j + 1)) {// 找到相同的前后缀
                j++;
            }
//            i=1  c != b  next[0] = -1  next[1] = -1;
//            i=2  d != b  next[0] = -1  next[1] = -1; next[2] = -1;
            System.out.println(" i = "+i+" boolean = "+(s.charAt(i) == s.charAt(j + 1)));
            next[i] = j;// 将j（前缀的长度）赋给next[i]
            PrintArray.print(next);
        }
    }
}
