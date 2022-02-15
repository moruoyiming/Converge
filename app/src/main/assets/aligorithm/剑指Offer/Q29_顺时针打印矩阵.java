package com.algorithm.demo.剑指Offer;

/**
 * 输入一个矩阵，按照从外向里顺时针的顺序依次打印出每一个数字。
 * 1   2   3   4
 * 5   6   7   8
 * 9   10  11  12
 * 13  14  15  16
 * 依次打印  1  2  3  4  8  12  16  15  14  13  9  5  6  7  11  10
 * 假设这个矩阵的行数是rows，列数是columns。打印第一圈的左上角的坐标是(0,0)，第二泉的左上角的坐标是(1,1)
 * 左上角的坐标中行标和列表总是相同的，可以在矩阵中选取左上角为(start,start)的圈作为我们分析的目标。
 * <p>
 * 对于5 x 5 的矩阵而言，最后一圈只有一个数字，对应的坐标为(2,2)。发现5>2 x 2 。对于一个 6 x 6的矩阵而言，最后一圈有4个数字，
 * 其左上角的坐标仍然为(2,2)。我们返现6 > 2 x 2依然成立。得出，让循环继续的条件是columns > startX x 2并且 rows>startY x 2
 */
public class Q29_顺时针打印矩阵 {

    public void printMatrix(int[][] numbers, int columns, int rows) {
        if (numbers == null || columns <= 0 || rows <= 0) {
            return;
        }
        int start = 0;
        while (columns > start * 2 && rows > start * 2) {
            printMatrixInCircle(numbers, columns, rows, start);
            ++start;
        }
    }

    /**
     * 第一步：从左到右打印一行
     * 第二步：从上到下打印一列
     * 第三步：从右到左打印一行
     * 第四步：从下网上打印一列
     *
     * @param numbers
     * @param columns
     * @param rows
     */
    private void printMatrixInCircle(int[][] numbers, int columns, int rows, int start) {
        int endX = columns - 1 - start;
        int endY = rows - 1 - start;
        //从左到右打印一行
        for (int i = start; i <= endX; ++i) {
            int number = numbers[start][i];
            printNumber(number);
        }
        //从上到下打印一列
        if (start < endY) {
            for (int i = start + 1; i <= endY; ++i) {
                int number = numbers[i][endX];
                printNumber(number);
            }
        }
        //从右到左打印一行
        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start; --i) {
                int number = numbers[endY][i];
                printNumber(number);
            }
        }
        //从下到上打印一列
        if (start < endX && start < endY - 1) {
            for(int i = endY - 1; i >= start + 1;--i){
                int number = numbers[i][start];
                printNumber(number);
            }
        }
    }

    private void printNumber(int number) {
    }

}
