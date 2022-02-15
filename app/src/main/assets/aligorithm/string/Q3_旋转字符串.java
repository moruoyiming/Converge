package com.algorithm.demo.string;

/**
 * 8. 旋转字符串
 * 给定一个字符串（以字符数组的形式给出）和一个偏移量，根据偏移量原地旋转字符串(从左向右旋转)。
 * <p>
 * 样例
 * Example 1:
 * <p>
 * Input: str="abcdefg", offset = 3
 * Output: str = "efgabcd"
 * Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "efgabcd".
 * Example 2:
 * <p>
 * Input: str="abcdefg", offset = 0
 * Output: str = "abcdefg"
 * Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "abcdefg".
 * Example 3:
 * <p>
 * Input: str="abcdefg", offset = 1
 * Output: str = "gabcdef"
 * Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "gabcdef".
 * Example 4:
 * <p>
 * Input: str="abcdefg", offset =2
 * Output: str = "fgabcde"
 * Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "fgabcde".
 * Example 5:
 * <p>
 * Input: str="abcdefg", offset = 10
 * Output: str = "efgabcd"
 * Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "efgabcd".
 * 挑战
 * 在数组上原地旋转，使用O(1)的额外空间
 * <p>
 * 说明
 * 原地旋转意味着你要在s本身进行修改。你不需要返回任何东西。
 * <p>
 * 注意事项
 * offset >= 0
 * the length of str >= 0
 * Make changes on the original input data
 */
public class Q3_旋转字符串 {

    public static void main(String[] args) {
        String str = "abcdefg"; // fgabcde
        char[] what = rotateString(str.toCharArray(), 2);
        for (int i = 0; i < what.length; i++) {
            System.out.print(" " + what[i]);
        }
    }

    /*
     * param A: A string
     * param offset: Rotate string with offset.
     * return: Rotated string.
     * 三步反转
     * 事件复杂度 O(n)
     * 空间复杂度 O(1)
     *
     */
    public static char[] rotateString(char[] A, int offset) {
        if (A == null || A.length == 0) {
            return A;
        }
        int len = A.length;
        offset %= len;
        reverse(A, 0, len - offset - 1);
        reverse(A, len - offset, len - 1);
        reverse(A, 0, len - 1);
        return A;
    }

    private static void reverse(char[] str, int start, int end) {
        System.out.println("reverse " + " start=" + start + " end=" + end);
        while (start < end) {
            char temp = str[start];
            str[start++] = str[end];
            str[end--] = temp;
        }
    }




}
