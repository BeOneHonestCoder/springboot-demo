package com.net.interview;


import java.util.HashSet;
import java.util.Set;

public class Question41 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 0};
        int result = firstMissingPositive(nums);
        System.err.println(result);
    }

    private static int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int missing = 1;
        while (set.contains(missing)) {
            missing++;
        }
        return missing;
    }

    private static int firstMissingPositive1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }
}
