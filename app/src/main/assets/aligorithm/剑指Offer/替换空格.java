package com.algorithm.demo.剑指Offer;

/**
 * 请实现一个函数，把字符串中的每个空格替换成"%20"。例如，输入"We are happy." ,则输出"We%20are%20happy."。
 */
public class 替换空格 {

    public static void main(String[] args) {
        char[] what = {'W', 'e', ' ', 'a', 'r', 'e', ' ', 'h', 'a', 'p', 'p', 'y', '.'};
        ReplaceBlank(what, what.length);
    }

    /**
     * 时间复杂度O(n)
     * 先遍历一次字符串，统计字符串中空格总数，由此计算出替换之后的字符串的总长度。没替换一个空格，长度增加2，因此替换以后字符串的长度等于原来的长度
     * 加上2乘以空格树木。 we are happy 为例， 长度14 包括结尾符号'\0'，里面有两个空格， 因此替换之后字符串的长度是18。
     * 从字符串后面开始复制和替换，首先准备两个指针：p1和p2.
     * p1指向原始字符串的末尾，而p2指向替换之后的字符串的末尾，接下来我们向前移动指针指针p1,逐个把它指向的字符复制到p2指向的位置，知道碰到第一个空格
     * 为止。碰到第一个空格之后，把p1向前移动1格，在p2之前插入字符串"%20"。由于"%20"的长度为3，同时也要把p2向前移动3格。
     *
     * @param string
     * @param length
     */
    public static void ReplaceBlank(char[] string, int length) {
        if (string == null || length <= 0) {
            return;
        }
        //originalLength为字符串string的实际长度
        int originalLength = 0;
        int numberOfBlank = 0;
        int i = 0;
        while (string[i] != '.') {
            ++originalLength;
            if (string[i] == ' ') {
                ++numberOfBlank;
            }
            ++i;
        }
        int newLength = originalLength + numberOfBlank * 3;
        int indexOfOriginal = originalLength;
        int indexOfNew = newLength-1;
        char[] what = new char[indexOfNew];
        while (indexOfOriginal >= 0 && indexOfNew > indexOfOriginal) {
            if (string[indexOfOriginal] == ' ') {
                what[--indexOfNew] = '0';
                what[--indexOfNew] = '2';
                what[--indexOfNew] = '%';
            } else {
                what[--indexOfNew] = string[indexOfOriginal];
            }
            --indexOfOriginal;
        }
        String whats = new String(what);
        System.out.println(whats);
    }

}
