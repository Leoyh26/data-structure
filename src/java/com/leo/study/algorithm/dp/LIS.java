package com.leo.study.algorithm.dp;

/**
 * leetcode: 300
 */
public class LIS {
    public static void main(String[] args) {
        System.out.println(LIS(new int[]{10, 2, 2, 5, 1, 7, 101, 18}));
    }

    public static int LIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
