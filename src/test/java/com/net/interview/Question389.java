package com.net.interview;


public class Question389 {

    public static void main(String[] args) {
        char result = findTheDifference("abcd", "abcde");
        System.err.println(result);
    }

    private static char findTheDifference(String s, String t) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result = result ^ s.charAt(i);
        }
        for (int i = 0; i < t.length(); i++) {
            result = result ^ t.charAt(i);
        }
        return (char) result;
    }
}
