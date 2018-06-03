package com.net.concurrent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class SearchFileTask extends RecursiveAction {

    private final String filePath;
    private final String keyWorld;

    public SearchFileTask(String filePath, String keyWorld){
        this.filePath=filePath;
        this.keyWorld=keyWorld;
    }


    @Override
    protected void compute() {
        File dirFile = new File(filePath);
        if (dirFile.listFiles() != null) {
            List<SearchFileTask> taskList = new ArrayList<SearchFileTask>();
            for (File file : dirFile.listFiles()) {
                if (file.isDirectory()) {
                    SearchFileTask fileTask = new SearchFileTask(file.getPath(), keyWorld);
                    fileTask.fork();
                    taskList.add(fileTask);
                } else if (file.getName().contains(keyWorld)) {
                    System.out.println(file.getName());
                }
            }
            for(SearchFileTask task: taskList){
                task.join();
            }
        }
    }
}
