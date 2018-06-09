package com.net.concurrent;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class SearchFile implements Runnable {

    private final String filePath;
    private final String keyWorld;
    private final AtomicInteger count;

    public SearchFile(String filePath, String keyWorld, AtomicInteger count) {
        this.filePath = filePath;
        this.keyWorld = keyWorld;
        this.count = count;
    }


    @Override
    public void run() {
        try {
            //System.out.println(filePath);
            searchFile(filePath, keyWorld, count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchFile(String filePath, String keyWorld, AtomicInteger count) {
        File dirFile = new File(filePath);
        if (dirFile.listFiles() != null) {
            for (File file : dirFile.listFiles()) {
                if (file.isDirectory()) {
                    searchFile(file.getPath(), keyWorld, count);
                } else {
                    if (file.getName().contains(keyWorld)) {
                        count.getAndIncrement();
                        System.out.println(file.getName());
                    }
                }
            }
        }
    }

}
