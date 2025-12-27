package com.net.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HelloWorldService {

    private final SyncService syncService;

    public void doSyncWork() {
        log.info("Started sync work");
        syncService.doAsyncWork1();
        syncService.doAsyncWork2();
        log.info("Sync work finished (async task submitted)");
    }

}
