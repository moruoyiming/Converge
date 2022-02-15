package com.algorithm.demo.array;

/**
 * 搜索插入位置
 * 给定一个排序数组和一个目标值，如果在数组中找到目标值则返回索引。如果没有，返回到它将会被按顺序插入的位置。
 * <p>
 * 你可以假设在数组中无重复元素。
 * <p>
 * 样例
 * [1,3,5,6]，5 → 2
 * <p>
 * [1,3,5,6]，2 → 1
 * <p>
 * [1,3,5,6]， 7 → 4
 * <p>
 * [1,3,5,6]，0 → 0
 * <p>
 * 挑战
 * 时间复杂度为O(log(n))
 */
public class Q3_搜索插入位置 {

    public static void main(String[] args) {
//        int[] num = {1, 3, 5, 6};
        int[] num = {1, 3, 5, 6};
        int m = searchInsert(num, 0);
        System.out.println(" 位置：" + m);
    }

    /**
     * @param A:      an integer sorted array
     * @param target: an integer to be inserted
     * @return: An integer
     */
    public static int searchInsert(int[] A, int target) {
        if (A == null) return 0;
        int left = 0;
        int right = A.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (A[mid] == target)
                return mid;
            else if (A[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return left;
    }

    public static int searchInsert2(int[] A, int target) {
        if (A == null) return 0;
        int left = 0;
        int right = A.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static int searchInsert3(int[] A, int target){
        if(A == null || A.length == 0){
            return 0;
        }
        int left = 0;
        int right = A.length - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if(A[mid] == target){
                return mid;
            }else if(A[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }


}
