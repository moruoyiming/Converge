package com.algorithm.demo.array;

import java.util.Arrays;

/**
 * 1119. 三个数的最大乘积
 * 给定一个整数数组，找到三个元素，使乘积最大，返回该积。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: 6
 * 样例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: 24
 * 注意事项
 * 数组的长度范围为[3, 10^4]，所有的元素范围为[-1000, 1000]。
 * 任意三个元素的积不会超过32位有符号整数的范围。
 */
public class Q27_三个数的最大乘积 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
//        int[] nums = {-1, 0, 1, 2};
        int value = maximumProduct(nums);
        System.out.println("  " + value);
    }

    /**
     * 方法一：排序
     * 首先将数组排序。
     * 如果数组中全是非负数，则排序后最大的三个数相乘即为最大乘积；如果全是非正数，则最大的三个数相乘同样也为最大乘积。
     * 如果数组中有正数有负数，则最大乘积既可能是三个最大正数的乘积，也可能是两个最小负数（即绝对值最大）与最大正数的乘积。
     * 综上，我们在给数组排序后，分别求出三个最大正数的乘积，以及两个最小负数与最大正数的乘积，二者之间的最大值即为所求答案。
     * 时间复杂度：O(N\log N)O(NlogN)，其中 NN 为数组长度。排序需要 O(N\log N)O(NlogN) 的时间。
     * 空间复杂度：O(\log N)O(logN)，主要为排序的空间开销。
     *
     * @param nums
     * @return
     */
    private static int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 1] * nums[n - 2] * nums[n - 3]);
    }

    /**
     * 方法二：线性扫描
     * 在方法一中，我们实际上只要求出数组中最大的三个数以及最小的两个数，因此我们可以不用排序，用线性扫描直接得出这五个数。
     * 时间复杂度：O(N)O(N)，其中 NN 为数组长度。我们仅需遍历数组一次。
     * 空间复杂度：O(1)O(1)。
     *
     * @param nums
     * @return
     */
    private static int maximumProduct2(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MAX_VALUE, max3 = Integer.MAX_VALUE;
        for (int x : nums) {
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min2 = x;
            }
            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 =x;
            }

        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }

}
