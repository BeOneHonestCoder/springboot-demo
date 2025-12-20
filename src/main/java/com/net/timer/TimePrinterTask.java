package com.net.timer;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class TimePrinterTask {

    @Scheduled(cron = "0 */1 * * * ?")
    @SchedulerLock(name = "TimePrinterTask", lockAtMostFor = "50s", lockAtLeastFor = "40s")
    public void printCurrentTime() {
        //System.out.println(String.format("Current time: %s", LocalDateTime.now()));
        log.info("Current time: {}", LocalDateTime.now());
    }
}
