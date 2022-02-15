package com.algorithm.demo.interview;

import java.util.*;

/**
 * 一个100大小的数组，里边有人名，随机打印，不重复。
 */
public class 数组随机打印不重复 {
    public static void main(String[] args) {
        findName();
    }

    private static void findName() {
        String[] ids = new String[]{
                "a1", "c1", "b1", "e1", "f1", "g1", "h1", "k1", "x1", "i1", "p1", "31", "41", "11", "m1", "n1",
                "a2", "c2", "b2", "e2", "f2", "g2", "h2", "k2", "x2", "i2", "p2", "32", "42", "12", "m2", "n2",
                "a3", "c3", "b3", "e3", "f3", "g3", "h3", "k3", "x3", "i3", "p3", "33", "43", "13", "m3", "n3",
                "a4", "c4", "b4", "e4", "f4", "g4", "h4", "k4", "x4", "i4", "p4", "34", "44", "14", "m4", "n4",
                "a5", "c5", "b5", "e5", "f5", "g5", "h5", "k5", "x5", "i5", "p5", "35", "45", "15", "m5", "n5",
                "a6", "c6", "b6", "e6", "f6", "g6", "h6", "k6", "x6", "i6", "p6", "36", "46", "16", "m6", "n6",
                "a7", "c7", "b7", "e7", "f7", "g7", "h7", "k7", "x7", "i7", "p7", "37", "47", "17", "m7", "n7",
                "a8", "c8", "b8", "e8", "f8", "g8", "h8", "k8", "x8", "i8", "p8", "38", "48", "18", "m8", "n8",
                "a9", "c9", "b9", "e9", "f9", "g9", "h9", "k9", "x9", "i9", "p9", "39", "49", "19", "m9", "n9",
                "a10", "c10", "b10", "e10", "f10", "g10", "h10", "k10", "x10", "i10", "p10", "310", "410", "110", "m10", "n10",
        };
        Random r = new Random();
        String[] strarray = new String[100];
        int index = 0;
        for (int i = 0; i < 100; i++) {
            //刚开始从数组中随机抽取一个
            //而后将抽取的元素后面的元素向前推进到随机的位置[index位置]
            //随着循环的继续,逐渐抛弃后面的元素
            index = r.nextInt(ids.length - i);
            strarray[i] = ids[index];
            //元素向前推进到随机[index]的位置
            for (int j = index; j < ids.length - i - 1; j++) {
                ids[j] = ids[j + 1];
            }
        }
        Set<String> set = new HashSet<String>();
        for (String string : strarray) {
            System.out.print(string + " ");
            set.add(string);
        }
        System.out.println();
        System.out.println(set + "," + set.size());

    }


}
