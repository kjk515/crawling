package com.jin.crawling.application;

import org.springframework.stereotype.Service;

@Service
public class CrawlingServiceImpl implements CrawlingService {

    private final Crawler crawler;

    public CrawlingServiceImpl(Crawler crawler) {
        this.crawler = crawler;
    }

    @Override
    public void run() {

    }
}
