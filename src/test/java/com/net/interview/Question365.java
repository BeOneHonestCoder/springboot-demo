package com.net.interview;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Question365 {

    public static void main(String[] args) {
        int x = 3;
        int y = 5;
        int target = 4;
        boolean result = canMeasureWater(x, y, target);
        System.err.println(result);
    }

    private static boolean canMeasureWater(int x, int y, int target) {
        if (target == 0) return true;
        if (target > x + y) return false;

        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0});
        visited.add("0,0");

        int steps = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.println("Step " + steps + ": processing " + levelSize + " states");

            for (int i = 0; i < levelSize; i++) {
                int[] state = queue.poll();
                int a = state[0];
                int b = state[1];

                if (a == target || b == target || a + b == target) {
                    System.out.println("Found target at step " + steps + " with state (" + a + "," + b + ")");
                    return true;
                }

                List<int[]> nextStates = generateNextStates(a, b, x, y);

                for (int[] ns : nextStates) {
                    String key = ns[0] + "," + ns[1];
                    if (!visited.contains(key)) {
                        visited.add(key);
                        queue.offer(ns);
                    }
                }
            }

            steps++;
        }

        return false;
    }

    private static List<int[]> generateNextStates(int a, int b, int x, int y) {
        List<int[]> states = new ArrayList<>();

        // 1. full A
        states.add(new int[]{x, b});
        // 2. full B
        states.add(new int[]{a, y});
        // 3. empty A
        states.add(new int[]{0, b});
        // 4. empty B
        states.add(new int[]{a, 0});

        // 5. A → B
        int pour = Math.min(a, y - b);
        states.add(new int[]{a - pour, b + pour});

        // 6. B → A
        pour = Math.min(b, x - a);
        states.add(new int[]{a + pour, b - pour});

        return states;
    }
}
