package com.net.interview;


import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Question739 {

    public static void main(String[] args) {
        int[] nums = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] results = dailyTemperatures(nums);
        System.err.println(Arrays.toString(results));
    }

    private static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    answer[i] = j - i;
                    break;
                }
            }
        }
        return answer;
    }

    private static int[] dailyTemperatures1(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }
}
