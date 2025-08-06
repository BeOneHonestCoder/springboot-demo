package com.net.interview;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Question210 {

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int numCourses = 4;
        int[] results = findOrder(numCourses, nums);
        System.err.println(Arrays.toString(results));
    }

    private static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indegree[info[0]];
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int index = 0;
        int[] results = new int[numCourses];
        while (!queue.isEmpty()) {
            int u = queue.poll();
            results[index++] = u;
            for (int v : edges.get(u)) {
                --indegree[v];
                if (indegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        if (index != numCourses) {
            return new int[0];
        }

        return results;
    }
}
