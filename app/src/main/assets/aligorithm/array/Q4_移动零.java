package com.algorithm.demo.array;

import com.algorithm.demo.PrintArray;

/**
 * 539. 移动零
 * 给一个数组 nums 写一个函数将 0 移动到数组的最后面，非零元素保持原数组的顺序
 * <p>
 * 样例
 * Example 1:
 * <p>
 * Input: nums = [0, 1, 0, 3, 12],
 * Output: [1, 3, 12, 0, 0].
 * Example 2:
 * <p>
 * Input: nums = [0, 0, 0, 3, 1],
 * Output: [3, 1, 0, 0, 0].
 * 注意事项
 * 1.必须在原数组上操作
 * 2.最小化操作数
 */
public class Q4_移动零 {

    public static void main(String[] args) {
        int[] what = {0, 1, 0, 3, 12};
        moveZeroes(what);
    }

    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public static void moveZeroes(int[] nums) {
        // write your code here
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {//不为 0 移动到数组前方
                nums[count] = nums[i];
                count++;
            }
        }
        for (int i = count; i < nums.length; i++) {//数组后方补0
            nums[i] = 0;
        }
        PrintArray.print(nums);
    }

    public static void moveZeroes2(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[count] = nums[i];
                count++;
            }
        }
        for (int i = count; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void moveZeroes3(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[count] = nums[i];
                count++;
            }
        }
        for (int i = count; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void moveZeroes4(int[] nums) {
        int j = 0;//记录非0元素位置
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                if (i != j) {
                    nums[i] = 0;
                }
                j++;
            }
        }
    }
    //1. loop ， count zeros
    //2. 开启新数组 loop
    //3. index直接操作


}
