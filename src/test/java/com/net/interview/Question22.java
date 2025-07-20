package com.net.interview;


import java.util.ArrayList;
import java.util.List;

public class Question22 {

    public static void main(String[] args) {
        List<String> results = generateParenthesis(2);
        System.err.println(results);
    }

    private static List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        backtrack(n, results, new StringBuilder(), 0, 0);
        return results;
    }

    private static void backtrack(int n, List<String> results, StringBuilder sb, int open, int close) {
        if (sb.length() == n * 2) {
            results.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append("(");
            backtrack(n, results, sb, open + 1, close);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(")");
            backtrack(n, results, sb, open, close + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
