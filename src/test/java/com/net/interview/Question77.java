package com.net.interview;

import java.util.ArrayList;
import java.util.List;

public class Question77 {

    public static void main(String[] args) {
        List<List<Integer>> results = combine(4, 2);
        System.err.println(results);
    }

    private static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> results = new ArrayList<>();
        backtrack(n, k, results, new ArrayList<>(), 1);
        return results;
    }

    private static void backtrack(int n, int k, List<List<Integer>> results, List<Integer> temp, int start) {
        if (temp.size() == k) {
            results.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i <= n; i++) {
            temp.add(i);
            backtrack(n, k, results, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
