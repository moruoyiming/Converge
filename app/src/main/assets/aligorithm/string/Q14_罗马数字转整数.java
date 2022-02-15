package com.algorithm.demo.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。
 */
public class Q14_罗马数字转整数 {
    Map<Character, Integer> symbolValues = new HashMap<>();

    {
        symbolValues.put('I', 1);
        symbolValues.put('V', 5);
        symbolValues.put('X', 10);
        symbolValues.put('L', 50);
        symbolValues.put('C', 100);
        symbolValues.put('D', 500);
        symbolValues.put('M', 1000);
    }

    /**
     * 方法一：模拟
     * 思路
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。若输入的字符串满足该情况，那么可以将每个字符视作一个单独的值，累加每个字符对应的数值即可。
     * 例如 \texttt{XXVII}XXVII 可视作 \texttt{X}+\texttt{X}+\texttt{V}+\texttt{I}+\texttt{I}=10+10+5+1+1=27X+X+V+I+I=10+10+5+1+1=27。
     * 若存在小的数字在大的数字的左边的情况，根据规则需要减去小的数字。对于这种情况，我们也可以将每个字符视作一个单独的值，若一个数字右侧的数字比它大，则将该数字的符号取反。
     * 例如 \texttt{XIV}XIV 可视作 \texttt{X}-\texttt{I}+\texttt{V}=10-1+5=14X−I+V=10−1+5=14。
     * 时间复杂度：O(n)O(n)，其中 nn 是字符串 ss 的长度。
     * 空间复杂度：O(1)O(1)。
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int value = symbolValues.get(s.charAt(i));
            if (i < n - 1 && value < symbolValues.get(s.charAt(i + 1))) {
                ans -= value;
            } else {
                ans += value;
            }
        }
        return ans;
    }

}
