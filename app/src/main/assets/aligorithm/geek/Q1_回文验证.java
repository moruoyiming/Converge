package com.algorithm.demo.geek;

public class Q1_回文验证 {
    //第一种思路：反转法加正则剔除，对于大小写问题：
    // 1.使用String类的toLowerCase()方法统一为小写或者toUpperCase()方法统一为大写；
    // 2.使用equalsIgnoreCase()方法忽略大小写。
    public static void main(String[] args) {
        String str = "A man, a plan, a canal: Panama";
        boolean result = isPalindrome(str);
        System.out.println(result);

    }

    public static boolean isPalindrome(String s) {
        //高层次 (主干) 逻辑。
        //1. filter out number & char; 2.reverse and compare
        String filteredStr = filterNonNumberAndChar(s);
        String reversedStr = reverseString(filteredStr);
        return reversedStr.equalsIgnoreCase(filteredStr);
    }

    private static String reverseString(String filteredStr) {
        return new StringBuilder(filteredStr).reverse().toString();
    }

    private static String filterNonNumberAndChar(String s) {
        String str = s.replaceAll("[^a-z^A-Z^0-9]", "").toLowerCase();
        return str;
    }

}
