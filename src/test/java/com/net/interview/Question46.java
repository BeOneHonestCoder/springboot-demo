package com.net.interview;


import java.util.ArrayList;
import java.util.List;

public class Question46 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> results = permute(nums);
        System.err.println(results);
    }

    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), res);
        return res;
    }

    private static void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            path.add(nums[i]);
            backtrack(nums, used, path, res);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
