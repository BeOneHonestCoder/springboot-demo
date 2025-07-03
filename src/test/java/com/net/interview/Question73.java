package com.net.interview;


import java.util.Arrays;

public class Question73 {

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        setZeroes(nums);
        System.err.println(Arrays.deepToString(nums));
    }

    private static void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
