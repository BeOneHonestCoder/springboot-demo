package com.net.interview;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Question402 {
    private static String min = null;

    public static void main(String[] args) {
        String num = "1432219";
        int k = 3;
        String result = removeKdigits(num, k);
        System.err.println(result);
        result = removeKdigits1(num, k);
        System.err.println(result);
    }

    private static String removeKdigits1(String num, int k) {
        if (k >= num.length()) return "0";

        Stack<Character> stack = new Stack<>();

        for (char c : num.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && stack.peek() > c) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }

        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }

        while (!sb.isEmpty() && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.isEmpty() ? "0" : sb.toString();
    }

    private static String removeKdigits(String num, int k) {
        backtrack(num, k, new ArrayList<>(), 0);
        return min;
    }

    private static void backtrack(String num, int k, List<Integer> indices, int start) {
        if (indices.size() == k) {
            System.err.println(indices);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < num.length(); i++) {
                if (!indices.contains(i)) {
                    sb.append(num.charAt(i));
                }
            }
            String current = sb.toString().replaceFirst("^0+", "");
            if (current.isEmpty()) current = "0";
            if (min == null || current.compareTo(min) < 0) {
                min = current;
            }
            return;
        }

        for (int i = start; i < num.length(); i++) {
            indices.add(i);
            backtrack(num, k, indices, i + 1);
            indices.remove(indices.size() - 1);
        }
    }

}
