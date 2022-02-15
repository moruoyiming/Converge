package com.algorithm.demo.剑指Offer;

import java.util.Queue;

/**
 * 通过一系列的压入和弹出操作来分析用两个队列模拟一个栈的过程
 * 我们先往栈内压入一个元素a。由于两个队列都是空的，我们可以选择把a插入两个队列的任意一个。我们不妨
 * 把a插入queue1.接下来继续往站内压入b、c两个元素，我们把他们都插入queue1.这个时候queue1包含三个元素a、b和c，其中a位于队列的头部，c位于队列的尾部。
 *
 * 从栈内弹出元素：根据栈的后入先出原则，最后被压入栈的c应该最先被弹出。由于c位于queue1的尾部，我们每次只能从队列的头部删除元素，因此我们可以先从queue1
 * 中一次删除元素a、b并插入queue2，再从queue1中删除元素c。这相当于从栈中弹出元素c了。同样方法从站内弹出元素b。
 *
 * 往站内压入元素d：此时queue1已经有元素，我们就把d插入queue1的尾部，如果我们再从栈内弹出一个元素，我们此时被弹出的应该是最后被压入的d。由于d位于
 * queue1的尾部，我们只能先从头删除queue1的元素并插入queue2，直到queue1中遇到d再直接把它删除。
 *
 * 依次压入 a b c
 * queue1  c  b  a
 * queue2
 *
 * 弹出c
 * queue1
 * queue2 b  a
 *
 * 弹出b
 * queue1 a
 * queue2
 *
 * 压入d
 * queue1  d  a
 * queue2
 *
 * 弹出d
 * queue1
 * queue2  a
 *
 *
 */
public class 两个队列实现一个栈 {
}
