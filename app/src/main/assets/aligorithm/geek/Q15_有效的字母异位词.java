package com.algorithm.demo.geek;


public class Q15_有效的字母异位词 {

    /**
     * 从另一个角度考虑，tt 是 ss 的异位词等价于「两个字符串中字符出现的种类和次数均相等」。由于字符串只包含 2626 个小写字母，
     * 因此我们可以维护一个长度为 2626 的频次数组 \textit{table}table，先遍历记录字符串 ss 中字符出现的频次，然后遍历字符串 tt，
     * 减去 \textit{table}table 中对应的频次，如果出现 \textit{table}[i]<0table[i]<0，则说明 tt 包含一个不在 ss 中的额外字符，
     * 返回 \text{false}false 即可。
     * 时间复杂度：O(n)O(n)，其中 nn 为 ss 的长度。
     * 空间复杂度：O(S)O(S)，其中 SS 为字符集大小，此处 S=26S=26。
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] nums = new int[26];
        for (int i = 0; i < s.length(); i++) {
            nums[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            nums[t.charAt(i) - 'a']--;
            if (nums[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

}
