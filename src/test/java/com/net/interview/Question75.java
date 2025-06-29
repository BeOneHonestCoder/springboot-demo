package com.net.interview;


import java.util.Arrays;

public class Question75 {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        sortColors(nums);
        System.err.println(Arrays.toString(nums));

        int[] nums1 = new int[]{2, 0, 2, 1, 1, 0};
        sortColors1(nums1);
        System.err.println(Arrays.toString(nums1));
    }

    private static void sortColors(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                swap(nums, i, j);
                j++;
            }
        }
        for (int i = j; i < nums.length; i++) {
            if (nums[i] == 1) {
                swap(nums, i, j);
                j++;
            }
        }
        System.err.println(j);
    }

    private static void sortColors1(int[] nums) {
        int p0 = 0;
        int p2 = nums.length - 1;
        for (int i = 0; i <= p2; i++) {
            while (i <= p2 && nums[i] == 2) {
                swap(nums, i, p2);
                p2--;
            }
            if (nums[i] == 0) {
                swap(nums, i, p0);
                p0++;
            }
        }
        System.err.println(p0);
        System.err.println(p2);
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
