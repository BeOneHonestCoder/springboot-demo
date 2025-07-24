package com.net.interview;


import java.util.LinkedList;
import java.util.Queue;

public class Question994 {

    public static void main(String[] args) {
        int[][] nums = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        printGrid(nums);
        int result = orangesRotting(nums);
        printGrid(nums);
        System.err.println(result);
    }

    private static int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;
        int minutes = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        if (fresh == 0) return 0;

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty() && fresh > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                for (int[] dir : directions) {
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];

                    if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] != 1) {
                        continue;
                    }

                    grid[x][y] = 2;
                    queue.offer(new int[]{x, y});
                    fresh--;
                }
            }
            minutes++;
        }

        return fresh == 0 ? minutes : -1;
    }

    private static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
