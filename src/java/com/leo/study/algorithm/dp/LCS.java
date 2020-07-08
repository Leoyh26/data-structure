package com.leo.study.algorithm.dp;

/**
 * leetcode: 1143
 */
public class LCS {
    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence2("BDCAB", "ABCBDABA"));
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        int row = text1.length();
        int col = text2.length();
        // dp[row][col] 表示text1前row的序列和text2前col的序列的最长公共子序列
        int[][] dp = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[row][col];
    }

    public static int longestCommonSubsequence2(String text1, String text2) {
        int row = text1.length();
        int col = text2.length();
        // dp[i]表示某一行第i列的最长公共子序列
        int[] dp = new int[col + 1];
        for (int i = 1; i <= row; i++) {
            int curr = 0;
            for (int j = 1; j <= col; j++) {
                int leftTop = curr;
                curr = dp[j];
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j-1]);
                }
            }
        }
        return dp[col];
    }
}
