package com.net.interview;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question1268 {

    public static void main(String[] args) {
        String[] words = new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        String searchWord = "mouse";
        List<List<String>> results = suggestedProducts(words, searchWord);
        System.err.println(results);
    }

    private static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();

        Arrays.sort(products);

        StringBuilder prefix = new StringBuilder();
        for (char c : searchWord.toCharArray()) {
            prefix.append(c);

            int left = lowerBound(products, prefix.toString());

            List<String> suggestions = new ArrayList<>();
            for (int i = left; i < products.length && suggestions.size() < 3; i++) {
                if (products[i].startsWith(prefix.toString())) {
                    suggestions.add(products[i]);
                } else {
                    break;
                }
            }

            result.add(suggestions);
        }

        return result;
    }

    private static int lowerBound(String[] arr, String target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid].compareTo(target) >= 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
