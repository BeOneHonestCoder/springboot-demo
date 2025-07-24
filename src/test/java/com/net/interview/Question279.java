package com.net.interview;


import java.util.Arrays;

public class Question279 {

    public static void main(String[] args) {
        int result = numSquares(12);
        System.err.println(result);
    }

    private static int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
            }
        }
        System.err.println(Arrays.toString(dp));
        return dp[n];
    }
}
