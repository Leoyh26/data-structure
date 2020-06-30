package com.leo.study.algorithm.recursion;

public class Queen01 {

    public static void main(String[] args) {
        int n = 8;
        new Queen01().placeQueens(n);
    }

    /**
     * 索引代表行 值代表列
     */
    private int[] queens;

    private int ways;

    public void placeQueens(int n) {
        if (n < 1) {
            return;
        }
        queens = new int[n];
        place(0);
        System.out.println(n + "皇后一共有: " + ways + "种摆法");
    }

    /**
     * 摆放第 row 列皇后
     * @param row
     */
    public void place(int row) {
        if (row == queens.length) {
            ways++;
            show();
            return;
        }
        for (int col = 0; col < queens.length; col++) {
            if (isValid(row, col)) {
                queens[row] = col;
                place(row + 1);
            }
        }
    }

    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col) {
                return false;
            }
            if ((row - i) == Math.abs(col - queens[i])) {
                return false;
            }
        }
        return true;
    }

    private void show() {
        for (int row = 0; row < queens.length; row++) {
            for (int col = 0; col < queens.length; col++) {
                if (queens[row] == col) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println("--------------------");
    }
}
