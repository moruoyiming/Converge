package com.algorithm.demo.剑指Offer;

/**
 * 请实现一个函数用来匹配包含'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次(含0次)。
 * 例如：字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 * 每次从字符串里拿出一个字符和模式中的字符去匹配。先来分析如何匹配一个字符。如果模式中的字符ch是'.'，那么它可以匹配字符串中的任意字符。
 * 如果模式中的字符ch不是'.'，而且字符串中的字符也是ch，那么他们相互匹配。当字符串中的字符和模式中的字符相匹配，接着匹配后面的字符。
 * 相对而言，当模式中的第二个字符不是'*'时，问题要简单很多。如果字符串中的第一个字符和模式中的第一个字符想匹配，那么在字符串和模式上都向后
 * 移动一个字符，然后匹配剩余的字符串和模式。如果字符串中的第一个字符和模式中的第一个字符不相匹配，则直接返回false。
 * 当模块中的第二个字符是'*'时，问题要复杂一些，因为可能有多种不同的匹配方式。一种选择是在模式上向后移动两个字符。着相当于'*'和它前面的字符被
 * 忽略了，因为'*'可以匹配字符串中的0个字符。如果模式中的第一个字符和字符串中的第一个字符相匹配，则在字符串上向后移动一个字符，而在模式上有
 * 两种选择：可以在模式上向后移动两个字符，也可以保持模式不变。
 */
public class Q19_正则表达式匹配 {

    public boolean match(char str, char pattern) {
        if (Character.isSpaceChar(str) || Character.isSpaceChar(pattern)) {
            return false;
        }
        return matchCore(str, pattern);
    }

    private boolean matchCore(char str, char pattern) {
        if (str == '\0' && pattern == '\0')
            return true;

        if (str != '\0' && pattern == '\0')
            return false;


        return false;
    }

}
