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
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] != 0) {
                        int temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                        break;
                    }
                }
            }
        }
        return nums;
    }

    private static int[] moveZeroes1(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;
                slow++;
            }
        }
        return nums;
    }
}
