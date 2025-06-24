package com.net.interview;

import java.util.Arrays;

public class Question283 {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        int[] results = moveZeroes(nums);
        System.err.println(Arrays.toString(results));

        int[] nums1 = new int[]{0, 1, 0, 3, 12};
        int[] results1 = moveZeroes1(nums1);
        System.err.println(Arrays.toString(results1));
    }

    private static int[] moveZeroes(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] == 0 && nums[j] != 0) {
                    nums[i] = nums[j];
                    nums[j] = 0;
                }
            }
        }
        return nums;
    }

    private static int[] moveZeroes1(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
        }
        return nums;
    }
}
