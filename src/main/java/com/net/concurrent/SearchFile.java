package com.net.concurrent;

import java.io.File;
import java.util.concurrent.CountDownLatch;

public class SearchFile implements Runnable {

    private final String filePath;
    private final String keyWorld;

    public SearchFile(String filePath, String keyWorld) {
        this.filePath = filePath;
        this.keyWorld = keyWorld;
    }


    @Override
    public void run() {
        try {
            //System.out.println(filePath);
            searchFile(filePath, keyWorld);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchFile(String filePath, String keyWorld) {
        File dirFile = new File(filePath);
        if (dirFile.listFiles() != null) {
            for (File file : dirFile.listFiles()) {
                if (file.isDirectory()) {
                    searchFile(file.getPath(), keyWorld);
                } else if (file.getName().contains(keyWorld)) {
                    System.out.println(file.getName());
                }
            }
        }
    }

}
