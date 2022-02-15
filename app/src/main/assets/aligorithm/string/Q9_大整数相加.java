package com.algorithm.demo.string;

/**
 * 655 · 大整数加法
 * <p>
 * 描述
 * 以字符串的形式给出两个非负整数 num1 和 num2，返回 num1 和 num2 的和。
 * <p>
 * num1 和 num2 的长度都小于5100。
 * num1 和 num2 都只包含数字 0-9。
 * num1 和 num2 都不包含任何前导零。
 * 您不能使用任何内置的BigInteger库内的方法或直接将输入转换为整数。
 * 样例
 * 样例 1:
 * <p>
 * 输入 : num1 = "123", num2 = "45"
 * 输出 : "168"
 */
public class Q9_大整数相加 {
    public static void main(String[] args) {

        String result = addStrings("17999071", "1237289861");
        System.out.println("result=" + result);
    }

    /**
     * @param num1: a non-negative integers
     * @param num2: a non-negative integers
     * @return: return sum of num1 and num2
     */
    public static String addStrings(String num1, String num2) {
        String res = "";
        int m = num1.length(), n = num2.length(), i = m - 1, j = n - 1, flag = 0;
        while (i >= 0 || j >= 0) {
            int a, b;
            if (i >= 0) {
                a = num1.charAt(i--) - '0';
            } else {
                a = 0;
            }
            if (j >= 0) {
                b = num2.charAt(j--) - '0';
            } else {
                b = 0;
            }
            int sum = a + b + flag;
            res = (char) (sum % 10 + '0') + res;
            flag = sum / 10;
        }
        return flag == 1 ? "1" + res : res;
    }

}
