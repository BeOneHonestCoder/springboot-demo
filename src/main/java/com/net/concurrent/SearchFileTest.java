package com.net.concurrent;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchFileTest {

    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Program Files";
        String keyWorld = "java";
        //Total seconds:13
        //testSingle(filePath, keyWorld);

        //Total seconds:6
        testMultiple(filePath, keyWorld);

    }


    public static void testMultiple(String filePath, String keyWorld) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        long start = System.currentTimeMillis();

        File dirFile = new File(filePath);
        try {
            for (File file : dirFile.listFiles()) {
                SearchFile searchFile = new SearchFile(file.getPath(), keyWorld);
                executorService.submit(searchFile);
            }
        } finally {
            executorService.shutdown();
        }

        while (!executorService.isTerminated()) {
            Thread.yield();
        }
        System.out.println("Total seconds:" + (System.currentTimeMillis() - start) / 1000);
    }


    public static void testSingle(String filePath, String keyWorld) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        long start = System.currentTimeMillis();
        SearchFile searchFile = new SearchFile(filePath, keyWorld);
        try {
            executorService.submit(searchFile);
        } finally {
            executorService.shutdown();
        }

        while (!executorService.isTerminated()) {
            Thread.yield();
        }
        System.out.println("Total seconds:" + (System.currentTimeMillis() - start) / 1000);

    }


}
