package com.net.interview;


public class Question485 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 0, 1, 1, 1};
        int result = findMaxConsecutiveOnes(nums);
        System.err.println(result);
    }

    private static int findMaxConsecutiveOnes(int[] nums) {
        int currentCount = 0;
        int maxCount = 0;
        for (int num : nums) {
            if (num == 1) {
                currentCount++;
            } else {
                currentCount = 0;
            }
            maxCount = Math.max(maxCount, currentCount);
        }
        return maxCount;
    }
}
