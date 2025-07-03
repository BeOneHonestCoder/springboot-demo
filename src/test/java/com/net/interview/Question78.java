package com.net.interview;


import java.util.ArrayList;
import java.util.List;

public class Question78 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> results = subsets(nums);
        System.err.println(results);
    }

    private static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        backtrack(nums, results, new ArrayList<>(), 0);
        return results;
    }

    private static void backtrack(int[] nums, List<List<Integer>> results, List<Integer> result, int start) {
        results.add(new ArrayList<>(result));
        for (int i = start; i < nums.length; i++) {
            result.add(nums[i]);
            backtrack(nums, results, result, i + 1);
            result.remove(result.size() - 1);
        }
    }
}
