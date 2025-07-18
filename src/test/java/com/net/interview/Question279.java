package com.net.interview;


import java.util.Arrays;

public class Question279 {

    public static void main(String[] args) {
        int result = numSquares(5);
        System.err.println(result);
    }

    private static int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, dp[i - j * j]);
            }
            dp[i] = minn + 1;
        }
        System.err.println(Arrays.toString(dp));
        return dp[n];
    }
}
