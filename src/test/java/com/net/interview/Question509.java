package com.net.interview;

import java.util.Arrays;

public class Question509 {

    public static void main(String[] args) {
        int result = fib(3);
        System.err.println(result);
        result = fib1(3);
        System.err.println(result);
    }


    private static int fib(int n) {
        if (n < 2) {
            return n;
        }
        int p = 0, q = 1, r = 0;
        for (int i = 2; i <= n; i++) {
            r = p + q;
            p = q;
            q = r;
        }
        return r;
    }

    private static int fib1(int n) {
        if (n < 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.err.println(Arrays.toString(dp));
        return dp[n];
    }
}
