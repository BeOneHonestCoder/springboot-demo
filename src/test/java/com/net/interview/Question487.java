package com.net.interview;


public class Question487 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, 1, 1, 0};
        int result = findMaxConsecutiveOnes(nums);
        System.err.println(result);
    }

    private static int findMaxConsecutiveOnes(int[] nums) {
        int left = 0;
        int zeroCount = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
