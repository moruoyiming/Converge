package com.algorithm.demo.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 22 · 列表扁平化
 * 描述
 * 给定一个列表，该列表中的每个元素要么是个列表，要么是整数。将其变成一个只包含整数的简单列表。
 * <p>
 * 如果给定的列表中的要素本身也是一个列表，那么它也可以包含列表。
 * <p>
 * 样例
 * 样例 1：
 * <p>
 * 输入：
 * <p>
 * 列表 = [[1,1],2,[1,1]]
 * 输出：
 * <p>
 * [1,1,2,1,1]
 */
public class Q52_列表扁平化 {
    public static void main(String[] args) {

    }

    // @param nestedList a list of NestedInteger
    // @return a list of integer
    public List<Integer> flatten(List<NestedInteger> nestedList) {
        // Write your code here
        List<Integer> result = new ArrayList<Integer>();
        for (NestedInteger nested : nestedList) {
            if (nested.isInteger()) {
                result.add(nested.getInteger());
            } else {
                result.addAll(flatten(nested.getList()));
            }
        }
        return result;
    }

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer,
        // rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds,
        // if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds,
        // if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}
