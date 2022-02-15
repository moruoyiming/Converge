package com.algorithm.demo.array;

public class Q8_打印斐波那契数列 {

    public static void main(String[] args) {
        what(10);
    }

    public static void what(int n) {
        int nums[] = new int[n];//产生一个整数数组
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                nums[i] = 0;
            } else if (i <= 2) {
                nums[i] = 1;//前两个数为1
            } else {
                nums[i] = nums[i - 1] + nums[i - 2];//第三个数开始等于前两个数之和
            }
            System.out.print(nums[i] + ",");//打印输出
        }
    }

    public static void what2(int n) {
        int nums[] = new int[n];
        for (int i = 0; i < n; i++) {
            if(i == 0){
                nums[i]= 0;
            }else if( i <= 2){
                nums[i] = 1;
            }else{
                nums[i] = nums[i - 1] + nums[n - 2];
            }
        }
    }


}
