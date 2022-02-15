package com.algorithm.demo.iq;

/**
 * 根据：Sudoku Puzzles - The Rules，来确定数独是否有效。
 * 数独表格可以部分填充，空的单元格用字符’.’来填充。
 */
public class 有效的数独 {

    public static void main(String[] args) {
        int result = '1'-'0';
        int reuslt2 = '1';
        System.out.println("result="+result+" "+reuslt2);
    }

    public static boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] cols = new int[9][9];
        int[][] boxs = new int[9][9];
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0' - 1;//减去0的asc码，对应的数值
                    int k = i / 3 * 3 + j / 3;
                    if (rows[i][num] == 1 || cols[j][num] == 1 || boxs[k][num] == 1)
                        return false;
                    rows[i][num] = cols[j][num] = boxs[k][num] = 1;
                }
            }
        }
        return true;
    }

}
