package com.algorithm.demo.string;

/**
 * 211. 字符串置换
 * 给定两个字符串，请设计一个方法来判定其中一个字符串是否为另一个字符串的置换。
 * <p>
 * 置换的意思是，通过改变顺序可以使得两个字符串相等。
 * <p>
 * 样例
 * 样例 1:
 * 输入: "abcd", "bcad"
 * 输出:  True
 * <p>
 * <p>
 * <p>
 * 样例 2:
 * 输入: "aac", "abc"
 * 输出:  False
 */
public class Q7_字符串置换 {

    public static void main(String[] args) {
        String A = "abcd";
        String B = "bcad";
        boolean value =Permutation(A,B);
        System.out.println(""+value);
    }

    /**
     * @param A: a string
     * @param B: a string
     * @return: a boolean
     */
    public static boolean Permutation(String A, String B) {
        // write your code here
        if(A.length()!=B.length()){
            return false;
        }
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
