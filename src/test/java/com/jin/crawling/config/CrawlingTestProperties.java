package com.jin.crawling.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("crawling")
public record CrawlingTestProperties(
        int timeout,
        int maxRetry
) {}
