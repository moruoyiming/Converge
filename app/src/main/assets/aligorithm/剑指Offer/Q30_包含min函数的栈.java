package com.algorithm.demo.剑指Offer;

import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的min函数。在该栈中，调用min、push及pop的时间复杂度都是O(1).
 * <p>
 * 首先往空的数据栈里压入数字3，显然现在3是最小值，把这个最小值压入辅助栈。
 * 接下来往数据栈里压入数字4，由于4大于之气爱你的最小值，因此仍然往辅助栈里压入数字3.第三步继续往数据栈里压入数字2.
 * 由于2小于之前的最小值，因此我们把最小值更新为2，并把2压入辅助栈。同样，当压入数字1时，也要更新最小值，并把心的最小值1压入辅助栈。
 * 如果每次把最小元素压入辅助栈，就能保证辅助栈一直都是最小元素。当最小元素从数据栈内被弹出之后，同时弹出辅助栈的栈顶元素，此时辅助栈的新栈顶元素就
 * 是下一个最小值。
 */
public class Q30_包含min函数的栈 {

    Stack<Integer> stackA = new Stack<>();
    Stack<Integer> stackB = new Stack<>();

    void stackWithMinPush(Integer number) {
        stackA.push(number);
        if (stackB.size() == 0 || number < stackB.peek()) {
            stackB.push(number);
        } else {
            stackB.push(stackB.peek());
        }
    }


    void stackWithMinPop() {
        if(stackA.size()>0&&stackB.size()>0){
            stackA.pop();
            stackB.pop();
        }
    }

    Integer stackWithMin(Integer number) {
        if(stackA.size()>0 && stackB.size() > 0 ){
            return stackB.peek();
        }
        return 0;
    }


}
