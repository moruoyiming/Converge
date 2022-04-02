package com.example.converge.note.javabasics.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2022/4/1
 * @Time: 11:01
 * @Author: Jian
 */
public class GenericTest {

    public static void main(String[] args) {
        List list = new ArrayList();//默认是object类型
        list.add("九阴真经");
        list.add("九阳神功");
        list.add(199);//放的时候没问题
        for (int i = 0; i < list.size(); i++) {
            String str = (String) list.get(i);//取的时候因为类型转换会出问题
            System.out.println(str);
        }

        List<String> strs = new ArrayList<>();
        strs.add("降龙十八掌");
        strs.add("六脉神剑");
        strs.add("天山折梅手");
        for (int i = 0; i < strs.size(); i++) {
            String str = strs.get(i);
            System.out.println(str);
        }

    }
}
