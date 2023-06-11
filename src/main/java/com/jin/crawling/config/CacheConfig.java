package com.jin.crawling.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.TimeUnit;

@EnableAsync
@Configuration
public class CacheConfig {

    private final AsyncProperties asyncProperties;

    public CacheConfig(AsyncProperties asyncProperties) {
        this.asyncProperties = asyncProperties;
    }

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeine()); // TODO
        return cacheManager;
    }

    @Bean
    public Caffeine caffeine() {
        return Caffeine
                .newBuilder()
                .maximumSize(10_000)
                .expireAfterWrite(60, TimeUnit.SECONDS);
    }
}
