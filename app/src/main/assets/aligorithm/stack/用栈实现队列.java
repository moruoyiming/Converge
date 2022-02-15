package com.algorithm.demo.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Date: 2021/12/13
 * @Time: 3:29 下午
 * @Author: Jian
 */
public class 用栈实现队列 {
    Deque<Integer> inStack;
    Deque<Integer> outStack;

    public 用栈实现队列() {
        inStack = new LinkedList<>();
        outStack = new LinkedList<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        if (outStack.isEmpty()) {
            in2Out();
        }
        return outStack.pop();
    }

    public int peek() {
        if (outStack.isEmpty()) {
            in2Out();
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public void in2Out() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }

}
