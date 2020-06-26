package com.example.basics.string;

import java.util.ArrayList;
import java.util.List;

public class Stringquchong {


    public static void main(String[] args) {
        String str = "AABBCCABC";
        System.out.println(sub(str));
        System.out.println(sub1(str));
    }

    //方法1  
    static String sub(String str) {
        StringBuffer result = new StringBuffer();
        List list = new ArrayList();
        char[] cs = str.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (!list.contains(cs[i])) {
                result.append(cs[i]);
                list.add(cs[i]);
            }
        }
        return result.toString();
    }

    //方法2  
    static String sub1(String str) {
        List list = new ArrayList();
        StringBuffer sb = new StringBuffer(str);
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            if (list.contains(str.charAt(i))) {
                sb.deleteCharAt(i - j);     //String 是没有delete方法的
                j++; //因为删除了sb中的字符，有一个偏移  
            } else {
                list.add(str.charAt(i));
            }
        }
        return sb.toString();
    }
}  