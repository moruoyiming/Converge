package com.algorithm.demo.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 1613 · 最高频率的IP
 * 题目题解笔记讨论排名
 * 描述
 * 给定一个字符串数组lines, 每一个元素代表一个IP地址，找到出现频率最高的IP。
 * <p>
 * 给定数据只有一个频率最高的IP
 * <p>
 * 样例
 * 样例1:
 * <p>
 * 输入 = ["192.168.1.1","192.118.2.1","192.168.1.1"]
 * 输出  "192.168.1.1"
 */
public class 最高频率的IP {

    /**
     * @param ipLines: ip  address
     * @return: return highestFrequency ip address
     */
    public String highestFrequency(String[] ipLines) {
        // Write your code here
        String result = ipLines[0];
        if (ipLines.length == 0 || ipLines == null) {
            return result;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < ipLines.length; i++) {
            if (map.containsKey(ipLines[i])) {
                map.put(ipLines[i], map.get(ipLines[i]) + 1);
            } else {
                map.put(ipLines[i], 1);
            }
        }
        int max = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max= entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }
}
