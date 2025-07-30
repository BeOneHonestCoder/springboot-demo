package com.net.interview;


import java.util.ArrayDeque;
import java.util.Queue;

public class Question933 {

    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        Integer result = recentCounter.ping(1);
        System.err.println(result);
        result = recentCounter.ping(100);
        System.err.println(result);
        result = recentCounter.ping(3001);
        System.err.println(result);
        result = recentCounter.ping(3002);
        System.err.println(result);
    }

    private static class RecentCounter {
        Queue<Integer> queue;

        public RecentCounter() {
            queue = new ArrayDeque<>();
        }

        public int ping(int t) {
            queue.offer(t);
            while (!queue.isEmpty() && queue.peek() < t - 3000) {
                queue.poll();
            }
            return queue.size();
        }
    }

}
