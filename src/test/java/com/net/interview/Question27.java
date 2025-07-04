package com.net.interview;


import java.util.Arrays;

public class Question27 {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 2, 3};
        int target = 3;

        int result = removeElement(nums, target);
        System.err.println(result);
        System.err.println(Arrays.toString(nums));
    }

    private static int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }
}
