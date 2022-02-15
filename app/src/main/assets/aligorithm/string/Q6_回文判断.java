package com.algorithm.demo.string;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 */
public class Q6_回文判断 {

    //第一种思路：反转法加正则剔除，对于大小写问题：
    // 1.使用String类的toLowerCase()方法统一为小写或者toUpperCase()方法统一为大写；
    // 2.使用equalsIgnoreCase()方法忽略大小写。
    public static void main(String[] args) {
        String str = "A man, a plan, a canal: Panama";
        boolean result = isPalindrome(str);
        System.out.println(result);

    }

    /**
     * 第二种思路：二分法加正则剔除。
     * 时间复杂度：O(logn)。
     * 空间复杂度：O(n)。
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        String str = s.replaceAll("[^a-z^A-Z^0-9]", "").toLowerCase();
        char[] ss = str.toCharArray();
        int left = 0;
        int right = ss.length - 1;
        while (left < right) {
            if (ss[left] != ss[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
