package com.jin.crawling.application;

import com.jin.crawling.infrastructure.CrawlingClient;
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
