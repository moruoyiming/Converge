package com.algorithm.demo.剑指Offer;

/**
 * 在一个数组中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 * 三个相同的数字的异或结果还是该数字。
 * 如果一个数字出现三次，那么它的二进制标识的每一位(0或者1)也出现三次。如果把所有出现三次的数字的二进制表示的每一位都分别加起来，
 * 那么每一位的和都能被3整除。
 * 把数组中所有数字的二进制表示的每一位都加起来。如果某一位的和都能被3整除，那么那个只出现一次的数字二进制表示中对应的那一位是0；否则就是1。
 */
public class 数组中唯一只出现一次的数字 {

    public int findNumberAppearingOnce(int numbers[], int length) {
        if (numbers == null || length <= 0)
            return -1;
        int[] array = {0};
        for (int i = 0; i < length; ++i) {
            int bitMask = 1;
            for (int j = 31; j >= 0; --j) {
                int bit = numbers[i] & bitMask;
                if (bit != 0) {
                    array[j] += 1;
                }
                bitMask = bitMask << 1;
            }
        }
        int result = 0;
        for (int i = 0; i < 32; ++i) {
            result = result << 1;
            result += array[i] % 3;
        }
        return result;
    }
}
