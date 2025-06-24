package com.net.interview;

public class Question35 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        int result = searchInsert(nums, 5);
        System.err.println(result);

        int[] nums1 = new int[]{1, 3, 5, 6};
        int result1 = searchInsert1(nums1, 5);
        System.err.println(result1);
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
