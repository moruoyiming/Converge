package com.algorithm.demo.array;


import com.algorithm.demo.PrintArray;

/**
 * 373. 奇偶分割数组
 * 分割一个整数数组，使得奇数在前偶数在后。
 * <p>
 * 样例
 * 样例1:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: [1,3,2,4]
 * 样例2:
 * <p>
 * 输入: [1,4,2,3,5,6]
 * 输出: [1,3,5,4,2,6]
 * 挑战
 * 在原数组中完成，不使用额外空间。
 * <p>
 * 注意事项
 * 答案不唯一。你只需要给出一个合法的答案。
 */
public class Q20_奇偶分割数组 {

    public static void main(String[] args) {
        int[] nums = {1, 4, 2, 3, 5, 6};
        partitionArray(nums);
    }

    /*
     * @param nums: an array of integers
     * @return: nothing
     */
    public static void partitionArray(int[] nums) {
        // write your code here
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (nums[left] % 2 == 1) {
                System.out.println("xxxx" + nums[left]);
                left++;
            }
            while (nums[right] % 2 == 0) {
                System.out.println("yyyy" + nums[right]);
                right--;
            }
            if (left < right) {
                //9=4+5;
                nums[left] = nums[left] + nums[right];
                //4=9-5;
                nums[right] = nums[left] - nums[right];
                //5=9-4;
                nums[left] = nums[left] - nums[right];
            }
            PrintArray.print(nums);
        }
    }
}
