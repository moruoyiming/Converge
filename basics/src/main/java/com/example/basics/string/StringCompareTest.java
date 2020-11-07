package com.example.basics.string;

/**
 * 编写实现从两字符串中找出最长的相同字符列的代码
 */
public class StringCompareTest {
    public static void main(String[] args) {
        String str1 = "abcdefghijkABCD";
        String str2 = "ilmabnopqefghrstABCD";
        String str3, str4, t1, t2, t3 = "";
        int i, j, k;
        int len1 = str1.length();
        int len2 = str2.length();
        int len3;
        if (len1 > len2)//确定使 str3 字符串的长度小于或等于 Str4 的长度
        {
            len3 = len1;
            len1 = len2;
            len2 = len3;
            str3 = str2;
            str4 = str1;
        } else {
            str3 = str1;
            str4 = str2;
        }
        for (i = len1; i >= 0; i--)//通过这个 for 嵌套循环找出一个最长相同字符列
        {
            for (j = 0; j <= i; j++) {
                for (k = 0; k <= len2; k++) {
                    t1 = str3.substring(j, i);
                    if ((len2 - k) < (i - j)) break;
                    t2 = str4.substring(k, k + i - j);
                    if (t1.equals(t2) && t1.length() > t3.length()) {
                        t3 = t1;
                        break;
                    }
                }
            }
        }
        System.out.println("两字符串中最长的相同字符列代码是:");
        //根据上一个循环确定的最长相同字符列的长度找出两字符串中所有的最长相同字符列
        for (i = len1; i >= 0; i--) {
            for (j = 0; j <= i; j++) {
                for (k = 0; k <= len2; k++) {
                    t1 = str3.substring(j, i);
                    if ((len2 - k) < (i - j)) break;
                    t2 = str4.substring(k, k + i - j);
                    if (t1.equals(t2) && t1.length() == t3.length())
                        System.out.println(t1);
                }
            }
        }
    }
} 