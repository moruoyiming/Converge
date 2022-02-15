package com.algorithm.demo.string;

/**
 * 157. 判断字符串是否没有重复字符
 * 实现一个算法确定字符串中的字符是否均唯一出现
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入:  "abc_____"
 * 输出:  false
 * 样例 2:
 * <p>
 * 输入:  "abc"
 * 输出:  true
 * 挑战
 * 如果不使用额外的存储空间，你的算法该如何改变？
 */
public class Q17_判断字符串是否没有重复字符 {
    public static void main(String[] args) {
        String abc = "abcc____";
//        String abc = "abc";
        Long begintime = System.nanoTime();
        boolean have = isUnique(abc);
        Long endtime = System.nanoTime();
        System.out.println("是否没有重复字符" + have + ",运行时间：" + (endtime - begintime) + "ns");
        Long begintime2 = System.nanoTime();
        boolean have2 = isUnique2(abc);
        Long endtime2 = System.nanoTime();
        System.out.println("是否没有重复字符" + have2 + ",运行时间：" + (endtime2 - begintime2) + "ns");
    }

    /*
     * @param str: A string
     * @return: a boolean
     */
    public static boolean isUnique(String str) {
        // write your code here
        if (str == null || str.length() == 0)
            return false;

        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j))
                    return false;
            }
        }
        return true;
    }


    /*
     * @param str: A string
     * @return: a boolean
     */
    public static boolean isUnique2(String str) {
        // write your code here
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.indexOf(str.charAt(i), i + 1) != -1) {
                return false;
            }
        }
        return true;
    }
}
