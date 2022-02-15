package com.algorithm.demo.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 607 · 两数之和 III-数据结构设计预发布
 * 描述
 * 设计并实现一个 TwoSum 类。他需要支持以下操作:add 和 find。
 * add -把这个数添加到内部的数据结构。
 * find -是否存在任意一对数字之和等于这个值
 * 样例 1:
 * add(1);add(3);add(5);
 * find(4)//返回true
 * find(7)//返回false
 */
public class Q61_两数之和_数据结构设计 {

    private List<Integer> list = null;
    private Map<Integer, Integer> map = null;


    public void Q61_两数之和_数据结构设计() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }

    /**
     * @param number: An integer
     * @return: nothing
     */
    public void add(int number) {
        // write your code here'
        if (map.containsKey(number)) {
            map.put(number, map.get(number) + 1);
        } else {
            map.put(number, 1);
            list.add(number);
        }
    }

    /**
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        // write your code here
        for (int i = 0; i < list.size(); i++) {
            int num1 = list.get(i),num2 = value - num1;
            if((num1 == num2 && map.get(num1) > 1) ||
                    (num1 != num2 && map.containsKey(num2))){
                return true;
            }
        }
        return false;
    }

}
