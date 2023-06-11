package com.jin.crawling.config;

import lombok.Getter;

@Getter
public enum CacheType {
    Crawling("crawling", 2 * 60, 100);

    private final String name;
    private final int expireTime;
    private final int maxSize;

    CacheType(String name, int expireTime, int maxSize) {
        this.name = name;
        this.expireTime = expireTime;
        this.maxSize = maxSize;
    }
}
