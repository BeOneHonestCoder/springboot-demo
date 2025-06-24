package com.net.interview;

public class Question136 {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 1, 2, 1, 2};
        int result = singleNumber(nums);
        System.err.println(result);
    }

    private static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            //result ^= num;
            result = result ^ num;
        }
        return result;
    }
}
