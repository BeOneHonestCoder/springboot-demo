package com.net.interview;

import java.util.ArrayList;
import java.util.List;

public class Question17 {

    public static void main(String[] args) {
        List<String> results = generateParenthesis(3);
        System.err.println(results);
    }

    private static List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();

        return results;
    }

    private static List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        backTrack(n, new StringBuilder(), results, 0, 0);
        return results;
    }

    private static void backTrack(int n, StringBuilder path, List<String> results, int open, int close) {
        if (path.length() == n * 2) {
            results.add(path.toString());
            return;
        }

        if (open < n) {
            path.append("(");
            backTrack(n, path, results, open + 1, close);
            path.deleteCharAt(path.length() - 1);
        }
        if (close < open) {
            path.append(")");
            backTrack(n, path, results, open, close + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
