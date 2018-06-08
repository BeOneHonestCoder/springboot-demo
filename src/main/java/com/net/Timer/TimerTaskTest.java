package com.net.Timer;

import java.util.Timer;

public class TimerTaskTest {

    public static void main(String[] args) throws Exception {
        testTimer();
        Thread.sleep(20000);
    }

    public static void testTimer() {
        Timer timer = new Timer(true);
        try {
            timer.schedule(new SampleTimerTask(timer), 10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
