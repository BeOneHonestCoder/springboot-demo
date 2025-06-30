package com.net.interview;


import java.util.HashSet;
import java.util.Set;

public class Question3 {

    public static void main(String[] args) {
        int result = lengthOfLongestSubstring("abcabcbb");
        System.err.println(result);
    }

    private static int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int left = 0, maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);

            while (window.contains(current)) {
                window.remove(s.charAt(left++));
            }

            window.add(current);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
