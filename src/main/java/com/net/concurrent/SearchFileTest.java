package com.net.concurrent;

import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;

public class SearchFileTest {

    /*
    * http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
    * */
    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Program Files";
        String file = "E:\\message\\huaqi\\undo.txt";
        String keyWorld = "java";

        //Total counts:337
        //Total seconds:18
        //testSingle(filePath, keyWorld);

        //Total counts:337
        //Total seconds:10
        //testMultiple(filePath, keyWorld);

        //Total counts:337
        //Total seconds:8
        //testForkJoinAction(filePath, keyWorld);

        //Total counts:337
        //Total seconds:8
        //testForkJoinTask(filePath, keyWorld);

        //Total counts:337
        //Total seconds:8
        testStream(file, keyWorld);

    }


    public static void testMultiple(String filePath, String keyWorld) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        AtomicInteger count = new AtomicInteger();
        long start = System.currentTimeMillis();

        File dirFile = new File(filePath);
        try {
            for (File file : dirFile.listFiles()) {
                SearchFile searchFile = new SearchFile(file.getPath(), keyWorld,count);
                executorService.submit(searchFile);
            }
        } finally {
            executorService.shutdown();
        }

        while (!executorService.isTerminated()) {
            Thread.yield();
        }
        System.out.println("Total counts:" + count);
        System.out.println("Total seconds:" + (System.currentTimeMillis() - start) / 1000);
    }


    public static void testSingle(String filePath, String keyWorld) {
        AtomicInteger count = new AtomicInteger();
        long start = System.currentTimeMillis();

        loopFile(filePath,keyWorld,count);
        System.out.println("Total counts:" + count);
        System.out.println("Total seconds:" + (System.currentTimeMillis() - start) / 1000);
    }

    public static void loopFile(String filePath, String keyWorld,AtomicInteger count){
        File dirFile = new File(filePath);
        if (dirFile.listFiles() != null) {
            for (File file : dirFile.listFiles()) {
                if (file.isDirectory()) {
                    loopFile(file.getPath(), keyWorld,count);
                } else {
                    if (file.getName().contains(keyWorld)) {
                        count.getAndIncrement();
                        System.out.println(file.getName());
                    }
                }
            }
        }
    }


    public static void testForkJoinAction(String filePath, String keyWorld){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        AtomicInteger count = new AtomicInteger();
        long start = System.currentTimeMillis();
        SearchFileAction searchFileAction = new SearchFileAction(filePath, keyWorld,count);
        try {
            forkJoinPool.invoke(searchFileAction);
        } finally {
            forkJoinPool.shutdown();
        }
        System.out.println("Total counts:" + count);
        System.out.println("Total seconds:" + (System.currentTimeMillis() - start) / 1000);

    }

    public static void testForkJoinTask(String filePath, String keyWorld){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        AtomicInteger count = new AtomicInteger();
        long start = System.currentTimeMillis();
        SearchFileTask searchFileTask = new SearchFileTask(filePath, keyWorld);
        try {
            forkJoinPool.invoke(searchFileTask);
        } finally {
            forkJoinPool.shutdown();
        }
        count = searchFileTask.join();
        System.out.println("Total counts:" + count);
        System.out.println("Total seconds:" + (System.currentTimeMillis() - start) / 1000);

    }

    public static void testStream(String file, String keyWorld) throws IOException {
        long start = System.currentTimeMillis();

        Files.lines(Paths.get(file)).filter(f -> f.contains(keyWorld)).forEach(System.out::println);

        //System.out.println("Total counts:" + count);
        System.out.println("Total seconds:" + (System.currentTimeMillis() - start) / 1000);

    }



}
