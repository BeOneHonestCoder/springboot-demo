package com.net.interview;

import java.util.ArrayList;
import java.util.List;

public class Question17 {

    private static final String[] MAPPING = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
        List<String> results = letterCombinations("345");
        System.err.println(results);
    }

    private static List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();
        backtrack(digits, 0, new StringBuilder(), results);
        return results;
    }

    private static void backtrack(String digits, int index, StringBuilder path, List<String> res) {
        if (index == digits.length()) {
            res.add(path.toString());
            return;
        }
        String letters = MAPPING[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            path.append(c);
            backtrack(digits, index + 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
