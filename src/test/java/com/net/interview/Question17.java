package com.net.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question17 {

    public static void main(String[] args) {
        List<String> results = letterCombinations("345");
        System.err.println(results);
    }

    private static List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.isEmpty()) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(digits, 0, new StringBuilder(), combinations, phoneMap);
        return combinations;
    }

    private static void backtrack(String digits, int index, StringBuilder path, List<String> res, Map<Character, String> phoneMap) {
        if (index == digits.length()) {
            res.add(path.toString());
            return;
        }
        char digit = digits.charAt(index);
        String letters = phoneMap.get(digit);
        for (char c : letters.toCharArray()) {
            path.append(c);
            backtrack(digits, index + 1, path, res, phoneMap);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
