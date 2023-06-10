package com.jin.crawling.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("thread")
public record AsyncProperties(
        int corePoolSize,
        int maxPoolSize,
        int queueCapacity
) {}
