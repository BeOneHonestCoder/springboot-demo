package com.net.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@Slf4j
@RequiredArgsConstructor
public class SyncService {

    private final Executor taskExecutor2;

    @Async("taskExecutor1")
    public void doAsyncWork1() {
        log.info("Inside @Async task1 - this should have traceId {}", MDC.get("traceId"));
    }

    public void doAsyncWork2() {
        CompletableFuture.runAsync(() -> {
            log.info("Inside @Async task2 - this should have traceId {}", MDC.get("traceId"));
        }, taskExecutor2);
    }
}
