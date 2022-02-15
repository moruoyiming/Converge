package com.algorithm.demo.matrix;

/**
 * 不难发现，机器人从左上角走到右下角，需要向下走m - 1步，向右走n - 1步，那么总步数也是一定的，为m + n - 2步。
 * 问题就转化成，从m + n - 2步中选出m - 1步向下，其余步数自然是向右，有多少种组合？
 * C m-1/m+n-2
 * <p>
 * (m + n - 2)! / (m-1)!(n-1)!
 * 计算三数的阶乘，我们就能得出答案。我们还可以进行一定的优化，展开成上述公式的最右项。设m < n，那么时间复杂度就只有
 * O(m)
 * 时间复杂度: O( min(m,n))。计算阶乘的时间复杂度与m, n中的较小数成线性关系。
 * <p>
 * 空间复杂度: O(1)。常量空间。
 */
public class 不同的路径 {


    /**
     * @param m: positive integer (1 <= m <= 100)
     * @param n: positive integer (1 <= n <= 100)
     * @return: An integer
     */

    public int uniquePaths(int m, int n) {
        // corner case
        if (m == 1 || n == 1) {
            return 1;
        }

        // 保证m<=n
        if (m > n) {
            // swap(m, n)
            int x = n;
            n = m;
            m = x;
        }

        //计算阶乘
        double temp = 1;
        double result = 1;
        for (int i = 1; i <= m - 1; i++) {
            temp *= i;
        }
        for (int i = n; i <= m + n - 2; i++) {
            result *= i;
        }
        result = result / temp;
        return (int) Math.round(result);
    }
}
