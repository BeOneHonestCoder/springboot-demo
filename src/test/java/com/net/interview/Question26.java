package com.net.interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Question26 {


    /**
     * Input: nums = [1, 1, 2]
     * Output: 2, nums = [1, 2, _]
     */
    public static void main(String[] args) {

        //int[] nums = new int[]{1, 2, 3};
        int[] nums = new int[] {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        int result = removeDuplicated(nums);
        System.err.println(result);
        System.err.println(Arrays.toString(nums));

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
}
