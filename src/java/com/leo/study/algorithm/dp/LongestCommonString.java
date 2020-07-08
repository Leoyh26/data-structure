package com.leo.study.algorithm.dp;

public class LongestCommonString {

    public static void main(String[] args) {
        System.out.println(longestCommonString("ABACD", "CBADA"));
    }

    public static int longestCommonString(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return 0;
        }
        int row = text1.length();
        int col = text2.length();
        int[][] dp = new int[row+1][col+1];
        int max = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = col; j >= 1; j--) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    public static int longestCommonString2(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return 0;
        }
        int row = text1.length();
        int col = text2.length();
        int[] dp = new int[col+1];
        int max = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[j] = dp[j-1] + 1;
                } else {
                    dp[j] = 0;
                }
                max = Math.max(max, dp[j]);
            }
        }
        return max;
    }
}
