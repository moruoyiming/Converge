package com.algorithm.demo.string;

import java.util.ArrayList;
import java.util.List;

public class Q24_最长单词 {

    public static void main(String[] args) {
//        String[] dictionary = {"dog", "google", "facebook", "internationalization", "blabla"};
        String[] dictionary = {"zscqe", "ahcqm", "sljum", "jluqh", "rowlv"};
        List<String> temp = longestWords(dictionary);
        System.out.println(temp.toString());
    }

    /*
     * @param dictionary: an array of strings
     * @return: an arraylist of strings
     */
    public static List<String> longestWords(String[] dictionary) {
        // write your code here
        if (dictionary.length <= 0) {
            return null;
        }
        List<String> temp = new ArrayList<>();
        int maxlength = dictionary[0].length();
        temp.add(dictionary[0]);
        for (int i = 1; i < dictionary.length; i++) {
            if (maxlength < dictionary[i].length()) {
                maxlength = dictionary[i].length();
                temp.clear();
                temp.add(dictionary[i]);
            } else if (maxlength == dictionary[i].length()) {
                temp.add(dictionary[i]);
            }
        }

        return temp;
    }

}
