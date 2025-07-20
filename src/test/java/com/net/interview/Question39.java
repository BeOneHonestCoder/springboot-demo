package com.net.interview;


import java.util.ArrayList;
import java.util.List;

public class Question39 {

    public static void main(String[] args) {
        int[] candidates = new int[]{2, 3, 5};
        int target = 8;
        List<List<Integer>> results = combinationSum(candidates, target);
        System.err.println(results);
    }

    private static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, new ArrayList<>(), res, 0);
        return res;
    }

    private static void backtrack(int[] candidates, int target, List<Integer> path, List<List<Integer>> res, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) break;
            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], path, res, i);
            path.remove(path.size() - 1);
        }
    }
}
