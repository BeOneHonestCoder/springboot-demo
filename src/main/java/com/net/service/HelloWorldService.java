package com.net.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.net.client.JsonPlaceholderClient;
import com.net.domain.ObjectFactory;
import com.net.domain.Student;
import com.net.dto.Post;
import com.net.mapper.JsonMapper;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class HelloWorldService {

    private final JsonMapper mapper;
    private final Tracer tracer;
    private final JsonPlaceholderClient jsonPlaceholderClient;
    private final SyncService syncService;
    private final WebClient jsonPlaceholderWebClient;

    public void triggerAsyncWork() {
        log.info("Started sync work");
        printTrace();
        syncService.doAsyncWork1();
        syncService.doAsyncWork2();
        log.info("Sync work finished (async task submitted)");
    }

    public Post getPostById(Long id) {
        Post post = jsonPlaceholderWebClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(Post.class)
                .block();
        log.info("Post details: {}", post);
        return jsonPlaceholderClient.getPostById(id);
    }

    public Student getStudent() {
        Student std = ObjectFactory.getInstance().createStudent();
        std.setId(1);
        std.setName("JsonMapper");

        String json = null;
        try {
            json = mapper.writeValueAsString(std);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        //System.out.println(json);
        log.info(json);
        return std;
    }

    private void printTrace() {
        String traceId = MDC.get("traceId");
        log.info("traceId from MDC: {}", traceId);

        traceId = Optional.ofNullable(tracer.currentSpan())
                .map(span -> span.context().traceId())
                .orElse("none");
        log.info("traceId from Tracer: {}", traceId);
    }

}
