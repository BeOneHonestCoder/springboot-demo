package com.net.timer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Timer;

@SpringBootTest
public class SampleTimerTaskTest {

    @Test
    public void testRun(){
        Timer timer = Mockito.mock(Timer.class);
        SampleTimerTask classUnderTest = new SampleTimerTask(timer);

        classUnderTest.run();
        Mockito.verify(timer).cancel();
    }

}
