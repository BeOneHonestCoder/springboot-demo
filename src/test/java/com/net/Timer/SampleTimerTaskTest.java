package com.net.Timer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Timer;

@RunWith(SpringRunner.class)
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
