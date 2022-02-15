package com.algorithm.demo.剑指Offer;

/**
 * 一个整型数组里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * 例如：输入数组{2,4,3,6,3,2,5,5}，因为只有4和6这两个数字只出现了一次，其他数字都出现了两次，所以输出4和6。
 * 异或运算的一个性质：任何一个数字异或它自己都等于0。
 * 如果从头到尾依次异或数组中的每个数字，那么最终的结果刚好是那个只出现依次的数字，因为那些成对出现两次的数字全部在异或中抵消了。
 * 试着把原数组分成两个子数组，使得每个子数组包含一个只出现一次的数字，而其他数字都成对出现两次。如果能这样拆分成两个数组，那么可以按照前面的办法
 * 找出两个只出现一次的数字。
 * 从头到尾依次异或数组中的每个数字，最终得到的结果就是两个只出现依次的数字的异或结果，因为其他数字都出现了两次，在异或中全部抵消。
 * 由于两个数字肯定不一样，那么异或的结果肯定部位0，也就是说，在这个结果数字的二进制标识中至少有一位为1。
 * 在结果数字中找到第一个为1的位的位置，记位第n位。现在我们以第n位是不是1位标准把原数组中的数字分成两个数组，第一个子数组中每个数字的第n位都是1，而
 * 第二个子数组中每个数字的第n位都是0。
 */
public class Q56_数组中数字出现的次数 {

    public void findNumbersAppearOnce(int[] array, int length, int number1, int number2) {
        if (array == null || length < 2)
            return;
        int resultExclusiveOR = 0;
        for (int i = 0; i < length; ++i)
            resultExclusiveOR ^= array[i];
        int indexOf1 = findFirstBitIs1(resultExclusiveOR);
        number1 = number2 = 0;
        for (int j = 0; j < length; ++j) {
            if (IsBit1(array[j], indexOf1))
                number1 ^= array[j];
            else
                number2 ^= array[j];
        }

    }

    public int findFirstBitIs1(int num) {
        int indexBit = 0;
        while ((num & 1) == 0 && (indexBit < 8 * 4)) {
            num = num >> 1;
            ++indexBit;
        }
        return indexBit;
    }

    public boolean IsBit1(int num, int indexBit) {
        num = num >> indexBit;
        if ((num & 1) == 0) {
            return false;
        }
        return true;
    }

}
