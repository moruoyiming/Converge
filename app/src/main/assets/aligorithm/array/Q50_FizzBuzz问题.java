package com.algorithm.demo.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 9 · Fizz Buzz 问题
 * 描述
 * 给定整数 n ，按照如下规则打印从 1 到 n 的每个数：
 * <p>
 * 如果这个数被3整除，打印fizz。
 * 如果这个数被5整除，打印buzz。
 * 如果这个数能同时被3和5整除，打印fizz buzz。
 * 如果这个数既不能被 3 整除也不能被 5 整除，打印数字本身。
 * 样例
 * 样例 1：
 * <p>
 * 输入：
 * <p>
 * n = 15
 * 输出：
 * <p>
 * [
 * "1", "2", "fizz",
 * "4", "buzz", "fizz",
 * "7", "8", "fizz",
 * "buzz", "11", "fizz",
 * "13", "14", "fizz buzz"
 * ]
 */
public class Q50_FizzBuzz问题 {

    public static void main(String[] args) {
        List<String> result = fizzBuzz2(15);
        System.out.println(result.toString());
    }

    /**
     * @param n: An integer
     * @return: A list of strings.
     */
    public static List<String> fizzBuzz(int n) {
        // write your code here
        List<String> result = new ArrayList<String>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) { 
                result.add("fizz buzz");
            } else if (i % 3 == 0) {
                result.add("fizz");
            } else if (i % 5 == 0) {
                result.add("buzz");
            } else {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }

    /**
     * @param n: An integer
     * @return: A list of strings.
     */
    public static List<String> fizzBuzz2(int n) {
        List<String> result = new ArrayList<String>();
        for (int i = 1; i <= n; i++) {
            result.add(i % 3 == 0 ? (i % 5 == 0 ? "fizz buzz" : "fizz") : (i % 5 == 0) ? "buzz" : String.valueOf(i));
        }
        return result;
    }

}
