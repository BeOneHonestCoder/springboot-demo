package com.net.interview;

public class Question121 {

    public static void main(String[] args) {
        int[] nums = new int[]{7, 1, 5, 3, 6, 4};
        int result = maxProfit(nums);
        System.err.println(result);
    }

    private static int maxProfit(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] - nums[i] > result) {
                    result = nums[j] - nums[i];
                }
            }
        }
        return result;
    }

    private static int maxProfit1(int[] nums) {
        int minPrice = nums[0];
        int maxProfit = 0;
        for (int i = 1; i < nums.length; i++) {
            //(a >= b) ? a : b
            maxProfit = Math.max(maxProfit, nums[i] - minPrice);
            //(a <= b) ? a : b
            minPrice = Math.min(minPrice, nums[i]);
        }
        return maxProfit;
    }
}
