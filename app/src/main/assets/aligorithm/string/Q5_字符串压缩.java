package com.algorithm.demo.string;

/**
 * 213. 字符串压缩
 * 设计一种方法，通过给重复字符计数来进行基本的字符串压缩。
 * <p>
 * 例如，字符串 aabcccccaaa 可压缩为 a2b1c5a3 。而如果压缩后的字符数不小于原始的字符数，则返回原始的字符串。
 * <p>
 * 可以假设字符串仅包括 a-z 的字母。
 * <p>
 * 样例
 * 样例 1：
 * <p>
 * 输入：str = "aabcccccaaa"
 * 输出："a2b1c5a3"
 * 样例 2：
 * <p>
 * 输入：str = "aabbcc"
 * 输出："aabbcc"
 */
public class Q5_字符串压缩 {

    public static void main(String[] args) {
        String str = "aabcccccaaa";
        String result = compress(str);
        System.out.println("result is " + result);
    }

    /**
     * @param originalString: a string
     * @return: a compressed string
     */
    public static String compress(String originalString) {
        if (originalString == null || originalString.length() == 0) {
            return originalString;
        }
        char[] arrays = originalString.toCharArray();
        char temp = arrays[0];
        int count = 1;
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < arrays.length; i++) {
            if (temp == arrays[i]) {
                count++;
            } else {
                sb.append(temp);
                sb.append(count);
                temp = arrays[i];
                count = 1;
            }
        }
        sb.append(temp);
        sb.append(count);
        //長度沒有優化的話返回原值
        if (sb.length() >= originalString.length()) {
            return originalString;
        }
        return sb.toString();
    }
}
