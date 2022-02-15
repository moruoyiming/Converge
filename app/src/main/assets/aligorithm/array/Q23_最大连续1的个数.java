package com.algorithm.demo.array;

/**
 * 1212. 最大连续1的个数
 * 给定一个二进制数组，找出该数组中最大连续1的个数。
 * <p>
 * 样例
 * 例 1:
 * <p>
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 前两位还有后三位数字是1.
 * 所以最大连续1的个数为3
 * 例 2:
 * <p>
 * 输入: [1]
 * 输出: 1
 * 注意事项
 * -输入数组将只包含0和1。
 * -输入数组的长度为正整数，不超过10000
 */
public class Q23_最大连续1的个数 {

    public static void main(String[] args) {
        int[] temp = {1, 1, 0, 1, 1, 1};
        int count = findMaxConsecutiveOnes(temp);
        System.out.println(" count " + count);
    }

    /**
     * @param nums: a binary array
     * @return: the maximum number of consecutive 1s
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        // Write your code here
        int maxcount = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
                maxcount = Math.max(count, maxcount);
            } else {
                count = 0;
            }
        }
        return maxcount;
    }

    public static int findMaxConsecutiveOnes2(int[] nums) {
        int max = 0;
        int count = 0;
        for(int num : nums){
            if(num == 1){
                count++;
                max = Math.max(max,count);
            }else{
                count = 0;
            }
        }
        return max;
    }


}
