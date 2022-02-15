package com.algorithm.demo.剑指Offer;

/**
 * 题目描述
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组  [0，1，2，4，5，6，7]  可能变为 [4，5，6，7，0，1，2] )。
 * <p>
 * 请找出其中最小的元素。
 * <p>
 * 你可以假设数组中不存在重复元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3，4，5，1，2]
 * 输出: 1
 * <p>
 * 题目解析
 * 最直接的方法是使用 暴力法：搜索整个数组，找到其中的最小元素，这样的时间复杂度是 O(N)，其中 N 是给定数组的大小。
 * <p>
 * 这道题目可以使用 二分搜索 的思想进行解决。
 * <p>
 * 找到数组的中间元素 mid。
 * <p>
 * 如果中间元素 > 数组第 low 个元素，则在 mid 右边搜索变化点。
 * <p>
 * 如果中间元素 < 数组第 high 个元素，则在 mid 左边搜索变化点。
 */
public class Q11_旋转数组的最小数字 {

    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            if (nums[low] < nums[high]) return nums[low];
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }


    public int findMin2(int[] nums, int length) {
        if (nums == null || length < nums.length) {
            return 0;
        }
        int index1 = 0;
        int index2 = length - 1;
        int indexMid = index1;
        while (nums[index1] >= nums[index2]) {
            if (index2 - index1 == 1) {
                indexMid = index2;
                break;
            }
        }
        indexMid = (index1 + index2) / 2;
//        if (nums[indexMid] >= nums[index1]) {
//            index1 = indexMid;
//        } else if (nums[indexMid] <= nums[index2]) {
//            index2 = indexMid;
//        }
        if (nums[index1] == nums[index2] && nums[indexMid] == nums[index1])
            return MinInOrder(nums, index1, index2);
        if (nums[indexMid] >= nums[index1]) {
            index1 = indexMid;
        } else {
            index2 = indexMid;
        }

        return nums[indexMid];
    }

    public int MinInOrder(int[] nums, int index1, int index2) {
        int result = nums[index1];
        for (int i = index1 + 1; i <= index2; ++i) {
            if (result > nums[i]) {
                result = nums[i];
            }
        }
        return result;
    }


}
