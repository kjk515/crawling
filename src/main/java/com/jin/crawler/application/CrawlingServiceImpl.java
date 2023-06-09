package com.jin.crawler.application;

import org.springframework.stereotype.Service;

@Service
public class CrawlingServiceImpl implements CrawlingService {

    private final CrawlingStore crawlingStore;

    public CrawlingServiceImpl(CrawlingStore crawlingStore) {
        this.crawlingStore = crawlingStore;
    }
}
