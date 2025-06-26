package com.net.interview;

import java.util.HashSet;
import java.util.Set;

public class Question287 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 4, 2, 2};
        int result = findDuplicate(nums);
        System.err.println(result);

        result = findDuplicate1(nums);
        System.err.println(result);
    }

    private static int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
        }
        return -1;
    }

    private static int findDuplicate1(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
