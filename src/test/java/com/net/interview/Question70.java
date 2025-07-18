package com.net.interview;

import java.util.Arrays;

public class Question70 {

    public static void main(String[] args) {
        int result = climbStairs(10);
        System.err.println(result);
        result = climbStairs2(10);
        System.err.println(result);
    }


    private static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    private static int climbStairs1(int n) {
        if (n <= 2) {
            return n;
        }
        int p = 1, q = 2, r = 0;
        for (int i = 3; i <= n; i++) {
            r = p + q;
            p = q;
            q = r;
        }
        return r;
    }

    private static int climbStairs2(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.err.println(Arrays.toString(dp));
        return dp[n];
    }
}
