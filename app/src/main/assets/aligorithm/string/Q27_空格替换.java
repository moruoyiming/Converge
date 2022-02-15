package com.algorithm.demo.string;

/**
 * 212. 空格替换
 * 设计一种方法，将一个字符串中的所有空格替换成 %20 。你可以假设该字符串有足够的空间来加入新的字符，且你得到的是“真实的”字符长度。
 *
 * 你的程序还需要返回被替换后的字符串的长度。
 *
 * 样例
 * 样例 1：
 *
 * 输入：string[] = "Mr John Smith" and length = 13
 * 输出：string[] = "Mr%20John%20Smith" and return 17
 * 解释：
 * 对于字符串 "Mr John Smith"，长度为 13。替换空格之后，参数中的字符串需要变为 "Mr%20John%20Smith"，并且把新长度 17 作为结果返回。
 * 样例 2：
 *
 * 输入：string[] = "LintCode and Jiuzhang" and length = 21
 * 输出：string[] = "LintCode%20and%20Jiuzhang" and return 25
 * 解释：
 * 对于字符串 "LintCode and Jiuzhang"，长度为 21。替换空格之后，参数中的字符串需要变为 "LintCode%20and%20Jiuzhang"，并且把新长度 25 作为结果返回。
 * 挑战
 * 在原字符串(字符数组)中完成替换，不适用额外空间
 */
public class Q27_空格替换 {
    /*
     * @param string: An array of Char
     * @param length: The true length of the string
     * @return: The true length of new string
     */
    public int replaceBlank(char[] string, int length) {
        if(string == null || length <= 0){
            return 0;
        }
        int finalLen = length;
        for(int i = 0; i < length; i++){
            if(string[i] == ' '){
                finalLen += 2;
            }
        }
        int i = finalLen - 1;
        int j = length - 1;
        while( i > 0 ){
            if(string[j] != ' '){
                string[i--] = string[j--];
            }else{
                string[i--] = '0';
                string[i--] = '2';
                string[i--] = '%';
                j--;
            }
        }
        return finalLen;
    }
}
