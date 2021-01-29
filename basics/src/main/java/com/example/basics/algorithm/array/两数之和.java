package com.example.basics.algorithm.array;

import com.example.basics.algorithm.LogUtils;

public class 两数之和 {
    public static void main(String[] args) {
//        int[] data = {2, 7, 11, 15};
        int[] data = {15, 2, 7, 11};

        int[] result =twoSum2(data, 9);
        LogUtils.log(result);
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


    public static int[] twoSum2(int[] nums, int target) {
        int indexArrayMax = 2047;
        int[] indexArrays = new int[indexArrayMax + 1];
        int diff = 0;
        for (int i = 0; i < nums.length; i++) {
            diff = target - nums[i];
            if (indexArrays[diff & indexArrayMax] != 0) {
                return new int[]{indexArrays[diff & indexArrayMax] - 1, i};
            }
            indexArrays[nums[i] & indexArrayMax] = i + 1;
        }
        throw new IllegalArgumentException("No two sum value");
     }

}
