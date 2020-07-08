package com.leo.study.algorithm.dp;

public class CoinChange {

    public static void main(String[] args) {
        System.out.println(coins(55, new int[]{5, 20, 25}));
    }

    /**
     * 最少兑换数
     * @param n 兑换金额
     * @param faces 面值
     * @return
     */
    public static int coins(int n, int[] faces){
        if (n < 1 || faces == null || faces.length == 0) {
            return -1;
        }
        // dp[i]表示兑换i最少兑换数
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = i + 1;
            for (int face : faces) {
                if (i < face || dp[i - face] == -1) {
                    continue;
                }
                min = Math.min(min, dp[i - face]);
            }
            if (min == i + 1) {
                dp[i] = -1;
            } else {
                dp[i] = min + 1;
            }
        }
        return dp[n];
    }
}
