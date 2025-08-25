package com.net.interview;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionA {
    private static Integer minDiff = Integer.MAX_VALUE;
    private static Integer totalSum = 0;

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        totalSum = Arrays.stream(nums).sum();
        backtrack(nums, 0, new ArrayList<>());
        System.out.println(minDiff);
    }

    private static void backtrack(int[] nums, int start, List<Integer> selected) {
        if (selected.size() == nums.length / 2) {
            int sum = selected.stream().mapToInt(Integer::intValue).sum();
            int diff = Math.abs(sum - (totalSum - sum));
            minDiff = Math.min(minDiff, diff);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            selected.add(nums[i]);
            backtrack(nums, i + 1, selected);
            selected.remove(selected.size() - 1);
        }
    }

}
