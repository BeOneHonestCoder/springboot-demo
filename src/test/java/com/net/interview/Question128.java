package com.net.interview;


import java.util.HashSet;
import java.util.Set;

public class Question128 {

    public static void main(String[] args) {
        int[] nums = new int[]{100, 4, 200, 1, 3, 2};

        int result = longestConsecutive(nums);
        System.err.println(result);
    }

    private static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int max = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int current = num;
                int streak = 1;

                while (set.contains(current + 1)) {
                    current++;
                    streak++;
                }
                max = Math.max(max, streak);
            }
        }
        return max;
    }
}
