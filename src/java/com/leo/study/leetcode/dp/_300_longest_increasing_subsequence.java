package com.leo.study.leetcode.dp;

/**
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 */
public class _300_longest_increasing_subsequence {

    public static void main(String[] args) {
        int[] nums = new int[]{10, 2, 2, 5, 1, 7, 101, 18};
        System.out.println(lengthOfLIS2(nums));
    }

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int max = dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
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

    public static int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = 0;
        int[] top = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int j = 0;
            while (j < len) {
                if (top[j] >= nums[i]) {
                    top[j] = nums[i];
                    break;
                }
                j++;
            }
            if (j == len) {
                len++;
                top[j] = nums[i];
            }
        }
        return len;
    }
}
