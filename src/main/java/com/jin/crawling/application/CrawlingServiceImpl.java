package com.jin.crawling.application;

import org.springframework.stereotype.Service;

@Service
public class CrawlingServiceImpl implements CrawlingService {

    private final CrawlingClient crawlingClient;

    public CrawlingServiceImpl(CrawlingClient crawlingClient) {
        this.crawlingClient = crawlingClient;
    }

    @Override
    public void run() {

    }
}
