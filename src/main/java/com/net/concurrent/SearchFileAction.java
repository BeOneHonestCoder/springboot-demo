package com.net.concurrent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

public class SearchFileAction extends RecursiveAction {

    private final String filePath;
    private final String keyWorld;
    private final AtomicInteger count;

    public SearchFileAction(String filePath, String keyWorld, AtomicInteger count) {
        this.filePath = filePath;
        this.keyWorld = keyWorld;
        this.count = count;
    }


    @Override
    protected void compute() {
        File dirFile = new File(filePath);
        if (dirFile.listFiles() != null) {
            List<SearchFileAction> taskList = new ArrayList<SearchFileAction>();
            for (File file : dirFile.listFiles()) {
                if (file.isDirectory()) {
                    SearchFileAction fileTask = new SearchFileAction(file.getPath(), keyWorld, count);
                    fileTask.fork();
                    taskList.add(fileTask);
                } else {
                    if (file.getName().contains(keyWorld)) {
                        count.getAndIncrement();
                        System.out.println(file.getName());
                    }
                }
            }
            for (SearchFileAction task : taskList) {
                task.join();
            }
        }
    }
}
