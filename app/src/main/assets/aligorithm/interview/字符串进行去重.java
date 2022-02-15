package com.algorithm.demo.interview;

import java.util.HashSet;

public class 字符串进行去重 {

    /**
     * 对字符串进行去重处理，并且保持原来的输入顺序。举例：
     * 输入的字符串 String  s = "ADABEFFFDCBGH发货价啦AFG"；
     * 经过处理之后结果应该是："ADBEFCGH发货价啦"
     * （考虑时间复杂度, 要求时间复杂度O(n)）
     *
     * @param str: 要去重的字符串
     * @return: 去重后的字符串
     */
    public static String removeDuplicate(String str) {
        // TODO: Your Code Here
        if (str == null || str.length() == 0) {
            return null;
        }
        HashSet<Character> sets = new HashSet<>();
        StringBuffer sb = new StringBuffer();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!sets.contains(chars[i])) {
                sets.add(chars[i]);
                sb.append(chars[i]);
                System.out.println(" chars[i] " + sb.toString());
            }
        }
        return sb.toString();
    }

}
