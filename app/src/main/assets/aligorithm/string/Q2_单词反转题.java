package com.algorithm.demo.string;

import com.algorithm.demo.PrintArray;

/**
 * 输入 I am a student.
 * 输出 student. a am I
 */
public class Q2_单词反转题 {

    public static void main(String[] args) {

        String str = "I am a student.";
        String[] strs = str.split(" ");
        PrintArray.printStringArrayBefore(strs);
        int length = strs.length;
        int i = 0;
        while (i < length) {
            String s = strs[i];
            strs[i] = strs[length - 1];
            strs[length - 1] = s;
            i++;
            length--;
        }
        PrintArray.printStringArrayBefore(strs);
    }


}
