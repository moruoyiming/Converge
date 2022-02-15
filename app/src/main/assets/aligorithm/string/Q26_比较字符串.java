package com.algorithm.demo.string;

/**
 * 55. 比较字符串
 * 比较两个字符串A和B，确定A中是否包含B中所有的字符。字符串A和B中的字符都是 大写字母
 * <p>
 * 样例
 * 给出 A = "ABCD" B = "ACD"，返回 true
 * <p>
 * 给出 A = "ABCD" B = "AABC"， 返回 false
 * <p>
 * 注意事项
 * The characters of B in A are not necessary continuous or ordered.
 */
public class Q26_比较字符串 {

    public static void main(String[] args) {
        String a = "ABCD";
        String b = "AABC";
        boolean value = compareStrings(a, b);
        System.out.println("  " + value);
    }

    /**
     * @param A: A string
     * @param B: A string
     * @return: if string A contains all of the characters in B return true else return false
     */
    public static boolean compareStrings(String A, String B) {
        // write your code here
        char[] Aarray = A.toCharArray();
        char[] Barray = B.toCharArray();
        int nums = B.length();
        for (int i = 0; i < B.length(); i++) {
            for (int j = 0; j < A.length(); j++) {
                if (Aarray[j] == Barray[i]) {
                    Aarray[j] = 1;
                    nums--;
                    break;
                }
            }
        }
        if (nums == 0) {
            return true;
        } else {
            return false;
        }
    }
}
