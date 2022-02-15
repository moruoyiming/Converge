package com.algorithm.demo.array;

/**
 * 百钱买百鸡
 * 鸡翁一，值钱五，鸡母一，值钱三，鸡雏三，值一文。百钱买百鸡，问鸡翁，鸡母，鸡雏各几何？
 * 内层循环 21*34 =714 次
 */
public class Q42_百钱买百鸡 {

    public static void main(String[] args) {
        int x, y, z;
        for (x = 1; x <= 20; x++) {
            for (y = 1; y <= 33; y++) {
                z = 100 - x - y;
                if ((5 * x + 3 * y + z / 3) == 100 && z % 3 == 0) {
                    System.out.println(x + "  " + y + "   " + z);
                }
            }
        }
    }


}
