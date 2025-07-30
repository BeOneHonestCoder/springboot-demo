package com.net.interview;


public class Question416 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 11, 5};
        boolean result = canPartition(nums);
        System.err.println(result);
    }

    private static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        return backtrack(nums, sum / 2, 0, 0);
    }

    private static boolean backtrack(int[] nums, int target, int start, int currentSum) {
        if (currentSum == target) return true;
        for (int i = start; i < nums.length; i++) {
            if (backtrack(nums, target, i + 1, currentSum + nums[i])) return true;
        }
        return false;
    }

    private static boolean canPartition1(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        int target = sum / 2;

        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        dp[0][0] = true;

        for (int i = 1; i <= nums.length; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= target; j++) {
                if (j < num) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - num];
                }
            }
        }
        return dp[nums.length][target];
    }

}
