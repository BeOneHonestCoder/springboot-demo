package com.net.interview;


import java.util.HashMap;
import java.util.Map;

public class Question560 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int k = 3;
        int result = subarraySum(nums, k);
        System.err.println(result);
    }

    private static int subarraySum(int[] nums, int k) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    result++;
                }
            }
        }
        return result;
    }

    private static int subarraySum1(int[] nums, int k) {
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1);
        int result = 0, sum = 0;

        for (int num : nums) {
            sum += num;
            if (prefixSumCount.containsKey(sum - k)) {
                result += prefixSumCount.get(sum - k);
            }
            prefixSumCount.put(sum, prefixSumCount.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
}
