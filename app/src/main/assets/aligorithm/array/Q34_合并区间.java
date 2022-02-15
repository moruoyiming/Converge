package com.algorithm.demo.array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 156. 合并区间
 * 给出若干闭合区间，合并所有重叠的部分。
 * <p>
 * 样例
 * 样例1:
 * <p>
 * 输入: [(1,3)]
 * 输出: [(1,3)]
 * 样例 2:
 * <p>
 * 输入:  [(1,3),(2,6),(8,10),(15,18)]
 * 输出: [(1,6),(8,10),(15,18)]
 * 挑战
 * O(n log n) 的时间和 O(1) 的额外空间。
 */
public class Q34_合并区间 {

    public static void main(String[] args) {
//        Interval interval = new Interval(1, 3);
//        Interval interval2 = new Interval(2, 6);
//        Interval interval3 = new Interval(8, 10);
//        Interval interval4 = new Interval(15, 18);
        Interval interval = new Interval(1, 4);
        Interval interval2 = new Interval(0, 2);
        Interval interval3 = new Interval(3, 5);
        List<Interval> intervals = new ArrayList<>();
        intervals.add(interval);
        intervals.add(interval2);
        intervals.add(interval3);
//        intervals.add(interval4);
        merge(intervals);
    }

    /**
     * @param intervals: interval list.
     * @return: A new interval list.
     * 时间复杂度O(nlogn)
     * 空间复杂度O(1)
     */
    public static List<Interval> merge(List<Interval> intervals) {
        // write your code here
        if (intervals == null || intervals.size() == 0) {
            return intervals;
        }
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval t1, Interval t2) {
                if (t1.start == t2.start) {
                    return t1.end - t2.end;
                }
                return t1.start - t2.start;
            }
        });
        for (int i = 0; i < intervals.size(); i++) {
            System.out.println("     " + intervals.get(i).start + "  "+intervals.get(i).end);
        }
        List<Interval> temp = new ArrayList<>();
        Interval last = null;
        for (Interval item : intervals) {
            if (last == null || last.end < item.start) {
                temp.add(item);
                for (int i = 0; i < temp.size(); i++) {
                    System.out.println("     " + temp.get(i).start + "  "+temp.get(i).end);
                }
                last = item;
            } else {
                last.end = Math.max(last.end, item.end);
            }
        }

        System.out.println(" temp  " + temp.size() + "  " + temp.toString());
        return temp;
    }

    public static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

}
