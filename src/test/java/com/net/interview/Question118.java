package com.net.interview;

import java.util.ArrayList;
import java.util.List;

public class Question118 {

    public static void main(String[] args) {
        List<List<Integer>> results = generate(4);
        System.err.println(results);
    }

    private static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> results = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> result = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    result.add(1);
                } else {
                    result.add(results.get(i-1).get(j-1) + results.get(i-1).get(j));
                }
            }
            results.add(result);
        }
        return results;
    }
}
