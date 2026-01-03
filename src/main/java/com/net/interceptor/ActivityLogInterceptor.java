package com.net.interceptor;

import com.net.annotation.ActivityLog;
import com.net.dto.ActivityLogDTO;
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

        ActivityLogDTO dto = ActivityLogDTO.builder()
                .activityType(annotation.value())
                .requestUrl(request.getRequestURI())
                .operationStatus(response.getStatus())
                .requestHeader(extractRequestHeaders(request))
                .responseHeader(extractResponseHeaders(response))
                .requestParameter(request.getQueryString())
                .requestBody(new String(reqWrapper.getContentAsByteArray(), StandardCharsets.UTF_8))
                .responseBody(new String(resWrapper.getContentAsByteArray(), StandardCharsets.UTF_8))
                .timestamp(LocalDateTime.now().toString())
                .build();
        CompletableFuture.runAsync(() -> {
            log.info("[ActivityLog-Async] Thread '{}' started processing log...", Thread.currentThread().getName());

            kafkaTemplate.send("orders", dto).whenComplete((result, ex) -> {
                if (ex != null) {
                    log.error("[ActivityLog-Kafka] Error sending to Kafka: {}", ex.getMessage());
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
