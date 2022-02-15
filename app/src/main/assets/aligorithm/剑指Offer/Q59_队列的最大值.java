package com.algorithm.demo.剑指Offer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。
 * 例如，如果输入数组{2，3，4，2，6，2，5，1}及滑动窗口的大小3，那么一共在6个滑动窗口，他们的最大值分别为{4，4，6，6，6，5}
 * 解法一：扫描每个滑动窗口的所有数字并找出其中的最大值。如果滑动窗口的大小为k，则需要O(k)时间才能找出滑动串口里的最大值。
 * 对于长度为n的输入数组，这种算法的总时间复杂的都是O(nk)。
 * <p>
 * 如果把队列用两个栈实现，由于可以用O(1)时间得到栈中的最大值，就可以用O(1)时间得到队列的最大值，因此总的时间复杂度也就降到了O(n)。
 * {[2，3，4]，2，6，2，5，1}  4
 * {2，[3，4，2]，6，2，5，1}  4
 * {2，3，[4，2，6]，2，5，1}  6
 * {2，3，4，[2，6，2]，5，1}  6
 * {2，3，4，2，[6，2，5]，1}  6
 * {2，3，4，2，6，[2，5，1]}  5
 */
public class Q59_队列的最大值 {

    private Deque<Integer> queue;
    private Deque<Integer> help;

    public Q59_队列的最大值() {
        queue = new ArrayDeque<>();
        help = new ArrayDeque<>();
    }

    public int max_value() {
        return queue.isEmpty() ? -1 : help.peek();
    }

    public void push_back(int value) {
        queue.offer(value);
        while (!help.isEmpty() && value > help.peekLast()) {
            help.pollLast();
        }
        help.offer(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        int val = queue.pop();
        if (help.peek() == val) {
            help.pop();
        }
        return val;
    }
}
