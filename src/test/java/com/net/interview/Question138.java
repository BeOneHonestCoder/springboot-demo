package com.net.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Question138 {

    public static void main(String[] args) {
        Persion person = new Persion(10);
        Map<Persion, Integer> map = new HashMap<>();
        Integer value = map.get(person);
        value = value + 100;
        map.put(person, value);

        Map<Persion, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.compute(person, (k,v)->(v == null) ? 1 : v + 1);
    }

    private static class Persion {
        int val;

        Persion(int x) {
            val = x;
        }
    }
}
