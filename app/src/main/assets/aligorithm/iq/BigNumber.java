package com.algorithm.demo.iq;

/**
 * 编写用数组实现大整数的类，提供大整数的加、减、乘等运算。
 */
public class BigNumber {
    public static int[] add(int a[], int b[])//大整数加法实现
    {
        int carry = 0;//进位设置
        int c[] = new int[a.length];
        for (int i = c.length - 1; i >= 0; i--) {
            c[i] = a[i] + b[i] + carry;
            if (c[i] > 10000) {
                c[i] = c[i] - 10000;
                carry = 1;
            } else
                carry = 0;
        }
        return c;
    }

    public static int[] subtract(int a[], int b[])//减法实现
    {
        int borrow = 0;
        int c[] = new int[a.length];
        for (int i = c.length - 1; i >= 0; i--) {
            if (a[0] < b[0])
                c[i] = b[i] - a[i] - borrow;
            else
                c[i] = a[i] - b[i] - borrow;
            if (c[i] >= 0)
                borrow = 0;
            else {
                borrow = 1;
                c[i] = c[i] + 10000;
            }
        }
        return c;
    }

    public static int[] multi(int a[], int b[])//乘法实现
    {
        int carry = 0;
        int c[] = new int[a.length + b.length];//两数相乘会使结果数组长度变长
        int d[] = new int[c.length];
        int r, s;
        for (r = c.length - 1, s = a.length - 1; r >= c.length - a.length; r--, s--)
            d[r] = a[s];
        int i, j, k, t;
        for (i = c.length - 1; i >= c.length - a.length - 1; i--) {
            for (k = i, t = i; k >= 0; k--) {
                for (j = b.length - 1; j >= 0; j--) {
                    int temp = d[k] * b[j] + carry;
                    if (t == 0) break;
                    c[t] = (c[t] + temp) % 10000;
                    carry = (c[t] + temp) / 10000;
                    t = t - 1;
                }
            }
        }
        return c;
    }

    public static void getBigNumber(int x[])//输出大整数
    {
        int j = 0;
        while (x[j] == 0)//当数组中第一个不为 0 的元素左边有 0 的元素时，不输出 0
        {
            if (j == x.length - 1)//当循环已到数组最后一个元素时，退出循环
                break;
            j++;
        }
        for (int i = j; i <= x.length - 1; i++)//对从第一个不为 0 的元素进行输出，或输出 0
        {
            if (i > j) {
                if (x[i] < 10)
                    System.out.print("000" + x[i]);
                else if (x[i] < 100)
                    System.out.print("00" + x[i]);
                else if (x[i] < 1000)
                    System.out.print("0" + x[i]);
                else
                    System.out.print(String.valueOf(x[i]));
            }
            if (i == j)
                System.out.print(String.valueOf(x[i]));
        }
    }

    public static void main(String[] args) {
        System.out.println("以数组形式显示两个大整数，如果两个数长度不等时，在短数前补 0 使两数长度相等");
        int a[] = {11, 0000};
        int b[] = {11, 0000};
        int c1[] = BigNumber.add(a, b);
        BigNumber.getBigNumber(c1);
        System.out.println();
        int c2[] = BigNumber.subtract(a, b);
        if (a[0] < b[0])
            System.out.print("-");
        BigNumber.getBigNumber(c2);
        System.out.println();
        int c3[] = BigNumber.multi(a, b);
        BigNumber.getBigNumber(c3);
    }
} 