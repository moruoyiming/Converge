package com.algorithm.demo.剑指Offer;

/**
 * 数字在排序数组中出现的次数
 * 统计一个数字在排序数组中出现的次数
 * 例如，输入排序数组{1，2，3，3，3，3，4，5}和数字3，由于3在这个数组中出现了4次，因此输出4.
 * <p>
 * 二分查找算法总是先拿数组中的数字和k作比较。
 * 如果中间的数字比k大，那么k只有可能出现在数组的前半段，下一轮我们只在数组的前半段查找就可以了。
 * 如果中间的数字比k小，那么k只有可能出现在数组的后半段，下一轮我们只在数组的后半段查找就可以了。
 * 如果中间的数字和k相等？
 * 我们先判断这个数字是不是第一个k。
 * 如果中间数字的前面一个数字不是k，那么此时中间的数字刚好就是第一个k。
 * 如果中间数字的前面一个数字也是k，那么第一个k肯定在数组的前半段，下一轮我们仍然需要在数组的前半段查找。
 */
public class Q53_排序数组中查找数字 {
    /**
     * 如果中间的数字和k相等？
     * 我们先判断这个数字是不是第一个k。
     * 如果中间数字的前面一个数字不是k，那么此时中间的数字刚好就是第一个k。
     * 如果中间数字的前面一个数字也是k，那么第一个k肯定在数组的前半段，下一轮我们仍然需要在数组的前半段查找。
     */
    public int getFirstK(int[] array, int length, int k, int start, int end) {
        if (start > end) {
            return -1;
        }
        int middleIndex = (start + end) / 2;
        int middle = array[middleIndex];
        if (middle == k) {
            if ((middleIndex > 0 && array[middleIndex - 1] != k) || middleIndex == 0)
                return middleIndex;
            else
                end = middleIndex - 1;
        } else if (middle > k)
            end = middleIndex - 1;
        else
            start = middleIndex + 1;
        return getFirstK(array, length, k, start, end);
    }


    /**
     * 在排序数组中找到最后一个k。
     * 如果中间数字比k大，那么k只能出现在数组的前半段。
     * 如果中间数字比k小，那么k只能出现在数组的后半段。
     * 如果中间数字等于k
     * 需要判断这个k是不是最后一个k，也就是中间数字的下一个数字是不是也等于k。
     * 如果下一个数字不是k，则中间数字就是最后一个k。
     * 否则下一轮我们还是要在数组的后半段中去查找。
     */
    public int getLastK(int[] array, int length, int k, int start, int end) {
        if (start > end)
            return -1;
        int middleIndex = (start + end) / 2;
        int middle = array[middleIndex];
        if (middle == k) {
            if ((middleIndex < length - 1 && array[middleIndex + 1] != k) || middleIndex == length - 1)
                return middleIndex;
            else
                start = middleIndex + 1;
        } else if (middle < k)
            start = middleIndex + 1;
        else
            end = middleIndex - 1;
        return getLastK(array, length, k, start, end);
    }


    public int getNumberOfK(int[] array, int length, int k) {
        int number = 0;
        if (array != null && array.length > 0) {
            int first = getFirstK(array, length, k, 0, length - 1);
            int last = getLastK(array, length, k, 0, length - 1);
            if (first > -1 && last > -1)
                number = last - first + 1;
        }
        return number;
    }


}
