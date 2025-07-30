package com.net.interview;


import java.util.Arrays;

public class Question881 {

    public static void main(String[] args) {
        int[] people = new int[]{3, 5, 3, 4};
        int limit = 5;
        int result = numRescueBoats(people, limit);
        System.err.println(result);
    }

    private static int numRescueBoats(int[] people, int limit) {
        int ans = 0;
        Arrays.sort(people);
        int light = 0, heavy = people.length - 1;
        while (light <= heavy) {
            if (people[light] + people[heavy] <= limit) {
                ++light;
            }
            --heavy;
            ++ans;
        }
        return ans;
    }
}
