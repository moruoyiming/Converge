package com.algorithm.demo.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 * 输出n以内所有的素数。
 * <p>
 * 保证 n 是100以内的整数。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入：5
 * 输出：[2, 3, 5]
 *
 */
public class Q48_寻找素数 {

    public static void main(String[] args) {
        List<Integer> result = prime(5);
        System.out.println("result=" + result.toString());
    }

    /**
     * @param n: an integer
     * @return: return all prime numbers within n.
     */
    public static List<Integer> prime(int n) {
        List<Integer> lists = new ArrayList<>();
        for(int i = 2 ; i <= n ;i++){
            if(isPrime(i)){
                lists.add(i);
            }
        }
        return lists;
    }

    private static boolean isPrime(int n) {
        for(int i = 2; i < n ; i++){
            System.out.println(" "+n + " "+ i  +" "+(n % i == 0));
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

}
