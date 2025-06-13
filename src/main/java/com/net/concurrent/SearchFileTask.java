package com.net.concurrent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class SearchFileTask extends RecursiveTask<AtomicInteger> {

    private final String filePath;
    private final String keyWorld;

    public SearchFileTask(String filePath, String keyWorld) {
        this.filePath = filePath;
        this.keyWorld = keyWorld;
    }


    @Override
    protected AtomicInteger compute() {
        AtomicInteger count = new AtomicInteger();
        File dirFile = new File(filePath);
        if (dirFile.listFiles() != null) {
            List<SearchFileTask> taskList = new ArrayList<SearchFileTask>();
            for (File file : dirFile.listFiles()) {
                if (file.isDirectory()) {
                    SearchFileTask fileTask = new SearchFileTask(file.getPath(), keyWorld);
                    fileTask.fork();
                    taskList.add(fileTask);
                } else {
                    if (file.getName().contains(keyWorld)) {
                        count.getAndIncrement();
                        System.out.println(file.getName());
                    }
                }
            }
            for (SearchFileTask task : taskList) {
                count.addAndGet(task.join().get());
            }
        }
        return count;
    }
}
