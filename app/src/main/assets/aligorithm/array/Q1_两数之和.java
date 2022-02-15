package com.algorithm.demo.array;

import com.algorithm.demo.PrintArray;

import java.util.HashMap;


/**
 * 56. 两数之和
 * 给一个整数数组，找到两个数使得他们的和等于一个给定的数 target。
 * <p>
 * 你需要实现的函数twoSum需要返回这两个数的下标, 并且第一个下标小于第二个下标。注意这里下标的范围是 0 到 n-1。
 * <p>
 * 样例
 * 样例1:
 * 给出 numbers = [2, 7, 11, 15], target = 9, 返回 [0, 1].
 * 样例2:
 * 给出 numbers = [15, 2, 7, 11], target = 9, 返回 [1, 2].
 * 挑战
 * 给自己加点挑战
 * <p>
 * O(n)O(n) 空间复杂度，O(nlogn)O(nlogn) 时间复杂度，
 * O(n)O(n) 空间复杂度，O(n)O(n) 时间复杂度，
 * 注意事项
 * 你可以假设只有一组答案。
 * ⣿⣿⣿⣿⣿⠟⠋⠄⠄⠄⠄⠄⠄⠄⢁⠈⢻⢿⣿⣿⣿⣿⣿⣿⣿
 * ⣿⣿⣿⣿⣿⠃⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⡀⠭⢿⣿⣿⣿⣿
 * ⣿⣿⣿⣿⡟⠄⢀⣾⣿⣿⣿⣷⣶⣿⣷⣶⣶⡆⠄⠄⠄⣿⣿⣿⣿
 * ⣿⣿⣿⣿⡇⢀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠄⠄⢸⣿⣿⣿⣿
 * ⣿⣿⣿⣿⣇⣼⣿⣿⠿⠶⠙⣿⡟⠡⣴⣿⣽⣿⣧⠄⢸⣿⣿⣿⣿
 * ⣿⣿⣿⣿⣿⣾⣿⣿⣟⣭⣾⣿⣷⣶⣶⣴⣶⣿⣿⢄⣿⣿⣿⣿⣿
 * ⣿⣿⣿⣿⣿⣿⣿⣿⡟⣩⣿⣿⣿⡏⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
 * ⣿⣿⣿⣿⣿⣿⣹⡋⠘⠷⣦⣀⣠⡶⠁⠈⠁⠄⣿⣿⣿⣿⣿⣿⣿
 * ⣿⣿⣿⣿⣿⣿⣍⠃⣴⣶⡔⠒⠄⣠⢀⠄⠄⠄⡨⣿⣿⣿⣿⣿⣿
 * ⣿⣿⣿⣿⣿⣿⣿⣦⡘⠿⣷⣿⠿⠟⠃⠄⠄⣠⡇⠈⠻⣿⣿⣿⣿
 * ⣿⣿⣿⣿⡿⠟⠋⢁⣷⣠⠄⠄⠄⠄⣀⣠⣾⡟⠄⠄⠄⠄⠉⠙⠻
 * ⡿⠟⠋⠁⠄⠄⠄⢸⣿⣿⡯⢓⣴⣾⣿⣿⡟⠄⠄⠄⠄⠄⠄⠄⠄
 * ⠄⠄⠄⠄⠄⠄⠄⣿⡟⣷⠄⠹⣿⣿⣿⡿⠁⠄⠄⠄⠄⠄⠄⠄⠄
 */
public class Q1_两数之和 {

    public static void main(String[] args) {
        int[] data = {2, 7, 11, 15};
//        int[] data = {15, 2, 7, 11};

        int[] result = sum2(data, 9);
        PrintArray.print(result);
    }

    /**
     * @param numbers: An array of Integer
     * @param target:  target = numbers[index1] + numbers[index2]
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public static int[] twoSum(int[] numbers, int target) {
        // write your code here
        int[] what = new int[2];
        int position = 0;
        int position2 = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    position = i;
                    position2 = j;
                }
            }
        }
        what[0] = position;
        what[1] = position2;
        return what;
    }


    public static int[] twoSum2(int[] numbers, int target) {
        int indexArrayMax = 2047;
        int[] indexArrays = new int[indexArrayMax + 1];
        int diff = 0;
        for (int i = 0; i < numbers.length; i++) {
            diff = target - numbers[i];
            if (indexArrays[diff & indexArrayMax] != 0) {
                return new int[]{indexArrays[diff & indexArrayMax] - 1, i};
            }
            indexArrays[numbers[i] & indexArrayMax] = i + 1;
        }
        throw new IllegalArgumentException("No two sum value");
    }


    public static int[] sum2(int[] numbers, int target) {
        HashMap<Integer, Integer> data = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (data.containsKey(target - numbers[i])) {
                return new int[]{data.get(target - numbers[i]), i};
            } else {
                data.put(numbers[i], i);
                System.out.println(data);
            }
        }
        return new int[0];
    }


    public static int[] sum3(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return null;
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (hashMap.containsKey(target - numbers[i])) {
                return new int[]{hashMap.get(target - numbers[i]), i};
            } else {
                hashMap.put(numbers[i], i);
            }
        }
        return new int[0];
    }

    public static int[] sum4(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return null;
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (hashMap.containsKey(target - numbers[i])){
                return new int[]{hashMap.get(target - numbers[i]) , i};
            }else{
                hashMap.put(numbers[i],i);
            }
        }
        return new int[0];
    }

}
