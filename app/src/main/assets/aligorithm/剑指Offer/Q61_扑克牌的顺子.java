package com.algorithm.demo.剑指Offer;

import java.util.Arrays;
import java.util.Collections;

/**
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续。2~10为数字本身，A为1，J为11，Q为12，K为13
 * 而大、小王可以看成任意数字。
 * <p>
 * 1.首先把数组排序
 * 2.其次统计数组中0的个数
 * 3.最后统计排序之后的数组中相邻数字之间的空缺总数。如果空缺的总数小于或者等于0的个数，那么这个数组就是连续的。反之则不连续。
 * 如果数组中的非0数字重复出现，则该数组不是连续的。换成扑克牌的描述方式就是：如果一副牌里含有个对子，则不可能是顺子。
 */
public class Q61_扑克牌的顺子 {

    public static void main(String[] args) {
        int[] number = {1,2,6,5,5};
        boolean result = isContinuous(number,number.length);
        System.out.println("result="+result);
    }

    public static boolean isContinuous(int[] numbers, int length) {
        if (numbers == null || length < 1)
            return false;
        //排序
        sort(numbers);
        int numberOfZero = 0;
        int numberOfGap = 0;
        //统计数组中的0的个数
        for (int i = 0; i < length && numbers[i] == 0; ++i){
            ++numberOfZero;
        }
        //统计数组中的间隔数目
        int small = numberOfZero;
        int big = small + 1;
        while (big < length) {
            //两个数相等，有对子，不可能是顺子
            if (numbers[small] == numbers[big])
                return false;
            numberOfGap += numbers[big] - numbers[small] - 1;
            small = big;
            ++big;
        }
        return numberOfGap <= numberOfZero;

    }

    public static void sort(int[] number){
        Arrays.sort(number);
    }

}
