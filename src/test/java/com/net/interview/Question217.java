package com.net.interview;


import java.util.HashSet;
import java.util.Set;

public class Question217 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1};
        boolean result = containsDuplicate(nums);
        System.err.println(result);
    }

    private static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}
