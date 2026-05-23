package com.net.interceptor;

import com.net.annotation.ActivityLog;
import com.net.avro.ActivityLogEvent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Component
@Slf4j
public class ActivityLogInterceptor implements HandlerInterceptor {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private Executor taskExecutor2;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        if (!(handler instanceof HandlerMethod)) return;

        ActivityLog annotation = ((HandlerMethod) handler).getMethodAnnotation(ActivityLog.class);

        if (annotation == null || !annotation.enabled()) {
            return;
        }

        log.info("[ActivityLog-Main] Detected @ActivityLog on: {} {}", request.getMethod(), request.getRequestURI());

        ContentCachingRequestWrapper reqWrapper = (ContentCachingRequestWrapper) request;
        ContentCachingResponseWrapper resWrapper = (ContentCachingResponseWrapper) response;

        ActivityLogEvent avroEvent = ActivityLogEvent.newBuilder()
                .setActivityType(annotation.value())
                .setRequestUrl(request.getRequestURI())
                .setOperationStatus(response.getStatus())
                .setRequestHeader(extractRequestHeaders(request))
                .setResponseHeader(extractResponseHeaders(response))
                .setRequestParameter(request.getQueryString())
                .setRequestBody(reqWrapper.getContentAsByteArray().length > 0 ?
                        new String(reqWrapper.getContentAsByteArray(), StandardCharsets.UTF_8) : null)
                .setResponseBody(resWrapper.getContentAsByteArray().length > 0 ?
                        new String(resWrapper.getContentAsByteArray(), StandardCharsets.UTF_8) : null)
                .setTimestamp(LocalDateTime.now().toString())
                .build();

        CompletableFuture.runAsync(() -> {
            log.info("[ActivityLog-Async] Thread '{}' started processing log...", Thread.currentThread().getName());

            kafkaTemplate.send("orders", avroEvent).whenComplete((result, ex) -> {
                if (ex != null) {
                    log.error("[ActivityLog-Kafka] Error sending to Kafka: {}", ex.getMessage());
                } else {
                    log.debug("[ActivityLog-Kafka] Avro message sent successfully.");
                }
            });
        }, taskExecutor2);
    }

    private Map<String, String> extractRequestHeaders(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> names = request.getHeaderNames();
        if (names != null) {
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                map.put(name, request.getHeader(name));
            }
        }
        return map;
    }

    private Map<String, String> extractResponseHeaders(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        Collection<String> names = response.getHeaderNames();
        if (names != null) {
            names.forEach(name -> map.put(name, response.getHeader(name)));
        }
        return map;
    }
}