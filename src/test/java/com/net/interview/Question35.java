package com.net.interview;

import java.util.Arrays;

public class Question35 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 8};
        int result = searchInsert1(nums, 9);
        System.err.println(result);
        System.err.println(Arrays.toString(nums));
    }

    private static int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    private static int searchInsert1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
