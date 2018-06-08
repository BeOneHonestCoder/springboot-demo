package com.net.Timer;

import java.util.Timer;
import java.util.TimerTask;

public class SampleTimerTask extends TimerTask {

    private final Timer timer;

    public SampleTimerTask(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void run() {
        try {
            System.out.println("Hello again!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            timer.cancel();
        }
    }
}
