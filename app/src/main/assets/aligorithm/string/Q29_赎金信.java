package com.algorithm.demo.string;

/**
 * 383. 赎金信
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * 如果可以，返回 true ；否则返回 false 。
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 * 示例 1：
 * 输入：ransomNote = "a", magazine = "b"
 * 输出：false
 * 示例 2：
 * 输入：ransomNote = "aa", magazine = "ab"
 * 输出：false
 */
public class Q29_赎金信 {

    public boolean canConstruct(String ransomNote, String magazine) {
        if(ransomNote.length() > magazine.length()){
            return false;
        }
        int[] nums = new int[26];
        for(int i = 0 ; i < magazine.length(); i++){
            nums[magazine.charAt(i) - 'a']++;
        }
        for(int i = 0 ; i < ransomNote.length() ; i++){
            nums[ransomNote.charAt(i) - 'a']--;
            if(nums[ransomNote.charAt(i) - 'a'] < 0){
                return false;
            }
        }
        return true;
    }
}
