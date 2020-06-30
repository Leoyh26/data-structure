package com.leo.study.algorithm.recursion;

public class Queen02 {

    public static void main(String[] args) {
        int n = 8;
        new Queen02().placeQueens(n);
    }

    /**
     * 索引代表行 值代表列
     */
    private int[] queens;

    private int ways;

    /**
     * 列摆放了皇后
     */
    private boolean[] cols;

    /**
     * 斜边摆放了皇后（左上角 --> 右下角）
     */
    private boolean[] leftTop;

    /**
     * 斜边摆放了皇后（右上角 --> 左下角）
     */
    private boolean[] rightTop;

    public void placeQueens(int n) {
        if (n < 1) {
            return;
        }
        queens = new int[n];
        cols = new boolean[n];
        leftTop = new boolean[(n << 1) - 1];
        rightTop = new boolean[(n << 1) - 1];
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
            if (cols[col]) {
                continue;
            }
            int ltIndex = row - col + cols.length - 1;
            if (leftTop[ltIndex]) {
                continue;
            }
            int rtIndex = row + col;
            if (rightTop[rtIndex]) {
                continue;
            }
            queens[row] = col;
            cols[col] = leftTop[ltIndex] = rightTop[rtIndex] = true;
            place(row + 1);
            cols[col] = leftTop[ltIndex] = rightTop[rtIndex] = false;
        }
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
