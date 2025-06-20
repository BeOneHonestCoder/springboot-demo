package com.net.interview;

import java.util.Arrays;

public class Question338 {

    public static void main(String[] args) {
        int[] results = countBits(5);
        System.err.println(Arrays.toString(results));
    }


    private static int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            res[i] = Integer.bitCount(i);
        }
        return res;
    }

    private static int[] countBits1(int n) {
        int[] res = new int[n + 1];
        res[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                res[i] = res[i / 2];
            } else {
                res[i] = res[i - 1] + 1;
            }
        }
        return res;
    }
}
