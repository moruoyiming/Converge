package com.algorithm.demo.array;

/**
 * 42.给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class Q53_接雨水 {

    public static void main(String[] args) {
        int[] array = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("result="+getS2(array));
    }

    /**
     * 解法1
     * 新建两个数组 a一个存放左边最大值 b一个存放右边最大值
     * 然后遍历原始数组h，由于木桶效应 承载水的量由短的一边决定 min = min（a[i],b[i]）然后比较min和h[i]的大小如果min>h[i] 就累加min-h[i]的值
     * 以上的做法 需要三次遍历
     * 可以把第二次遍历寻找右边最大值和判断min的大小的第三次遍历合成一次遍历
     *
     * @param height
     * @return
     */
    public static int getS(int[] height) {
        int sum = 0;
        int[] a = new int[height.length];
        int[] b = new int[height.length];
        int leftmax = 0;
        int rightmax = 0;
        for (int i = 0; i < height.length; i++) {
            a[i] = leftmax;
            leftmax = Math.max(leftmax, height[i]);
        }
        for (int i = height.length - 1; i >= 0; i--) {
            a[i] = Math.min(rightmax, a[i]);
            rightmax = Math.max(rightmax, height[i]);
            if (a[i] > height[i]) {
                sum += a[i] - height[i];
            }
        }
        return sum;
    }

    /**
     * 解法2
     * 使用两个指针，一个在数组的最左边，另一个在数组的最右边 记录指针所在位置的左边最大值和右边最大值 如果左边最大值小于右边最大值就移动左边指针，
     * 并且使用左边最大值减去指针位置数组值做累加，反之移动右边指针，直到两个指针相遇
     * @param height
     * @return
     */
    public static int getS2(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int sum = 0;
        int leftmax = 0;
        int rightmax = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            leftmax = Math.max(leftmax, height[i]);
            rightmax = Math.max(rightmax, height[j]);
            System.out.println("leftmax="+leftmax+" ,rightmax="+rightmax);
            if (leftmax < rightmax) {
                System.out.println("before sum="+sum+" ,i="+ i);
                sum += leftmax - height[i];
                i++;
                System.out.println("after sum="+sum+" ,i="+ i);
            } else {
                System.out.println("before sum="+sum+" ,j="+ j);
                sum += rightmax - height[j];
                j--;
                System.out.println("after sum="+sum+" ,j="+ j);
            }
        }
        return sum;
    }

}
