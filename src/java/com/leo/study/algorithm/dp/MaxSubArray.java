package com.leo.study.algorithm.dp;

public class MaxSubArray {
    public static void main(String[] args) {
        System.out.println(maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    public static int maxSubArray(int[] nums){
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // dp[i] 以nums[i]结尾的最大连续子序列和
        int[] dp = new int[nums.length];
        int max = dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] <= 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static int maxSubArray2(int[] nums){
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // dp[i] 以nums[i]结尾的最大连续子序列和
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            if (dp <= 0) {
                dp = nums[i];
            } else {
                dp = dp + nums[i];
            }
            max = Math.max(max, dp);
        }
        return max;
    }
}
