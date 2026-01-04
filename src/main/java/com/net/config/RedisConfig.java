package com.net.config;

import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import io.lettuce.core.tracing.MicrometerTracing;
import io.micrometer.observation.ObservationRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Slf4j
@Configuration
@EnableCaching
public class RedisConfig implements CachingConfigurer {

    /**
     * [CORE FIX] Explicitly define ClientResources
     * Purpose: Override the default Spring Boot resource manager to force Micrometer observation injection.
     * Without this Bean, Redis Tracing and Metrics (lettuce.command.*) often fail to activate in Spring Boot 3.
     *
     * @param observationRegistry The observation registry auto-configured by Spring Boot.
     * @return Redis client resources with Tracing enabled.
     */
    @Bean(destroyMethod = "shutdown")
    public ClientResources clientResources(ObservationRegistry observationRegistry) {
        return DefaultClientResources.builder()
                .tracing(new MicrometerTracing(observationRegistry, "redis-client"))
                .build();
    }

    /**
     * PRO CacheManager Configuration:
     * 1. Explicitly defines the CacheManager to handle 'sync=true' correctly.
     * 2. Automatically creates missing caches (solving 'Cannot find cache named...' error).
     * 3. Applies the JSON serialization config globally.
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(cacheConfiguration()) // Apply the specific config defined below
                .transactionAware() // Support Spring Transactions (optional but recommended)
                .build();
    }

    /**
     * PRO Configuration:
     * 1. Use JSON Serializer (Jackson) so data in Redis is readable.
     * 2. Set default TTL to prevent memory leaks (e.g., 1 hour).
     * 3. Disable caching of NULL values.
     */
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1)) // Default TTL
                .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    /**
     * PRO Error Handling:
     * When Redis is down, this handler intercepts the exception and logs it.
     * The application treats it as a "Cache Miss" and falls back to the DB gracefully.
     */
    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                log.error("!!!! [Redis Down] GET failed for Key: {}. Error: {}. Falling back to DB.", key, e.getMessage());
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                log.error("!!!! [Redis Down] PUT failed for Key: {}. Data not cached.", key);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                log.error("!!!! [Redis Down] EVICT failed for Key: {}. Cache might be stale.", key);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                log.error("!!!! [Redis Down] CLEAR failed. Error: {}", e.getMessage());
            }
        };
    }
}
