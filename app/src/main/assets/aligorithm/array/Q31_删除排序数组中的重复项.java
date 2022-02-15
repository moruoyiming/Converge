package com.algorithm.demo.array;

import com.algorithm.demo.PrintArray;


/**
 * 题干：
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 示例 1:
 * <p>
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * <p>
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class Q31_删除排序数组中的重复项 {

    public static void main(String[] args) {
        // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
        int[] array = {1, 1, 1, 2};
        int len = removeDuplicates3(array);
        // 在函数里修改输入数组对于调用者是可见的。
        // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
        for (int i = 0; i < len; i++) {
            System.out.println(array[i]);
        }
    }

    /**
     * 第一种思路：首位可替换，重复就去除，长度要减一，然后重复检（后面的值代替前面的已经被检测出重复的值，有前移，下次检测的长度就减一，然后重新再按上次的检测位置重新检测）。
     * 时间复杂度：O(n^2)。
     * 空间复杂度：O(1)。
     *
     * @param array
     * @return
     */
    public static int removeDuplicates(int[] array) {
        if (array.length == 0) return 0;
        int len = array.length;
        for (int i = 1; i < len; ) {
//            LogUtils.log(array);
            if (array[i] == array[i - 1]) {
                System.out.println("xxxxxx");
                for (int j = i; j < len; j++) {
                    System.out.println("yyyyyy");
                    PrintArray.print(array);
                    array[j - 1] = array[j];
                    PrintArray.print(array);
                }
                len--;
            } else {
                i++;
                System.out.println("zzzzzz");
            }
        }
        return len;
    }

    /**
     * 第二种思路：首位不可替换，重复值不处理，外联替换位置（首位我们不在改变它，它就是第一个不重复的值，然后我们需要一个变量来确定我们重复值被不重复的值替换的位置，且重复的值不做处理，直接忽略过）。
     * 时间复杂度：O(n^2)。
     * 空间复杂度：O(1)。
     *
     * @param array
     * @return
     */
    public static int removeDuplicates2(int[] array) {
        if (array.length == 0) return 0;
        int count = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] != array[i]) {
                array[count++] = array[i];
            }
        }
        PrintArray.print(array);
        return count;
    }

    /**
     * 方法：双指针法
     * 数组完成排序后，我们可以放置两个指针 i 和 j，其中 i 是慢指针，而 j 是快指针。只要 nums[i]=nums[j]，我们就增加 j 以跳过重复项。
     * 当我们遇到 nums[j]≠nums[i] 时，跳过重复项的运行已经结束，因此我们必须把它（nums[j]）的值复制到 nums[i + 1]。然后递增 i，接着我们将再次重复相同的过程，直到 j 到达数组的末尾为止。
     * 时间复杂度：O(n)。假设数组的长度是 n，那么 i 和 j 分别最多遍历 n 步。
     * 空间复杂度：O(1)。
     *
     * @param array
     * @return
     */
    public static int removeDuplicates3(int[] array) {
        if (array.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < array.length; j++) {
            if (array[j] != array[i]) {
                i++;
                array[i] = array[j];
            }
        }
        return i + 1;

    }

}
