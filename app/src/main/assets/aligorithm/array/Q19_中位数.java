package com.algorithm.demo.array;


import com.algorithm.demo.PrintArray;

/**
 * 80.中位数
 * 给定一个未排序的整数数组，找到其中位数。
 * <p>
 * 中位数是排序后数组的中间值，如果数组的个数是偶数个，则返回排序后数组的第N/2个数。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入：[4, 5, 1, 2, 3]
 * 输出：3
 * 解释：
 * 经过排序，得到数组[1,2,3,4,5]，中间数字为3
 * 样例 2:
 * <p>
 * 输入：[7, 9, 4, 5]
 * 输出：5
 * 解释：
 * 经过排序，得到数组[4,5,7,9]，第二个(4/2)数字为5
 * 挑战
 * 时间复杂度为O(n)
 * <p>
 * 注意事项
 * 数组大小不超过10000
 */
public class Q19_中位数 {
    public static void main(String[] args) {
        int[] array = {4, 5, 1, 2, 3};
        int[] array2 = {7, 9, 4, 5};
        int median = median2(array2);
        System.out.println("median = " + median);

    }

    /**
     * @param nums: A list of integers
     * @return: An integer denotes the middle number of the array
     */
    public static int median(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //pos用于表示每一次遍历的起始比较位置
        int pos = 0;
        //temp用于保存将要插入的数字
        int temp = 0;
        //插入排序
        for (int i = 1; i < nums.length; i++) {
            pos = i - 1;
            temp = nums[i];
            while (pos >= 0 && temp < nums[pos]) {
                nums[pos + 1] = nums[pos];
                pos--;
            }
            nums[pos + 1] = temp;
        }

        if (nums.length % 2 == 0) {
            return nums[nums.length / 2 - 1];
        }
        return nums[nums.length / 2];

    }

    public static int median2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int pos = 0;
        int temp = 0;
        for (int i = 1; i < nums.length; i++) {
            pos = i - 1;
            temp = nums[i];
            while (pos >= 0 && temp < nums[pos]) {
                nums[pos + 1] = nums[pos];
                PrintArray.print(nums);
                pos--;
            }
            nums[pos + 1] = temp;
            PrintArray.print(nums);
        }
        if (nums.length % 2 == 0) {
            return nums[nums.length / 2 - 1];
        }
        System.out.println("  " + (nums.length / 2) + "   " + nums[nums.length / 2]);
        return nums[nums.length / 2];
    }

}
