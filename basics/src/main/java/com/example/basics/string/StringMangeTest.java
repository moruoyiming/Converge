package com.example.basics.string;

import java.util.StringTokenizer;

/**
 * 整理字符串，将字符串的前导空白符和后随空白符删去，并将字符串中非空白符列之前的连续的多个空白符只保留一个，
 * 而去掉多余的空白符
 */
public class StringMangeTest {
    public static void main(String[] args) {
        String str1 = " 123456 77 88 abdde fg ";
        System.out.println("整理前的字符串 str1=" + str1);
        //String str2=str1.trim();
        StringTokenizer pas = new StringTokenizer(str1, " ");//将非空白字符列看作单词进行分析
        int n = pas.countTokens();//单词（没有空格分格的一串字符列）个数
        int i = 0;
        String s = "";
        String s1 = "";
        while (i <= n - 1) {
            if (i < n - 1)//对除最后一个单词的前面的单词加一个空格赋给字符串 s1
                s1 = pas.nextToken() + ' ';//当 i=0 时，s1 得到第一个单词
            if (i == n - 1)//将最后一个单词赋给字符串 s1
                s1 = pas.nextToken();
            s += s1;//将各字符串连接起来组成一个新串 s
            i++;
        }
        str1 = s;
        System.out.println("整理后的字符串 str1=" + str1);
    }
} 