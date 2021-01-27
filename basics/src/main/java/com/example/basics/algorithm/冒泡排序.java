package com.example.basics.algorithm;

/**
 * 冒泡排序
 */
public class 冒泡排序 {

    public static void main(String[] args) {

        int[] what = {2, 3, 1, 9, 4, 2, 5, 12, 31, 1};
        System.out.println("排序前顺序");
        for (int i : what) {
            System.out.print(i + "  ");
        }

        for(int i=0;i<what.length-1;i++){
            for(int j=0;j<what.length-i-1;j++){
                if(what[j]<what[j+1]){
                    int temp=what[j];
                    what[j]=what[j+1];
                    what[j+1]=temp;
                }
            }

        }

        System.out.println("排序后顺序");
        for (int i : what) {
            System.out.print(i + "  ");
        }
    }
}
