package com.algorithm.demo.search;

public class 二分查找 {

    public static void main(String[] args) {
        //[1,4,4,5,7,7,8,9,9,10]，1
        int[] array = {3, 4, 5, 8, 8, 8, 8, 10, 13, 14};
        int x = binarySearch(array, 8);
        System.out.println(" binarySearch " + x);

//        int[] what = {2, 3, 6, 8, 20, 23};
//        int position = binarySearch(what, 6);
//        System.out.println("key position is " + position);
//        int position2 = findFirstEqual(what, 8);
//        System.out.println("key position is " + position2);
    }

    /**
     * @param nums:  The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            System.out.println("middle" + middle + " left " + left + " (right - left) >> 1)  " + (((right - left) >> 1)));
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {//当有重复数据时
                while (middle >= 0) {
                    if (nums[middle] != target) {
                        break;
                    }
                    middle--;
                }
                if (middle <= -1) {
                    return 0;
                }
                return middle + 1;
            }
        }
        return -1;
    }


    /**
     * 二分查找，找到该值在数组中的下标，否则为-1
     * <p>
     * 　每次移动left和right指针的时候，需要在mid的基础上+1或者-1， 防止出现死循环， 程序也就能够正确的运行。
     * 注意：代码中的判断条件必须是while (left <= right)，否则的话判断条件不完整，比如：array[3] = {1, 3, 5};待查找的键为5，
     * 此时在(low < high)条件下就会找不到，因为low和high相等时，指向元素5，但是此时条件不成立，没有进入while()中。
     */
    public static int binarySearch2(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == key) {
                return mid;
            } else if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 查找第一个与key相等的元素
     * 查找第一个相等的元素，也就是说等于查找key值的元素有好多个，返回这些元素最左边的元素下标。
     *
     * @param array
     * @param key
     * @return
     */
    public static int findFirstEqual(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mind = (left + right) / 2;
            if (array[mind] >= key) {
                right = mind - 1;
            } else {
                left = mind + 1;
            }
            if (left < array.length && array[left] == key) {
                return left;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个与key相等的元素
     * 查找最后一个相等的元素，也就是说等于查找key值的元素有好多个，返回这些元素最右边的元素下标。
     *
     * @param array
     * @param key
     * @return
     */
    public static int findLastEqualSmaller(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] > key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * 查找第一个大于key的元素
     *
     * @param array
     * @param key
     * @return
     */
    public static int findFirstLarger(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] > key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 变种总结
    // 这里必须是 <=
    //while(left <=right)
    //
    //    {
    //        int mid = (left + right) / 2;
    //        if (array[mid] ? key) {
    //            //... right = mid - 1;
    //        } else {
    //            // ... left = mid + 1;
    //        }
    //    }
    //return xxx;
}
