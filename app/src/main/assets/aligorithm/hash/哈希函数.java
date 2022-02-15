package com.algorithm.demo.hash;

/**
 * 哈希函数 · Hash Function
 * 在数据结构中，哈希函数是用来将一个字符串（或任何其他类型）转化为小于哈希表大小且大于等于零的整数。一个好的哈希函数可以尽可能少地产生冲突。一种广泛使用的哈希函数算法是使用数值 33，假设任何字符串都是基于 33 的一个大整数，比如：
 * [Math Processing Error]hashcode("abcd")
 * [Math Processing Error]=(ascii(a)∗33
 * 3
 * +ascii(b)∗33
 * 2
 * +ascii(c)∗33+ascii(d)) % HASH_SIZE
 * [Math Processing Error]=(97∗33
 * 3
 * +98∗33
 * 2
 * +99∗33+100) % HASH_SIZE
 * [Math Processing Error]=3595978 % HASH_SIZE
 * 其中HASH_SIZE表示哈希表的大小(可以假设一个哈希表就是一个索引 [Math Processing Error]0 ~ HASH_SIZE - 1的数组)。给出一个字符串作为 key 和一个哈希表的大小，返回这个字符串的哈希值。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入:  key = "abcd", size = 1000
 * 输出: 978
 * 样例解释：(97 * 33^3 + 98*33^2 + 99*33 + 100*1)%1000 = 978
 * 样例 2:
 * <p>
 * 输入:  key = "abcd", size = 100
 * 输出: 78
 * 样例解释：(97 * 33^3 + 98*33^2 + 99*33 + 100*1)%100 = 78
 */
public class 哈希函数 {

    public static void main(String[] args) {
        char[] key = new char[]{'a', 'b', 'c', 'd'};
        int result = hashCode(key, 100);
        System.out.println("result=" + result);
    }

    public static int hashCode(char[] key, int HASH_SIZE) {
        long result = 0;
        for (char c : key) {
            result = (result * 33 + c) % HASH_SIZE;
        }
        return (int) result;
    }


}
