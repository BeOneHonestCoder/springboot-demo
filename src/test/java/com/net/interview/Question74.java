package com.net.interview;


public class Question74 {

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 3;
        boolean result = searchMatrix(nums, target);
        System.err.println(result);
    }

    private static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;

        int top = 0, bottom = matrix.length - 1;
        int targetRow = 0;
        while (top <= bottom) {
            int mid = top + (bottom - top) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                top = mid + 1;
                targetRow = mid;
            } else {
                bottom = mid - 1;
            }
        }

        int left = 0, right = matrix[targetRow].length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[targetRow][mid] == target) {
                return true;
            } else if (matrix[targetRow][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
