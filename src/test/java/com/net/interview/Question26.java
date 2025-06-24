package com.net.interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Question26 {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int result = removeDuplicated(nums);
        System.err.println(result);
        System.err.println(Arrays.toString(nums));

        int[] nums1 = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int result1 = removeDuplicated1(nums1);
        System.err.println(result1);
        System.err.println(Arrays.toString(nums1));
    }

    private static int removeDuplicated(int[] nums) {
        Set<Integer> uniqueNums = new HashSet<>();
        uniqueNums.add(nums[0]);
        int cursor = 1;
        for (int i = 1; i < nums.length; i++) {
            if (!uniqueNums.contains(nums[i])) {
                uniqueNums.add(nums[i]);
                nums[cursor] = nums[i];
                if (cursor != i) {
                    nums[i] = -1;
                }
                cursor++;
            } else {
                nums[i] = -1;
            }
        }
        System.err.println(uniqueNums);
        return cursor;
    }

    private static int removeDuplicated1(int[] nums) {
        int fast = 1;
        int slow = 1;
        while (fast < nums.length) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
