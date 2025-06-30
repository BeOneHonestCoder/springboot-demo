package com.net.interview;


public class Question11 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};

        int result = maxArea(nums);
        System.err.println(result);
    }

    private static int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = Math.min(height[i], height[j]) * (j - i);
                max = Math.max(max, area);
            }
        }
        return max;
    }

    private static int maxArea1(int[] height) {
        int left = 0, right = height.length - 1;
        int max = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(max, area);
            if (height[left] < height[right]) left++;
            else right--;
        }
        return max;
    }
}
