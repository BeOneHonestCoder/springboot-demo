package com.net.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class HttpFeignClientConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
