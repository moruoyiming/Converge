package com.algorithm.demo.string;

import java.util.HashSet;

public class Q4_字符串去重 {
    public static void main(String[] args) {
        String s = "ADABEFFFDCBGH发货价啦AFG";
        removeDuplicate(s);
    }

    public static String removeDuplicate(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        HashSet<Character> sets = new HashSet<>();
        StringBuffer sb =new StringBuffer();
        char[] chars = str.toCharArray();
        for(int i = 0 ;i< chars.length;i++){
            if(!sets.contains(chars[i])){
                sets.add(chars[i]);
                sb.append(chars[i]);
                System.out.println(" chars[i] "+  sb.toString());
            }
        }
        return sb.toString();
    }
}
